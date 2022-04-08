package com.uz.abumax.uzligabot.entity

import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "player")
class Player(

    val firstName: String,
    var lastName: String? = null,
    var POSITION: POSITION,
    var goals: Int? = null,
    val loseGoals: Int? = null,

    @ManyToOne
    @JoinColumn(name = "club_id")
    val club: Club?=null
) : BaseEntity<Long>()

enum class POSITION {
    FORWARD, DEFENCIVE, HALF_BACK, GOAL_KIPPER
}