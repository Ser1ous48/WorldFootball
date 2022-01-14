package com.example.worldfootball.api_worldfootball.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Jogos_Models (
    val id: Int,
    val users_id : Int,
    val user_name: String,
    val EquipaCasa: String,
    val EquipaFora: String,
    val ResultadoCasa: Int,
    val ResultadoFora: Int,
    val AmarelosCasa: Int,
    val AmarelosFora: Int,
    val VermelhosCasa: Int,
    val VermelhosFora: Int
    //val created_at : String,
    /*val title: String,
    val description: String,*/
) : Parcelable
