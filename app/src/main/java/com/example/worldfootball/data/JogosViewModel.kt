package com.example.worldfootball.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
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
}