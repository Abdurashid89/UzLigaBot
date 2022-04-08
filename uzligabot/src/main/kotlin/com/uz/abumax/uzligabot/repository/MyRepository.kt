package com.uz.abumax.uzligabot.repository

import com.uz.abumax.uzligabot.entity.Club
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service


@Service
interface MyRepository : JpaRepository<Club,Long> {

}