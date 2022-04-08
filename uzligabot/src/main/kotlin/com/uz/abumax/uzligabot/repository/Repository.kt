package com.uz.abumax.uzligabot.repository

import com.uz.abumax.uzligabot.entity.Club
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface Repository : CrudRepository<Club, String> {

//    @Query("select * from clubs")
//    fun clubs(): List<Club>
}