package com.uz.abumax.uzligabot.entity

import org.springframework.stereotype.Component
import javax.persistence.*

@Entity
//@Table(name = "clubs")
class Club(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val name: String = "",
    var wins: Int = 0,
    var lose: Int = 0,
    var draw: Int = 0,
    var goals: Int = 0,
    var loseGoals: Int = 0,
    var isCreated: Boolean = false,
    var titleCount: Int = 0,

) : BaseEntity<Long>()