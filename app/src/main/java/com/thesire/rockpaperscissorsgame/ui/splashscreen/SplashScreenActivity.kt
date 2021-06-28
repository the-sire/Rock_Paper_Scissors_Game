package com.thesire.rockpaperscissorsgame.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.thesire.rockpaperscissorsgame.R
import com.thesire.rockpaperscissorsgame.ui.intro.AppIntroActivity

class SplashScreenActivity : AppCompatActivity() {
    private var timer : CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()
        setEventSplash()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(timer != null){
            timer?.cancel()
            timer = null
        }
    }

    private fun setEventSplash(){
        timer = object : CountDownTimer(3000,1000) {
            override fun onTick(millisUntilFinished: Long) { }
            override fun onFinish() {
                    val intent = Intent(this@SplashScreenActivity, AppIntroActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
            }
        }
        timer?.start()
    }

}