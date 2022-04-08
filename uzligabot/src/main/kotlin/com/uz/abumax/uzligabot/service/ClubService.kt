package com.uz.abumax.uzligabot.service

import com.uz.abumax.uzligabot.entity.Club
import com.uz.abumax.uzligabot.repository.ClubRepository
import com.uz.abumax.uzligabot.repository.MyRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

@Repository
class ClubService(val db : MyRepository) {

    fun add(club : Club){
        db.save(club)
    }
}