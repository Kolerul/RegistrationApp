package com.example.registrationapp.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.registrationapp.R
import com.example.registrationapp.RegistrationApp
import com.example.registrationapp.databinding.FragmentRegistrationBinding
import com.example.registrationapp.presentation.UserDataValidator
import com.example.registrationapp.presentation.uistate.RegistrationUIState
import com.example.registrationapp.presentation.viewmodel.RegistrationViewModel
import com.example.registrationapp.ui.animation.shakeAnimation
import com.google.android.material.snackbar.Snackbar
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
        setUIStateObserver()
    }

    private fun setUIStateObserver() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is RegistrationUIState.Initializing -> {
                    binding.apply {
                        contentLayout.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                    }
                    viewModel.checkIsRegistered()
                }

                is RegistrationUIState.Loading -> {
                    binding.apply {
                        contentLayout.visibility = View.GONE
                        progressBar.visibility = View.VISIBLE
                    }
                }

                is RegistrationUIState.Usual -> {
                    binding.apply {
                        contentLayout.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                    }
                }

                is RegistrationUIState.Success -> {
                    binding.apply {
                        contentLayout.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                    }
                    findNavController().navigate(R.id.action_registrationFragment_to_mainFragment)
                }

                is RegistrationUIState.Error -> {
                    binding.apply {
                        contentLayout.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                    }
                    showSnackBar(state.message)
                }
            }
        }
    }

    private fun setOnButtonClickListener() {
        binding.apply {
            registrationButton.setOnClickListener {
                if (checkFields()) {
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

    private fun showSnackBar(message: String) {
        Snackbar.make(binding.contentLayout, message, 3000).show()
    }

    private fun checkFields(): Boolean {
        binding.apply {
            val nameCheck = validator.checkName(nameEditText.text.toString())
            val surnameCheck = validator.checkSurname(surnameEditText.text.toString())
            val birthCheck = validator.checkBirthDate(dateOfBirthEditText.text.toString())
            val passwordCheck = validator.checkPassword(passwordEditText.text.toString())
            val confirmCheck = validator.checkPasswordConfirmation(
                passwordEditText.text.toString(),
                confirmPasswordEditText.text.toString()
            )

            if (!nameCheck) {
                nameValidationErrorText.visibility = View.VISIBLE
                nameValidationErrorText.text = getString(R.string.invalid_name)
                nameEditText.shakeAnimation()
            } else {
                nameValidationErrorText.visibility = View.INVISIBLE
                nameValidationErrorText.text = getString(R.string.name)
            }
            if (!surnameCheck) {
                surnameEditText.shakeAnimation()
                surnameValidationErrorText.visibility = View.VISIBLE
                surnameValidationErrorText.text = getString(R.string.invalid_surname)
            } else {
                surnameValidationErrorText.visibility = View.INVISIBLE
                surnameValidationErrorText.text = getString(R.string.surname)
            }
            if (!birthCheck) {
                dateOfBirthEditText.shakeAnimation()
                birthDateValidationErrorText.visibility = View.VISIBLE
                birthDateValidationErrorText.text = getString(R.string.invalid_birth_date)
            } else {
                birthDateValidationErrorText.visibility = View.INVISIBLE
                birthDateValidationErrorText.text = getString(R.string.date_of_birth)
            }
            if (!passwordCheck) {
                passwordEditText.shakeAnimation()
                passwordValidationErrorText.text = getString(R.string.invalid_password)
                passwordValidationErrorText.visibility = View.VISIBLE
            } else {
                passwordValidationErrorText.text = getString(R.string.password)
                passwordValidationErrorText.visibility = View.INVISIBLE
            }
            if (!confirmCheck) {
                confirmPasswordEditText.shakeAnimation()
                confirmHint.setTextColor(requireActivity().getColor(R.color.red))
                confirmHint.text = getString(R.string.invalid_confirm)
            } else {
                confirmHint.setTextColor(requireActivity().getColor(R.color.black))
                confirmHint.text = getString(R.string.repeat_the_password)
            }

            return nameCheck && surnameCheck && birthCheck && passwordCheck && confirmCheck
        }
    }

}