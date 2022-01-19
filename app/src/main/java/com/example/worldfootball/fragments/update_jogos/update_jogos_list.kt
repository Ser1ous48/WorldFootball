package com.example.worldfootball.fragments.update_jogos

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.worldfootball.R
import com.example.worldfootball.api_worldfootball.dto.JogoDto
import com.example.worldfootball.api_worldfootball.jogos.JogosApi
import com.example.worldfootball.api_worldfootball.retrofit.ServiceBuilder
import com.example.worldfootball.utils.Utils.Companion.getToken
import com.example.worldfootball.utils.Utils.Companion.hideKeyboard
import com.example.worldfootball.utils.Utils.Companion.somethingWentWrong
import com.example.worldfootball.utils.Utils.Companion.unauthorized
import kotlinx.android.synthetic.main.fragment_update.view.*
import kotlinx.android.synthetic.main.fragment_update_jogos_list.*
import kotlinx.android.synthetic.main.fragment_update_jogos_list.view.*
import kotlinx.android.synthetic.main.fragment_user_login_add.*
import kotlinx.android.synthetic.main.fragment_user_login_add.EquipaCasa_list
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class update_jogos_list : Fragment() {


    private val args by navArgs<update_jogos_listArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update_jogos_list, container, false)

        setHasOptionsMenu(true)

        view.UpdateEquipaCasa_list_update.setText(args.currentJogosList.EquipaCasa)
        view.UpdateEquipaFora_list_update.setText(args.currentJogosList.EquipaFora)
        view.UpdateGolosMarcadosCasa_list_update.setText(args.currentJogosList.ResultadoCasa.toString())
        view.UpdateGolosMarcadosFora_list_update.setText(args.currentJogosList.ResultadoFora.toString())
        view.UpdateAmarelosCasa_list_update.setText(args.currentJogosList.AmarelosCasa.toString())
        view.UpdateAmarelosFora_list_update.setText(args.currentJogosList.AmarelosFora.toString())
        view.UpdateVermelhosCasa_list_update.setText(args.currentJogosList.VermelhosCasa.toString())
        view.UpdateVermelhosFora_list_update.setText(args.currentJogosList.VermelhosFora.toString())

        view.update_btn_list_update.setOnClickListener{
            updateJogos_login()

        }

        // Add menu
        setHasOptionsMenu(true)


        return view
    }


    private fun updateJogos_login() {
        if (TextUtils.isEmpty(UpdateEquipaCasa_list_update.text.toString())
            ||
            TextUtils.isEmpty(UpdateEquipaFora_list_update.text.toString())
            ||
            TextUtils.isEmpty(UpdateGolosMarcadosCasa_list_update.text.toString())
            ||
            TextUtils.isEmpty(UpdateGolosMarcadosFora_list_update.text.toString())
            ||
            TextUtils.isEmpty(UpdateAmarelosCasa_list_update.text.toString())
            ||
            TextUtils.isEmpty(UpdateAmarelosFora_list_update.text.toString())
            ||
            TextUtils.isEmpty(UpdateVermelhosCasa_list_update.text.toString())
            ||
            TextUtils.isEmpty(UpdateVermelhosFora_list_update.text.toString())

        ) {
            Toast.makeText(
                requireContext(),
                getString(R.string.preencher_equipas),
                Toast.LENGTH_LONG
            )
                .show()
        } else {
            val request = ServiceBuilder.buildService(JogosApi::class.java)
            val call = request.updateJogo(
                token = "Bearer ${getToken()}",
                id = args.currentJogosList.id,
                EquipaCasa = UpdateEquipaCasa_list_update.text.toString(),
                EquipaFora = UpdateEquipaFora_list_update.text.toString(),
                ResultadoCasa = UpdateGolosMarcadosCasa_list_update.text.toString(),
                ResultadoFora = UpdateGolosMarcadosFora_list_update.text.toString(),
                AmarelosCasa = UpdateAmarelosCasa_list_update.text.toString(),
                AmarelosFora = UpdateAmarelosFora_list_update.text.toString(),
                VermelhosCasa = UpdateVermelhosCasa_list_update.text.toString(),
                VermelhosFora = UpdateVermelhosFora_list_update.text.toString()
            )

            call.enqueue(object : Callback<JogoDto> {
                override fun onResponse(call: Call<JogoDto>, response: Response<JogoDto>) {
                    if (response.isSuccessful) {
                        val report: JogoDto = response.body()!!

                        if (report.status == "OK") {
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.successfull_updated_games),
                                Toast.LENGTH_LONG
                            ).show()
                            findNavController().navigate(R.id.action_update_jogos_list_to_jogosListFragment)//action_updateReportFragment_to_reportsListFragment
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
                                findNavController().navigate(R.id.action_update_jogos_list_to_userLoginFragment)//action_updateReportFragment_to_userLoginFragment
                            })
                        } else {
                            somethingWentWrong()
                        }
                    }
                }

                override fun onFailure(call: Call<JogoDto>, t: Throwable) {
                    somethingWentWrong()
                }
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteJogos_login()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteJogos_login() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton(getString(R.string.Sim)) { _, _ ->

            val request = ServiceBuilder.buildService(JogosApi::class.java)
            val call = request.deleteJogo(
                token = "Bearer ${getToken()}",
                id = args.currentJogosList.id
            )

            call.enqueue(object : Callback<JogoDto> {
                override fun onResponse(call: Call<JogoDto>, response: Response<JogoDto>) {
                    if (response.isSuccessful) {
                        val report: JogoDto = response.body()!!

                        if(report.status == "OK") {
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.successfull_deleted_games),
                                Toast.LENGTH_LONG
                            ).show()
                            findNavController().navigate(R.id.action_update_jogos_list_to_jogosListFragment)//action_updateReportFragment_to_reportsListFragment
                        }
                        else {
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

                        if(response.code() == 401){
                            unauthorized(navigatonHandlder = {
                                findNavController().navigate(R.id.action_update_jogos_list_to_userLoginFragment)//action_updateReportFragment_to_userLoginFragment
                            })
                        }
                        else {
                            somethingWentWrong()
                        }
                    }
                }

                override fun onFailure(call: Call<JogoDto>, t: Throwable) {
                    somethingWentWrong()
                }


            })
        }
        builder.setNegativeButton(getString(R.string.Não)) { _, _ -> }
        builder.setTitle(getString(R.string.delete_game))
        builder.setMessage(getString(R.string.question_delete_game))
        builder.create().show()
    }


}