package com.example.worldfootball.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.worldfootball.R
import com.example.worldfootball.viewmodel.JogosViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*


class ListFragment : Fragment() {

    private lateinit var mJogosViewModel: JogosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        //RecyclerView
        val adapter = ListAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // UserViewModel
        mJogosViewModel = ViewModelProvider(this).get(JogosViewModel::class.java)
        mJogosViewModel.readAllData.observe(viewLifecycleOwner, Observer {  jogo ->
            adapter.setData(jogo)
        })

        view.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteAllJogos()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllJogos(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Sim") {_,_ ->
            mJogosViewModel.deleteAllJogos()
            Toast.makeText(requireContext(), "Tudo apagado com sucesso!", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("Não"){_,_ ->

        }
        builder.setTitle("Apagar tudo?")
        builder.setMessage("Tem a certeza que quer apagar tudo?")
        builder.create().show()
    }

}