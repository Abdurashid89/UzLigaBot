package com.uz.abumax.uzligabot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@ComponentScan("com")
@EntityScan("com.uz.abumax.uzligabot.entity")
@EnableJpaRepositories("com.uz.abumax.uzligabot.repository")
@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
class UzligabotApplication

fun main(args: Array<String>) {
	runApplication<UzligabotApplication>(*args)
}
