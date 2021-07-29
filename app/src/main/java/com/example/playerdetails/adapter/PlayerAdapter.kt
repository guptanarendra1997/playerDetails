package com.example.playerdetails.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.playerdetails.R
import com.example.playerdetails.model.PlayerData
import com.example.playerdetails.viewholder.PlayerViewHolder
import kotlinx.android.synthetic.main.item_player.view.*

class PlayerAdapter(
    private val playerList: List<PlayerData>
) : RecyclerView.Adapter<PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem: View = layoutInflater.inflate(R.layout.item_player, parent, false)
        return PlayerViewHolder(listItem)
    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val single: PlayerData = playerList.get(position)
        holder.itemView.playerName.text = single.playerName
        single.isCaptain?.let {
            if (it) {
                holder.itemView.playerType.visibility = View.VISIBLE
                holder.itemView.playerType.text = "C"
            }
        }
        single.IsKeeper?.let {
            if (it) {
                holder.itemView.playerType.visibility = View.VISIBLE
                holder.itemView.playerType.text = "WK"
            }
        }
    }
}