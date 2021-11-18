package com.example.worldfootball.data

import androidx.lifecycle.LiveData
import  androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface JogoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addJogos(jogos: jogos)

    @Query("SELECT * FROM tabela_jogos ORDER BY id ASC")
    fun readAllData(): LiveData<List<jogos>>
}