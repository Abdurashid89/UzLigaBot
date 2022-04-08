import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	//id("org.springframework.boot") version "2.7.0-SNAPSHOT"
	id("application")
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.10"
	kotlin("plugin.spring") version "1.6.10"
}

group = "com.uz.abumax"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {

	implementation("org.springframework.boot:spring-boot-starter-web:2.6.5")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.2")
	implementation("org.telegram:telegrambots-spring-boot-starter:5.7.1")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("org.springframework.boot:spring-boot-starter-test")


	implementation("org.springframework.boot:spring-boot-starter:2.6.5") //
	runtimeOnly("org.postgresql:postgresql")
//	implementation("com.fasterxml.jackson.core:jackson-core:2.11.0")
//	implementation("com.fasterxml.jackson.core:jackson-databind:2.11.0")
	implementation("org.json:json:20090211")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
