package com.thesire.rockpaperscissorsgame.ui.intro

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro2
import com.github.appintro.AppIntroCustomLayoutFragment
import com.github.appintro.AppIntroFragment
import com.github.appintro.AppIntroPageTransformerType
import com.thesire.rockpaperscissorsgame.R

class AppIntroActivity : AppIntro2() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        isSkipButtonEnabled = false
        setTransformer(AppIntroPageTransformerType.Zoom)
        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.layout_intro_page_1))
        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.layout_intro_page_2))
        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.layout_intro_page_3))

        addSlide(PlayerNameFormFragment())
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        finish()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        if (currentFragment is PlayerNameFormFragment) {
            currentFragment.navigateToGameMenu()
        }
    }

}