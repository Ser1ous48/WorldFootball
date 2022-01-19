package com.example.worldfootball.fragments.jogos_list

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.worldfootball.R
import com.example.worldfootball.api_worldfootball.models.Jogos_Models
import kotlinx.android.synthetic.main.custom_row_jogos_list.view.*

class JogosListAdapter(userIdInSession: String?) : RecyclerView.Adapter<JogosListAdapter.MyViewHolder>() {
    private var jogos_list = emptyList<Jogos_Models>()
    private  val _userIdInSession = userIdInSession
    private  var  _ctx : Context? = null

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        _ctx = parent.context

        return JogosListAdapter.MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.custom_row_jogos_list,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = jogos_list[position]
        holder.itemView.id_text_list.text = currentItem.id.toString()
        holder.itemView.username_list.text = currentItem.user_name
        holder.itemView.EquipaCasa_list.text = currentItem.EquipaCasa
        holder.itemView.EquipaFora_list.text = currentItem.EquipaFora
        holder.itemView.GolosMarcadosCasa_list.text = currentItem.ResultadoCasa?.toString()
        holder.itemView.GolosMarcadosFora_list.text = currentItem.ResultadoFora?.toString()
        holder.itemView.AmarelosCasa_list.text = currentItem.AmarelosCasa?.toString()
        holder.itemView.AmarelosFora_list.text = currentItem.AmarelosFora?.toString()
        holder.itemView.VermelhosCasa_list.text = currentItem.VermelhosCasa?.toString()
        holder.itemView.VermelhosFora_list.text = currentItem.VermelhosFora?.toString()

        if(position%2 == 0){
            holder.itemView.setBackgroundColor(Color.parseColor("#d6d4e0"))
        }
        else {
            holder.itemView.setBackgroundColor(Color.parseColor("#b8a9c9"))
        }

        /*holder.itemView.rowLayout_jogos_list.setOnClickListener {
            if(_userIdInSession == currentItem.users_id.toString()){
                val action =
                    JogosListFragmentDirections.actionReportsListFragmentToUpdateReportFragment(
                        currentItem
                    )
                holder.itemView.findNavController().navigate(action)
            }
            else {
                Toast.makeText(_ctx,R.string.ony_edit_your_reports, Toast.LENGTH_LONG).show()
            }
        }*/

        holder.itemView.rowLayout_list.setOnClickListener {
            if(_userIdInSession == currentItem.users_id.toString()){
                val action =
                    JogosListFragmentDirections.actionJogosListFragmentToUpdateJogosList(//actionReportsListFragmentToUpdateReportFragment
                        currentItem
                    )
                holder.itemView.findNavController().navigate(action)
            }
            else {
                Toast.makeText(_ctx,R.string.ony_edit_your_games, Toast.LENGTH_LONG).show()
            }
        }


    }

    override fun getItemCount(): Int {
        return jogos_list.size
    }

    fun setData(jogos_List: List<Jogos_Models>){
        this.jogos_list = jogos_List
        notifyDataSetChanged()
    }
}