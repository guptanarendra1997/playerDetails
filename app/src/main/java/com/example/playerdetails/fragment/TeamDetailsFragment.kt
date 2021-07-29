package com.example.playerdetails.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.playerdetails.R
import com.example.playerdetails.adapter.PlayerAdapter
import com.example.playerdetails.model.PlayerData
import kotlinx.android.synthetic.main.fragment_team.view.*

class TeamDetailsFragment(var playerList: List<PlayerData>) : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_team, container, false)
        setData(view)
        return view
    }

    private fun setData(view: View) {
        view.playerList.layoutManager = LinearLayoutManager(context)
        view.playerList.adapter = PlayerAdapter(playerList)
    }
}