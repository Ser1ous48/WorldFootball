package com.example.worldfootball.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Entity(tableName = "tabela_jogos")

@Parcelize
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
    val VermelhosFora: Int,
    //@ColumnInfo(defaultValue = "")
    //val DateGame: String
): Parcelable