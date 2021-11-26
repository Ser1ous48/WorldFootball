package com.example.worldfootball.fragments.list

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.worldfootball.R
import com.example.worldfootball.data.jogos
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var jogosList = emptyList<jogos>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun getItemCount(): Int {
        return jogosList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = jogosList[position]
        holder.itemView.id_text.text = currentItem.id.toString()
        holder.itemView.EquipaCasa.text = currentItem.EquipaCasa
        holder.itemView.EquipaFora.text = currentItem.EquipaFora
        holder.itemView.GolosMarcadosCasa.text = currentItem.ResultadoCasa.toString()
        holder.itemView.GolosMarcadosFora.text = currentItem.ResultadoFora.toString()
        holder.itemView.AmarelosCasa.text = currentItem.AmarelosCasa.toString()
        holder.itemView.AmarelosFora.text = currentItem.AmarelosFora.toString()
        holder.itemView.VermelhosCasa.text = currentItem.VermelhosCasa.toString()
        holder.itemView.VermelhosFora.text = currentItem.VermelhosFora.toString()

    }

    fun setData(jogos: List<jogos>){
        this.jogosList = jogos
        notifyDataSetChanged()
    }

}