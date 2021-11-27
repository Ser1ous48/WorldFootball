package com.example.worldfootball.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.worldfootball.model.jogos

@Dao
interface JogoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addJogos(jogos: jogos)

    @Update
    fun updateJogos(jogos: jogos)

    @Query("SELECT * FROM tabela_jogos ORDER BY id ASC")
    fun readAllData(): LiveData<List<jogos>>
}