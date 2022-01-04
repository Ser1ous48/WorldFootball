package com.example.worldfootball.api_worldfootball.dto

import com.example.worldfootball.api_worldfootball.models.User

data class UserDto (
    val status : String,
    val message : String,
    val user : List<User>,
    val token : String
)