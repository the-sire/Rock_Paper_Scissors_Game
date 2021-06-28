package com.thesire.rockpaperscissorsgame.ui.game

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.thesire.rockpaperscissorsgame.R
import com.thesire.rockpaperscissorsgame.data.preference.UserPreference
import com.thesire.rockpaperscissorsgame.databinding.ActivityMainBinding
import com.thesire.rockpaperscissorsgame.enum.PlayerPosition
import com.thesire.rockpaperscissorsgame.enum.PlayerType
import com.thesire.rockpaperscissorsgame.menu.GameMenuActivity
import com.thesire.rockpaperscissorsgame.ui.dialog.*
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val TAG = GameActivity::class.java.simpleName


    private var posPlayerOne: Int = 0
    private var posPlayerTwo: Int = 0
    private var isGameFinished: Boolean = false

    private var playMode: Int = PLAY_MODE_VS_COM
    private var playTurn: PlayerPosition = PlayerPosition.LEFT


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        getIntentExtras()
        setIdleState()
        setClickEvent()
    }


    private fun getIntentExtras(){
        playMode = intent.getIntExtra(ARG_EXTRA_PLAY_MODE, PLAY_MODE_VS_COM)
    }

    private fun showPlayerPosition(playerPosition: PlayerPosition,isVisible: Boolean){
        if (playerPosition == PlayerPosition.LEFT){
            binding.flPlayerRockLeft.visibility = if (isVisible) View.VISIBLE else View.GONE
            binding.flPlayerPaperLeft.visibility = if (isVisible) View.VISIBLE else View.GONE
            binding.flPlayerScissorLeft.visibility = if (isVisible) View.VISIBLE else View.GONE
        }else{
            binding.flPlayerRockRight.visibility = if (isVisible) View.VISIBLE else View.GONE
            binding.flPlayerPaperRight.visibility = if (isVisible) View.VISIBLE else View.GONE
            binding.flPlayerScissorRight.visibility = if (isVisible) View.VISIBLE else View.GONE
        }
    }


    private fun setClickEvent() {
        if (playMode == PLAY_MODE_VS_COM) {
            binding.icReturnToMenu.setImageResource(R.drawable.ic_return_to_menu)
            binding.icVersus.setImageResource(R.drawable.ic_versus_icon)
            binding.tvPlayerLeft.text = UserPreference(this).userName
            binding.tvPlayerRight.text = getString(R.string.text_p2_name_vs_com)
            binding.ivPlayerRockLeft.setOnClickListener {
                if (!isGameFinished) {
                    Toast.makeText(
                        this,
                        (UserPreference(this).userName)!!.uppercase() + " CHOOSES ROCK",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.d(TAG, "Player Chooses Rock")
                    posPlayerOne = 0
                    playerPick()
                    playerTwoPick()
                    startGame()
                    isGameFinished = true
                }
            }

            binding.ivPlayerPaperLeft.setOnClickListener {
                if (!isGameFinished) {
                    Toast.makeText(
                        this,
                        (UserPreference(this).userName)!!.uppercase() + " CHOOSES PAPER",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.d(TAG, "Player Chooses Paper")
                    posPlayerOne = 1
                    playerPick()
                    playerTwoPick()
                    startGame()
                    isGameFinished = true
                }
            }

            binding.ivPlayerScissorLeft.setOnClickListener {
                if (!isGameFinished) {
                    Toast.makeText(
                        this,
                        (UserPreference(this).userName)!!.uppercase() + " CHOOSES SCISSORS",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.d(TAG, "Player Chooses Scissors")
                    posPlayerOne = 2
                    playerPick()
                    playerTwoPick()
                    startGame()
                    isGameFinished = true
                }
            }
        }

        if (playMode == PLAY_MODE_VS_PLAYER) {
            binding.icReturnToMenu.setImageResource(R.drawable.ic_return_to_menu)
            binding.icVersus.setImageResource(R.drawable.ic_versus_icon)
            binding.tvPlayerLeft.text = UserPreference(this).userName
            binding.tvPlayerRight.text = getString(R.string.text_p2_name_vs_player)
            if (playTurn == PlayerPosition.LEFT) {
                showPlayerPosition(PlayerPosition.LEFT, true)
                showPlayerPosition(PlayerPosition.RIGHT, false)
                binding.ivPlayerRockLeft.setOnClickListener {
                    if (!isGameFinished) {
                        Toast.makeText(
                            this,
                            (UserPreference(this).userName)!!.uppercase() + "'S CHOICE HAS BEEN LOCKED",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.d(TAG, UserPreference(this).userName + " Chooses Rock")
                        posPlayerOne = 0
                        playerPick()
                        playTurn = PlayerPosition.RIGHT
                        showPlayerPosition(PlayerPosition.LEFT, false)
                        showPlayerPosition(PlayerPosition.RIGHT, true)
                        binding.ivPlayerRockRight.setOnClickListener {
                            if (!isGameFinished) {
                                Toast.makeText(this, "PLAYER 2 CHOOSES ROCK", Toast.LENGTH_SHORT)
                                    .show()
                                Log.d(TAG, "Player 2 Chooses Rock")
                                posPlayerTwo = 0
                                playerTwoPick()
                                startGame()
                                isGameFinished = true

                            }
                        }
                        binding.ivPlayerPaperRight.setOnClickListener {
                            if (!isGameFinished) {
                                Toast.makeText(this, "PLAYER 2 CHOOSES PAPER", Toast.LENGTH_SHORT)
                                    .show()
                                Log.d(TAG, "Player 2 Chooses Paper")
                                posPlayerTwo = 1
                                playerTwoPick()
                                startGame()
                                isGameFinished = true
                            }
                        }
                        binding.ivPlayerScissorsRight.setOnClickListener {
                            if (!isGameFinished) {
                                Toast.makeText(
                                    this,
                                    "PLAYER 2 CHOOSES SCISSORS",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                                Log.d(TAG, "Player 2 Chooses Scissors")
                                posPlayerTwo = 2
                                playerTwoPick()
                                startGame()
                                isGameFinished = true
                            }
                        }

                    }
                }

                binding.ivPlayerPaperLeft.setOnClickListener {
                    if (!isGameFinished) {
                        Toast.makeText(
                            this,
                            (UserPreference(this).userName)!!.uppercase() + "'S CHOICE HAS BEEN LOCKED",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.d(TAG, UserPreference(this).userName + " Chooses Paper")
                        posPlayerOne = 1
                        playerPick()
                        playTurn = PlayerPosition.RIGHT
                        showPlayerPosition(PlayerPosition.LEFT, false)
                        showPlayerPosition(PlayerPosition.RIGHT, true)
                        binding.ivPlayerRockRight.setOnClickListener {
                            if (!isGameFinished) {
                                Toast.makeText(this, "PLAYER 2 CHOOSES ROCK", Toast.LENGTH_SHORT)
                                    .show()
                                Log.d(TAG, "Player 2 Chooses Rock")
                                posPlayerTwo = 0
                                playerTwoPick()
                                startGame()
                                isGameFinished = true
                            }
                        }
                        binding.ivPlayerPaperRight.setOnClickListener {
                            if (!isGameFinished) {
                                Toast.makeText(this, "PLAYER 2 CHOOSES PAPER", Toast.LENGTH_SHORT)
                                    .show()
                                Log.d(TAG, "Player 2 Chooses Paper")
                                posPlayerTwo = 1
                                playerTwoPick()
                                startGame()
                                isGameFinished = true
                            }
                        }
                        binding.ivPlayerScissorsRight.setOnClickListener {
                            if (!isGameFinished) {
                                Toast.makeText(
                                    this,
                                    "PLAYER 2 CHOOSES SCISSORS",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                                Log.d(TAG, "Player 2 Chooses Scissors")
                                posPlayerTwo = 2
                                playerTwoPick()
                                startGame()
                                isGameFinished = true
                            }
                        }

                    }
                }

                binding.ivPlayerScissorLeft.setOnClickListener {
                    if (!isGameFinished) {
                        Toast.makeText(
                            this,
                            (UserPreference(this).userName)!!.uppercase() + "'S CHOICE HAS BEEN LOCKED",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.d(TAG, UserPreference(this).userName + " Chooses Scissors")
                        posPlayerOne = 2
                        playerPick()
                        playTurn = PlayerPosition.RIGHT
                        showPlayerPosition(PlayerPosition.LEFT, false)
                        showPlayerPosition(PlayerPosition.RIGHT, true)
                        binding.ivPlayerRockRight.setOnClickListener {
                            if (!isGameFinished) {
                                Toast.makeText(this, "PLAYER 2 CHOOSES ROCK", Toast.LENGTH_SHORT)
                                    .show()
                                Log.d(TAG, "Player 2 Chooses Rock")
                                posPlayerTwo = 0
                                playerTwoPick()
                                startGame()
                                isGameFinished = true
                            }
                        }
                        binding.ivPlayerPaperRight.setOnClickListener {
                            if (!isGameFinished) {
                                Toast.makeText(this, "PLAYER 2 CHOOSES PAPER", Toast.LENGTH_SHORT)
                                    .show()
                                Log.d(TAG, "Player 2 Chooses Paper")
                                posPlayerTwo = 1
                                playerTwoPick()
                                startGame()
                                isGameFinished = true
                            }
                        }
                        binding.ivPlayerScissorsRight.setOnClickListener {
                            if (!isGameFinished) {
                                Toast.makeText(
                                    this,
                                    "PLAYER 2 CHOOSES SCISSORS",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                                Log.d(TAG, "Player 2 Chooses Scissors")
                                posPlayerTwo = 2
                                playerTwoPick()
                                startGame()
                                isGameFinished = true
                            }
                        }

                    }
                }
            } else {
                setIdleState()

            }
        }
        binding.icRestart.setOnClickListener {
            Log.d(TAG, "Time To Restart The Game")
            isGameFinished = true
            restartGame()

            }

        binding.icReturnToMenu.setOnClickListener {
            Log.d(TAG, "Return To Game Menu")
            isGameFinished = true
            returnToMenu()
        }

        }



    private fun returnToMenu(){
        val intent = Intent(this,GameMenuActivity::class.java)
        this.startActivity(intent)
    }



    @SuppressLint("ResourceType")
    private fun startGame() {
        showPlayerPosition(PlayerPosition.LEFT,true)
        showPlayerPosition(PlayerPosition.RIGHT,true)

        if (playMode == PLAY_MODE_VS_COM) {
            if (posPlayerOne == posPlayerTwo) {
                ResultIsDrawVsComDialogFragment().show(supportFragmentManager, null)
            } else if ((posPlayerOne.plus(1)).mod(3) == posPlayerTwo) {
                ComputerWinDialogFragment().show(supportFragmentManager,null)
            } else {
                PlayerOneWinVsComDialogFragment().show(supportFragmentManager, null)
            }
        }else{
            playMode = PLAY_MODE_VS_PLAYER
            if (posPlayerOne == posPlayerTwo) {
                ResultIsDrawDialogVsP2Fragment().show(supportFragmentManager, null)
            } else if ((posPlayerOne.plus(1)).mod(3) == posPlayerTwo) {
                PlayerTwoWinDialogFragment().show(supportFragmentManager, null)
            } else {
                PlayerOneWinVsP2DialogFragment().show(supportFragmentManager, null)
            }
        }

        isGameFinished = true
        binding.icRestart.setImageResource(R.drawable.ic_restart_icon)
        binding.tvRestartGame.text = getString(R.string.text_restart_game)

    }

    private fun restartGame() {
        isGameFinished = false
        posPlayerOne = -1
        posPlayerTwo = -1
        binding.icVersus.setImageResource(R.drawable.ic_versus_icon)
        binding.icRestart.setImageResource(0)
        binding.tvRestartGame.text = ""
        setIdleState()
    }

    private fun playerTwoPick() {
        if (playMode == PLAY_MODE_VS_COM) {
            posPlayerTwo = Random.nextInt(0, 3)
            Toast.makeText(this, "COMPUTER CHOOSES ${PlayerType.fromInt(posPlayerTwo)}", Toast.LENGTH_SHORT).show()
        }
            when (PlayerType.fromInt(posPlayerTwo)) {
                PlayerType.ROCK -> {
                    binding.ivPlayerRockRight.setBackgroundResource(R.drawable.bg_action_button)
                    binding.ivPlayerPaperRight.setBackgroundResource(0)
                    binding.ivPlayerScissorsRight.setBackgroundResource(0)

                }

                PlayerType.PAPER -> {
                    binding.ivPlayerRockRight.setBackgroundResource(0)
                    binding.ivPlayerPaperRight.setBackgroundResource(R.drawable.bg_action_button)
                    binding.ivPlayerScissorsRight.setBackgroundResource(0)
                }

                PlayerType.SCISSORS -> {
                    binding.ivPlayerRockRight.setBackgroundResource(0)
                    binding.ivPlayerPaperRight.setBackgroundResource(0)
                    binding.ivPlayerScissorsRight.setBackgroundResource(R.drawable.bg_action_button)

                }
                else -> setIdleState()
            }
            }

    private fun playerPick() {
        when (PlayerType.fromInt(posPlayerOne)) {
            PlayerType.ROCK -> {
                binding.ivPlayerRockLeft.setBackgroundResource(R.drawable.bg_action_button)
                binding.ivPlayerPaperLeft.setBackgroundResource(0)
                binding.ivPlayerScissorLeft.setBackgroundResource(0)

            }

            PlayerType.PAPER -> {
                binding.ivPlayerRockLeft.setBackgroundResource(0)
                binding.ivPlayerPaperLeft.setBackgroundResource(R.drawable.bg_action_button)
                binding.ivPlayerScissorLeft.setBackgroundResource(0)

            }

            PlayerType.SCISSORS -> {
                binding.ivPlayerRockLeft.setBackgroundResource(0)
                binding.ivPlayerPaperLeft.setBackgroundResource(0)
                binding.ivPlayerScissorLeft.setBackgroundResource(R.drawable.bg_action_button)

            }
            else -> setIdleState()
        }
    }


    private fun setIdleState() {
        binding.icReturnToMenu.setImageResource(R.drawable.ic_return_to_menu)
        binding.icVersus.setImageResource(R.drawable.ic_versus_icon)
        binding.ivPlayerRockLeft.setBackgroundResource(0)
        binding.ivPlayerPaperLeft.setBackgroundResource(0)
        binding.ivPlayerScissorLeft.setBackgroundResource(0)
        binding.ivPlayerRockRight.setBackgroundResource(0)
        binding.ivPlayerPaperRight.setBackgroundResource(0)
        binding.ivPlayerScissorsRight.setBackgroundResource(0)
        if (playMode == PLAY_MODE_VS_PLAYER) {
            showPlayerPosition(PlayerPosition.LEFT, true)
            showPlayerPosition(PlayerPosition.RIGHT, false)
            playTurn = PlayerPosition.LEFT
        }
    }


    companion object {
        const val ARG_EXTRA_PLAY_MODE = "ARG_EXTRA_PLAY_MODE"
        const val PLAY_MODE_VS_COM = 0
        const val PLAY_MODE_VS_PLAYER = 1

        fun startTheActivity(context: Context?, playMode: Int){
            val intent = Intent(context,GameActivity::class.java)
            intent.putExtra(ARG_EXTRA_PLAY_MODE,playMode)
            context?.startActivity(intent)
        }
    }


}