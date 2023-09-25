package com.example.registrationapp.ui.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.registrationapp.R
import com.example.registrationapp.databinding.FragmentUserGreetingDialogBinding

class UserGreetingDialogFragment: DialogFragment() {

    private var _binding: FragmentUserGreetingDialogBinding? = null
    private val binding: FragmentUserGreetingDialogBinding
        get() = _binding!!


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = FragmentUserGreetingDialogBinding.inflate(layoutInflater)

        val userName = arguments?.getString(USER_NAME) ?: "Unknown"

        binding.userGreeting.text = getString(R.string.greeting_message, userName)

        val dialog = AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.greeting))
            .setView(binding.root)

        return dialog.create()
    }

    companion object{
        val TAG = UserGreetingDialogFragment::class.java.simpleName
        const val USER_NAME = "user name"
    }
}