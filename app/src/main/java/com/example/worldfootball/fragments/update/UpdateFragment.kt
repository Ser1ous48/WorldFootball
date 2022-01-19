package com.example.worldfootball.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.worldfootball.R
import com.example.worldfootball.model.jogos
import com.example.worldfootball.viewmodel.JogosViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mJogosViewModel: JogosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mJogosViewModel = ViewModelProvider(this).get(JogosViewModel::class.java)

        view.UpdateEquipaCasa.setText(args.currentJogos.EquipaCasa)
        view.UpdateEquipaFora.setText(args.currentJogos.EquipaFora)
        view.UpdateGolosMarcadosCasa.setText(args.currentJogos.ResultadoCasa.toString())
        view.UpdateGolosMarcadosFora.setText(args.currentJogos.ResultadoFora.toString())
        view.UpdateAmarelosCasa.setText(args.currentJogos.AmarelosCasa.toString())
        view.UpdateAmarelosFora.setText(args.currentJogos.AmarelosFora.toString())
        view.UpdateVermelhosCasa.setText(args.currentJogos.VermelhosCasa.toString())
        view.UpdateVermelhosFora.setText(args.currentJogos.VermelhosFora.toString())

        view.update_btn.setOnClickListener{
            updateItem()

        }

        // Add menu
        setHasOptionsMenu(true)

        return view
    }



    private fun updateItem(){
        val EquipaCasa = UpdateEquipaCasa.text.toString()
        val EquipaFora = UpdateEquipaFora.text.toString()
        val ResultadoCasa = Integer.parseInt(UpdateGolosMarcadosCasa.text.toString())
        val ResultadoFora = Integer.parseInt(UpdateGolosMarcadosFora.text.toString())
        val AmarelosCasa = Integer.parseInt(UpdateAmarelosCasa.text.toString())
        val AmarelosFora = Integer.parseInt(UpdateAmarelosFora.text.toString())
        val VermelhosCasa = Integer.parseInt(UpdateVermelhosCasa.text.toString())
        val VermelhosFora = Integer.parseInt(UpdateVermelhosFora.text.toString())

        if(inputcheck(EquipaCasa, EquipaFora, UpdateGolosMarcadosCasa.text, UpdateGolosMarcadosFora.text, UpdateAmarelosCasa.text, UpdateAmarelosFora.text, UpdateVermelhosCasa.text, UpdateVermelhosFora.text)){
            // Create Jogos Object
            val UpdateJogos = jogos(args.currentJogos.id, EquipaCasa, EquipaFora, ResultadoCasa, ResultadoFora, AmarelosCasa, AmarelosFora, VermelhosCasa, VermelhosFora)
            // Update Current Jogos
            mJogosViewModel.updateJogos(UpdateJogos)
            Toast.makeText(requireContext(), R.string.AtualizarJogo, Toast.LENGTH_SHORT).show()
            //Navigate Back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), R.string.AtualizarErro, Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputcheck(EquipaCasa: String, EquipaFora: String, GolosMarcadosCasa: Editable, GolosMarcadosFora: Editable, AmarelosCasa: Editable, AmarelosFora: Editable, VermelhosCasa: Editable, VermelhosFora: Editable): Boolean{
        return !(TextUtils.isEmpty(EquipaCasa) && TextUtils.isEmpty(EquipaFora) && GolosMarcadosCasa.isEmpty() && GolosMarcadosFora.isEmpty() && AmarelosCasa.isEmpty() && AmarelosFora.isEmpty() && VermelhosCasa.isEmpty() && VermelhosFora.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteJogos()
        }

        if(item.itemId == R.id.maps){
            //findNavController().navigate(R.id.action_listFragment_to_maps_2)
            openmaps()
        }

        if (item.itemId == R.id.user_login) {
            openlogin()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteJogos(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton(R.string.Sim) {_,_ ->
            mJogosViewModel.deleteJogos(args.currentJogos)
            Toast.makeText(requireContext(), R.string.ApagarSucesso, Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton(R.string.Não){_,_ ->

        }
        builder.setTitle(R.string.ApagarJogo)
        builder.setMessage(R.string.ConfirmarApagar)
        builder.create().show()
    }

    private  fun openlogin(){
        findNavController().navigate(R.id.action_updateFragment_to_userLoginFragment2)//action_listFragment_to_userLoginFragment
    }

    private  fun openmaps(){
        findNavController().navigate(R.id.action_updateFragment_to_maps2)//action_listFragment_to_maps
    }



}