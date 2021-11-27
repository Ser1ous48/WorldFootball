package com.example.worldfootball.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.worldfootball.repository.JogoRepositorio
import com.example.worldfootball.data.jogosDatabase
import com.example.worldfootball.model.jogos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class JogosViewModel(application: Application): AndroidViewModel(application){

    val readAllData: LiveData<List<jogos>>
    private val repositorio: JogoRepositorio

    init {
        val JogoDao = jogosDatabase.getDatabase(application).jogoDao()
        repositorio = JogoRepositorio(JogoDao)
        readAllData = repositorio.readAllData
    }

    fun addJogos(jogos: jogos){
        viewModelScope.launch(Dispatchers.IO) {
            repositorio.addJogos(jogos)
        }
    }

    fun updateJogos(jogos: jogos){
        viewModelScope.launch(Dispatchers.IO){
            repositorio.updateJogos(jogos)
        }
    }
}