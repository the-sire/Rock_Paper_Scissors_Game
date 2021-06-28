package com.thesire.rockpaperscissorsgame.ui.dialog

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.thesire.rockpaperscissorsgame.R
import com.thesire.rockpaperscissorsgame.data.preference.UserPreference
import com.thesire.rockpaperscissorsgame.databinding.FragmentPlayerOneWinVsComDialogBinding
import com.thesire.rockpaperscissorsgame.menu.GameMenuActivity
import com.thesire.rockpaperscissorsgame.ui.game.GameActivity


class PlayerOneWinVsComDialogFragment : DialogFragment() {

    private lateinit var binding : FragmentPlayerOneWinVsComDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayerOneWinVsComDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.bg_dialog_rounded_white)
        binding.tvPlayerOneWinnerVsComResultDialog.text = context?.let { UserPreference(it).userName } + " WINS!"
        binding.btnPlayAgainVsComDialog.setOnClickListener {
            GameActivity.startTheActivity(context,GameActivity.PLAY_MODE_VS_COM)
            Toast.makeText(context, "PLAY AGAIN!", Toast.LENGTH_SHORT).show()
        }
        binding.btnReturnToMenuVsComDialog.setOnClickListener{
            context?.startActivity(Intent(context, GameMenuActivity::class.java))
            Toast.makeText(context, "RETURNED TO GAME MENU", Toast.LENGTH_SHORT).show()
        }
    }

}