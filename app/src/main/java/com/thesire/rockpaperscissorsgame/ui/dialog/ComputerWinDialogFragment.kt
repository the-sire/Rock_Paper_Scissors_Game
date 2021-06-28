package com.thesire.rockpaperscissorsgame.ui.dialog

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.thesire.rockpaperscissorsgame.R
import com.thesire.rockpaperscissorsgame.databinding.FragmentComputerWinDialogBinding
import com.thesire.rockpaperscissorsgame.menu.GameMenuActivity
import com.thesire.rockpaperscissorsgame.ui.game.GameActivity


class ComputerWinDialogFragment : DialogFragment() {

    private lateinit var binding : FragmentComputerWinDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentComputerWinDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.bg_dialog_rounded_white)
        binding.btnPlayAgainCwDialog.setOnClickListener {
            GameActivity.startTheActivity(context,GameActivity.PLAY_MODE_VS_COM)
            Toast.makeText(context, "PLAY AGAIN!", Toast.LENGTH_SHORT).show()
        }
        binding.btnReturnToMenuCwDialog.setOnClickListener{
            context?.startActivity(Intent(context, GameMenuActivity::class.java))
            Toast.makeText(context, "RETURNED TO GAME MENU", Toast.LENGTH_SHORT).show()
        }
    }

}