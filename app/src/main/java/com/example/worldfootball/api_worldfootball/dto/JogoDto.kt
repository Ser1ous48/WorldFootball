package com.example.worldfootball.api_worldfootball.dto

import com.example.worldfootball.api_worldfootball.models.Jogos_Models


data class JogoDto(
    val status : String,
    val message : String,
    val jogos_Models : Jogos_Models
)
