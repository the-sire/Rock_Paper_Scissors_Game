package com.thesire.rockpaperscissorsgame.enum

enum class PlayerType(val value: Int) {

    IDLE(-1),
    ROCK(0),
    PAPER(1),
    SCISSORS(2);

    companion object {
        fun fromInt(value: Int) = values().first() { it.value == value }
    }


}