package com.thesire.rockpaperscissorsgame.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.thesire.rockpaperscissorsgame.R
import com.thesire.rockpaperscissorsgame.data.preference.UserPreference
import com.thesire.rockpaperscissorsgame.databinding.ActivityGameMenuBinding
import com.thesire.rockpaperscissorsgame.ui.game.GameActivity

class GameMenuActivity : AppCompatActivity() {
    private lateinit var binding : ActivityGameMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameMenuBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)
        setNameOnTitlePage()
        setClickListeners()
        Snackbar.make(binding.root, "WELCOME " + (UserPreference(this).userName)!!.uppercase(), Snackbar.LENGTH_LONG).show()
    }



    private fun setNameOnTitlePage(){
        binding.tvMenuTitle.text = String.format(getString(R.string.text_title_menu,UserPreference(this).userName))
    }

    private fun setClickListeners(){
        binding.ivVsCom.setOnClickListener {
            GameActivity.startTheActivity(this,GameActivity.PLAY_MODE_VS_COM)
            Toast.makeText(this, "VS COM WAS CHOSEN", Toast.LENGTH_SHORT).show()
        }

        binding.ivVsPlayer.setOnClickListener {
            GameActivity.startTheActivity(this,GameActivity.PLAY_MODE_VS_PLAYER)
            Toast.makeText(this, "VS PLAYER 2 WAS CHOSEN", Toast.LENGTH_SHORT).show()
        }
    }
}