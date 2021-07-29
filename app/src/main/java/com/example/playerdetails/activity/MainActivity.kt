package com.example.playerdetails.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.playerdetails.R
import com.example.playerdetails.adapter.ViewPagerAdapter
import com.example.playerdetails.fragment.TeamDetailsFragment
import com.example.playerdetails.model.PlayerData
import com.example.playerdetails.model.TeamDetails
import com.example.playerdetails.network.ApiClient
import com.example.playerdetails.network.ApiService
import com.example.playerdetails.viewmodel.PlayerDetailsViewModel
import com.google.android.material.tabs.TabLayout
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    private lateinit var apiInterface: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiInterface = ApiClient().getClient()?.create(ApiService::class.java)!!

        val dashboardViewModel = ViewModelProviders.of(this)
            .get(PlayerDetailsViewModel::class.java)

        dashboardViewModel.getPlayer(apiInterface).observe(this,
            Observer {
                setData(it)
            })
    }


    private fun setData(it: JsonObject) {
        val teamData: JsonObject = it.getAsJsonObject("Teams")
        val teamJson = JSONObject(teamData.toString())
        val teamIterator = teamJson.keys()
        val teamList = mutableListOf<TeamDetails>()

        while (teamIterator.hasNext()) {
            val teamId = teamIterator.next()
            val value = JSONObject(teamJson.optString(teamId))
            val teamName = value.getString("Name_Full")
            val playerListObject = value.getString("Players")

            val jsonPlayer = JSONObject(playerListObject)
            val playerIterator = jsonPlayer.keys()
            val playerList = mutableListOf<PlayerData>()

            while (playerIterator.hasNext()) {
                val playerId = playerIterator.next()
                val playerObject = JSONObject(jsonPlayer.optString(playerId))
                val playerName = playerObject.getString("Name_Full")

                var icCaptain: Boolean
                var IsKeeper: Boolean
                icCaptain = checkIfKeyExists(playerObject.toString(), "Iscaptain")
                IsKeeper = checkIfKeyExists(playerObject.toString(), "Iskeeper")

                playerList.add(
                    PlayerData(
                        playerId.toInt(),
                        playerName,
                        icCaptain,
                        IsKeeper
                    )
                )
            }
            teamList.add(TeamDetails(teamId.toInt(), teamName, playerList))
        }
        setAdapter(teamList)
    }

    private fun setAdapter(teamList: MutableList<TeamDetails>) {

        tabLayout.setupWithViewPager(viewpager)
        viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewpager.offscreenPageLimit = teamList.size

        for (i in 0 until teamList.size) {
            viewPagerAdapter.addFragment(
                TeamDetailsFragment(teamList[i].playerList),
                teamList[i].teamName
            )
        }
        viewpager.adapter = viewPagerAdapter
    }

    private fun checkIfKeyExists(response: String, key: String): Boolean {
        val parser = JsonParser()
        val jsonObject = parser.parse(response).asJsonObject
        return jsonObject.has(key)
    }

}