package com.example.worldfootball.data

import androidx.lifecycle.LiveData

class JogoRepositorio(private val jogoDao: JogoDao) {

    val readAllData: LiveData<List<jogos>> = jogoDao.readAllData()

    suspend fun addJogos(jogos: jogos){
        jogoDao.addJogos(jogos)
    }
}