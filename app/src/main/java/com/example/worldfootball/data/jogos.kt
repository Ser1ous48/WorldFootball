package com.example.worldfootball.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabela_jogos")


data class jogos(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val EquipaCasa: String,
    val EquipaFora: String,
    val ResultadoCasa: Int,
    val ResultadoFora: Int,
    val AmarelosCasa: Int,
    val AmarelosFora: Int,
    val VermelhosCasa: Int,
    val VermelhosFora: Int

)