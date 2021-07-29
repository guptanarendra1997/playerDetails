package com.example.playerdetails.model

data class PlayerData(
    val playerId: Int,
    val playerName: String,
    val isCaptain: Boolean? = null,
    val IsKeeper: Boolean? = null
)
