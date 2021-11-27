package com.example.worldfootball.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.worldfootball.R
import com.example.worldfootball.viewmodel.JogosViewModel
import com.example.worldfootball.model.jogos
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var mJogoViewModel : JogosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mJogoViewModel = ViewModelProvider(this).get(JogosViewModel::class.java)

        view.add_btn.setOnClickListener{
            InsertDatatoDatabase()
        }

        return view
    }
    private fun InsertDatatoDatabase(){
        val EquipaCasa = EquipaCasa.text.toString()
        val EquipaFora = EquipaFora.text.toString()
        val GolosMarcadosCasa = GolosMarcadosCasa.text
        val GolosMarcadosFora = GolosMarcadosFora.text
        val AmarelosCasa = AmarelosCasa.text
        val AmarelosFora = AmarelosFora.text
        val VermelhosCasa = VermelhosCasa.text
        val VermelhosFora = VermelhosFora.text

        if(inputcheck(EquipaCasa, EquipaFora, GolosMarcadosCasa, GolosMarcadosFora, AmarelosCasa, AmarelosFora, VermelhosCasa, VermelhosFora)){
            val jogo = jogos(0, EquipaCasa,
                EquipaFora,
                Integer.parseInt(GolosMarcadosCasa.toString()),
                Integer.parseInt(GolosMarcadosFora.toString()),
                Integer.parseInt(AmarelosCasa.toString()),
                Integer.parseInt(AmarelosFora.toString()),
                Integer.parseInt(VermelhosCasa.toString()),
                Integer.parseInt(VermelhosFora.toString()),
                )

            mJogoViewModel.addJogos(jogo)
            Toast.makeText(requireContext(), R.string.JogoAdicionado, Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), R.string.AdicionarErro, Toast.LENGTH_LONG).show()
        }
    }

    private fun inputcheck(EquipaCasa: String, EquipaFora: String, GolosMarcadosCasa: Editable, GolosMarcadosFora: Editable, AmarelosCasa: Editable, AmarelosFora: Editable, VermelhosCasa: Editable, VermelhosFora: Editable): Boolean{
        return !(TextUtils.isEmpty(EquipaCasa) && TextUtils.isEmpty(EquipaFora) && GolosMarcadosCasa.isEmpty() && GolosMarcadosFora.isEmpty() && AmarelosCasa.isEmpty() && AmarelosFora.isEmpty() && VermelhosCasa.isEmpty() && VermelhosFora.isEmpty())
    }


}