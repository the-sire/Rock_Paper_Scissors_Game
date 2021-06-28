package com.thesire.rockpaperscissorsgame.ui.intro

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.thesire.rockpaperscissorsgame.R
import com.thesire.rockpaperscissorsgame.data.preference.UserPreference
import com.thesire.rockpaperscissorsgame.databinding.FragmentPlayerNameFormBinding
import com.thesire.rockpaperscissorsgame.menu.GameMenuActivity

class PlayerNameFormFragment : Fragment() {
    private lateinit var binding: FragmentPlayerNameFormBinding
    private lateinit var userPreference: UserPreference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayerNameFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefilledName()
    }

    private fun prefilledName() {
        context?.let {
            userPreference = UserPreference(it)
            binding.etPlayerName.setText(userPreference.userName.orEmpty())
        }
    }

    private fun isFormFilled(): Boolean {
        val name = binding.etPlayerName.text.toString()
        var isFormValid = true

        if (name.isEmpty()) {
            isFormValid = false
            Toast.makeText(
                context,
                getString(R.string.text_error_toast_empty_name),
                Toast.LENGTH_SHORT
            ).show()
        }
        return isFormValid
    }

    fun navigateToGameMenu() {
        if (isFormFilled()) {
            userPreference.userName = binding.etPlayerName.text.toString()
            context?.startActivity(Intent(context,GameMenuActivity::class.java))
        }
    }

}