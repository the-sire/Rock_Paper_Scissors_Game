package com.thesire.rockpaperscissorsgame.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.thesire.rockpaperscissorsgame.R
import com.thesire.rockpaperscissorsgame.databinding.ActivityMainBinding
import com.thesire.rockpaperscissorsgame.enum.PlayerType
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val TAG = MainActivity::class.java.simpleName

    private var playerChoose: Int = 0
    private var compChoose: Int = 0
    private var isGameFinished: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setClickEvent()
    }


    private fun setClickEvent() {
        binding.ivPlayerRock.setOnClickListener {
            Log.d(TAG, "Player Chooses Rock")
            playerChoose = 0
            playerPick()
            computerPick()
            startGame()
        }

        binding.ivPlayerPaper.setOnClickListener {
            Log.d(TAG, "Player Chooses Paper")
            playerChoose = 1
            playerPick()
            computerPick()
            startGame()
        }

        binding.ivPlayerScissor.setOnClickListener {
            Log.d(TAG, "Player Chooses Scissors")
            playerChoose = 2
            playerPick()
            computerPick()
            startGame()
        }
        binding.icRestart.setOnClickListener {
            Log.d(TAG, "Time To Restart The Game")
            if (isGameFinished) {
                restartGame()
            } else {
                startGame()
            }
        }
    }


    @SuppressLint("ResourceType")
    private fun startGame() {
        if (playerChoose == compChoose) {
            binding.icVersus.setImageResource(R.drawable.ic_rs_draw)
        } else if ((playerChoose.plus(1)).mod(3) == compChoose) {
            binding.icVersus.setImageResource(R.drawable.ic_rs_lose)
        } else {
            binding.icVersus.setImageResource(R.drawable.ic_rs_win)
        }

        isGameFinished = true
        binding.icRestart.setImageResource(R.drawable.ic_restart_icon)

    }

    private fun restartGame() {
        isGameFinished = false
        playerChoose = -1
        compChoose = -1
        binding.icVersus.setImageResource(R.drawable.ic_versus_icon)
        binding.icRestart.setImageResource(0)
        setIdleState()
        setClickEvent()
    }

    private fun computerPick() {
        compChoose = Random.nextInt(0, 3)
        when (PlayerType.fromInt(compChoose)) {
            PlayerType.ROCK -> {
                binding.ivCompRock.setBackgroundResource(R.drawable.bg_action_button)
                binding.ivCompPaper.setBackgroundResource(0)
                binding.ivCompScissor.setBackgroundResource(0)

            }

            PlayerType.PAPER -> {
                binding.ivCompRock.setBackgroundResource(0)
                binding.ivCompPaper.setBackgroundResource(R.drawable.bg_action_button)
                binding.ivCompScissor.setBackgroundResource(0)
            }

            PlayerType.SCISSORS -> {
                binding.ivCompRock.setBackgroundResource(0)
                binding.ivCompPaper.setBackgroundResource(0)
                binding.ivCompScissor.setBackgroundResource(R.drawable.bg_action_button)

            }
        }
    }

    private fun playerPick() {
        when (PlayerType.fromInt(playerChoose)) {
            PlayerType.ROCK -> {
                binding.ivPlayerRock.setBackgroundResource(R.drawable.bg_action_button)
                binding.ivPlayerPaper.setBackgroundResource(0)
                binding.ivPlayerScissor.setBackgroundResource(0)

            }

            PlayerType.PAPER -> {
                binding.ivPlayerRock.setBackgroundResource(0)
                binding.ivPlayerPaper.setBackgroundResource(R.drawable.bg_action_button)
                binding.ivPlayerScissor.setBackgroundResource(0)

            }

            PlayerType.SCISSORS -> {
                binding.ivPlayerRock.setBackgroundResource(0)
                binding.ivPlayerPaper.setBackgroundResource(0)
                binding.ivPlayerScissor.setBackgroundResource(R.drawable.bg_action_button)

            }
        }
    }


    private fun setIdleState() {
        binding.ivPlayerRock.setBackgroundResource(0)
        binding.ivPlayerPaper.setBackgroundResource(0)
        binding.ivPlayerScissor.setBackgroundResource(0)
        binding.ivCompRock.setBackgroundResource(0)
        binding.ivCompPaper.setBackgroundResource(0)
        binding.ivCompScissor.setBackgroundResource(0)

    }

}