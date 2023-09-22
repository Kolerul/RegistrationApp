package com.example.registrationapp.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.registrationapp.RegistrationApp
import com.example.registrationapp.databinding.FragmentRegistrationBinding
import com.example.registrationapp.presentation.UserDataValidator
import com.example.registrationapp.presentation.viewmodel.RegistrationViewModel
import com.example.registrationapp.ui.animation.shakeAnimation
import javax.inject.Inject

class RegistrationFragment :
    BaseFragment<FragmentRegistrationBinding>(FragmentRegistrationBinding::inflate) {

    private val viewModel: RegistrationViewModel by viewModels {
        (requireActivity().application as RegistrationApp).appComponent.viewModelFactory()
    }

    @Inject
    lateinit var validator: UserDataValidator

    override fun onAttach(context: Context) {
        (requireActivity().application as RegistrationApp).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnButtonClickListener()
        viewModel.uiState.observe(viewLifecycleOwner) {
            binding.confirmHint.text = viewModel.uiState.toString()
        }
    }

    private fun setOnButtonClickListener() {
        binding.apply {
            registrationButton.setOnClickListener {
                val nameCheck = validator.checkName(nameEditText.text.toString())
                val surnameCheck = validator.checkSurname(surnameEditText.text.toString())
                val birthCheck = validator.checkBirthDate(dateOfBirthEditText.text.toString())
                val passwordCheck = validator.checkPassword(passwordEditText.text.toString())
                val confirmCheck = validator.checkPasswordConfirmation(
                    passwordEditText.text.toString(),
                    confirmPasswordEditText.text.toString()
                )

                if (!nameCheck) nameEditText.shakeAnimation()
                if (!surnameCheck) surnameEditText.shakeAnimation()
                if (!birthCheck) dateOfBirthEditText.shakeAnimation()
                if (!passwordCheck) passwordEditText.shakeAnimation()
                if (!confirmCheck) confirmPasswordEditText.shakeAnimation()

                if (nameCheck && surnameCheck && birthCheck && passwordCheck && confirmCheck)
                    viewModel.registration(
                        nameEditText.text.toString(),
                        surnameEditText.text.toString(),
                        dateOfBirthEditText.text.toString(),
                        passwordEditText.text.toString()
                    )
            }
        }
    }

}