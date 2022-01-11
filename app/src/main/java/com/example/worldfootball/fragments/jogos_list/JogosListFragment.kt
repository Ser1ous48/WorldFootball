package com.example.worldfootball.fragments.jogos_list

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.worldfootball.R
import com.example.worldfootball.api_worldfootball.jogos.JogosApi
import com.example.worldfootball.api_worldfootball.models.Jogos_Models
import com.example.worldfootball.api_worldfootball.retrofit.ServiceBuilder
import com.example.worldfootball.utils.Utils.Companion.getToken
import com.example.worldfootball.utils.Utils.Companion.getUserIdInSession
import com.example.worldfootball.utils.Utils.Companion.hideKeyboard
import com.example.worldfootball.utils.Utils.Companion.somethingWentWrong
import com.example.worldfootball.utils.Utils.Companion.unauthorized
import kotlinx.android.synthetic.main.fragment_jogos_list.*
import kotlinx.android.synthetic.main.fragment_jogos_list.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JogosListFragment : Fragment() {
    private  var  _view : View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_jogos_list, container, false)
        _view = view

        setHasOptionsMenu(true)

        getAndSetData(view)

        view.floatingActionButton_list.setOnClickListener() {
            //findNavController().navigate(R.id.action_reportsListFragment_to_addReportFragment)
        }

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_list_jogos, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        hideKeyboard()

        if (item.itemId == R.id.user_logout) {
            logout()
        }

        if(item.itemId == R.id.user_refresh){
            _view?.let { getAndSetData(it) }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun getAndSetData(view: View) {

        view.llProgressBarList.bringToFront()
        view.llProgressBarList.visibility = View.VISIBLE


        val adapter = JogosListAdapter(getUserIdInSession())

        val recyclerView = view.recyclerview_list
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val request = ServiceBuilder.buildService(JogosApi::class.java)
        val call = request.getJogo(token = "Bearer ${getToken()}")

        call.enqueue(object : Callback<List<Jogos_Models>> {
            override fun onResponse(call: Call<List<Jogos_Models>>, response: Response<List<Jogos_Models>>) {

                llProgressBarList.visibility = View.GONE

                if (response.isSuccessful) {
                    val reports: List<Jogos_Models> = response.body()!!
                    adapter.setData(reports)
                } else {
                    if (response.code() == 401) {
                        unauthorized(navigatonHandlder = {
                            findNavController().navigate(R.id.action_jogosListFragment_to_userLoginFragment)
                        })
                    } else {
                        somethingWentWrong()
                    }
                }
            }

            override fun onFailure(call: Call<List<Jogos_Models>>, t: Throwable) {
                llProgressBarList.visibility = View.GONE
                somethingWentWrong()
            }
        })
    }

    private fun logout() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton(getString(R.string.Sim)) { _, _ ->
            val preferences = requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE)
            preferences.edit().putString("token", null).apply()
            findNavController().navigate(R.id.action_jogosListFragment_to_userLoginFragment)
        }
        builder.setNegativeButton(getString(R.string.Não)) { _, _ -> }
        builder.setTitle(getString(R.string.Sair))
        builder.setMessage(getString((R.string.Sair_mensagem)))
        builder.create().show()
    }
}