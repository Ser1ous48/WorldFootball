package com.example.worldfootball.fragments.jogos

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.worldfootball.R
import com.example.worldfootball.api_worldfootball.dto.JogoDto
import com.example.worldfootball.api_worldfootball.jogos.JogosApi
import com.example.worldfootball.api_worldfootball.retrofit.ServiceBuilder
import com.example.worldfootball.utils.Utils.Companion.getToken
import com.example.worldfootball.utils.Utils.Companion.getUserIdInSession
import com.example.worldfootball.utils.Utils.Companion.hideKeyboard
import com.example.worldfootball.utils.Utils.Companion.somethingWentWrong
import com.example.worldfootball.utils.Utils.Companion.unauthorized
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_user_login.*
import kotlinx.android.synthetic.main.fragment_user_login_add.*
import kotlinx.android.synthetic.main.fragment_user_login_add.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class userLoginAddFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_user_login_add, container, false)

        setHasOptionsMenu(true)

        view.add_btn_list.setOnClickListener{
            addJogo()
        }


        return view
    }

    /*override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        //inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        hideKeyboard()

        if (item.itemId == R.id.action_settings) {
            addJogo()
        }

        return super.onOptionsItemSelected(item)
    }
*/
    private fun addJogo() {
        if (TextUtils.isEmpty(EquipaCasa_list.text.toString())
            ||
            TextUtils.isEmpty(EquipaFora_list.text.toString())
            ||
            TextUtils.isEmpty(GolosMarcadosCasa_list.text.toString())
            ||
            TextUtils.isEmpty(GolosMarcadosFora_list.text.toString())
            ||
            TextUtils.isEmpty(AmarelosCasa_list.text.toString())
            ||
            TextUtils.isEmpty(AmarelosFora_list.text.toString())
            ||
            TextUtils.isEmpty(VermelhosCasa_list.text.toString())
            ||
            TextUtils.isEmpty(VermelhosFora_list.text.toString())

        ) {
            Toast.makeText(
                requireContext(),
                getString(R.string.preencher_equipas),
                Toast.LENGTH_LONG
            )
                .show()
        } else {
            llProgressBar.bringToFront()
            llProgressBar.visibility = View.VISIBLE

            val request = ServiceBuilder.buildService(JogosApi::class.java)
            val call = request.createJogo(
                token = "Bearer ${getToken()}",
                users_id = getUserIdInSession(),
                EquipaCasa = EquipaCasa_list.text.toString(),
                EquipaFora = EquipaFora_list.text.toString(),
                ResultadoCasa = GolosMarcadosCasa_list.text.toString(),
                ResultadoFora = GolosMarcadosFora_list.text.toString(),
                AmarelosCasa = AmarelosCasa_list.text.toString(),
                AmarelosFora = AmarelosFora_list.text.toString(),
                VermelhosCasa = VermelhosCasa_list.text.toString(),
                VermelhosFora = VermelhosFora_list.text.toString()
            )

            call.enqueue(object : Callback<JogoDto> {
                override fun onResponse(call: Call<JogoDto>, response: Response<JogoDto>) {
                    llProgressBar.visibility = View.GONE

                    if (response.isSuccessful) {
                        val report: JogoDto = response.body()!!

                        if (report.status == "OK") {
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.JogoAdicionado),
                                Toast.LENGTH_LONG
                            ).show()
                            findNavController().navigate(R.id.action_userLoginAddFragment_to_jogosListFragment)//action_addReportFragment_to_reportsListFragment
                        } else {
                            Toast.makeText(
                                requireContext(), getString(
                                    resources.getIdentifier(
                                        report.message, "string",
                                        context?.packageName
                                    )
                                ), Toast.LENGTH_LONG
                            ).show()
                        }
                    } else {
                        if (response.code() == 401) {
                            unauthorized(navigatonHandlder = {
                                findNavController().navigate(R.id.action_userLoginAddFragment_to_userLoginFragment)//action_addReportFragment_to_userLoginFragment
                            })
                        } else {
                            somethingWentWrong()
                        }
                    }
                }

                override fun onFailure(call: Call<JogoDto>, t: Throwable) {
                    llProgressBar.visibility = View.GONE
                    somethingWentWrong()
                }
            })
        }
    }
}