package com.uz.abumax.uzligabot.repository

import com.uz.abumax.uzligabot.entity.Club
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ClubRepository : CrudRepository<Club, Long> {
    fun findByName(name: String): Iterable<Club>

    fun findAllClubs(): List<Club>
}