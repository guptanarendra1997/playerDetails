package com.example.playerdetails.model

data class TeamDetails(
    val teamId: Int,
    val teamName: String,
    val playerList: List<PlayerData>
)