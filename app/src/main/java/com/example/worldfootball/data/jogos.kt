package com.example.worldfootball.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabela_jogos")


data class jogos(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val EquipaCasa: String,
    val EquipaFora: String,
    val Resultado: String,
    val Amarelos: Int,
    val Vermelhos: Int

)