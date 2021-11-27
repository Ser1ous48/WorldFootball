package com.example.worldfootball.repository

import androidx.lifecycle.LiveData
import com.example.worldfootball.data.JogoDao
import com.example.worldfootball.model.jogos

class JogoRepositorio(private val jogoDao: JogoDao) {

    val readAllData: LiveData<List<jogos>> = jogoDao.readAllData()

    suspend fun addJogos(jogos: jogos){
        jogoDao.addJogos(jogos)
    }

    suspend fun updateJogos(jogos: jogos){
        jogoDao.updateJogos(jogos)
    }

    suspend fun deleteJogos(jogos: jogos){
        jogoDao.deleteJogos(jogos)
    }

    suspend fun deleteAllJogos(){
        jogoDao.deleteAllJogos()
    }
}