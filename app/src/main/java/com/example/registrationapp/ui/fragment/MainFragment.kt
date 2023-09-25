package com.example.registrationapp.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.example.registrationapp.RegistrationApp
import com.example.registrationapp.databinding.FragmentMainBinding
import com.example.registrationapp.presentation.uistate.MainUIState
import com.example.registrationapp.presentation.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class MainFragment: BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private val viewModel: MainViewModel by viewModels {
        (requireActivity().application as RegistrationApp).appComponent.viewModelFactory()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnButtonClickListener()
        setUIStateObserver()
    }

    private fun setUIStateObserver(){
        viewModel.uiState.observe(viewLifecycleOwner){ state ->
            when(state){
                is MainUIState.Initializing -> {
                    binding.apply {
                        greetingsButton.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                    }
                }
                is MainUIState.Loading -> {
                    binding.apply {
                        greetingsButton.visibility = View.GONE
                        progressBar.visibility = View.VISIBLE
                    }
                }
                is MainUIState.Success -> {
                    binding.apply {
                        greetingsButton.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                    }
                    val name = "${state.user.name} ${state.user.surname}"
                    showGreetingDialog(name)
                }
                is MainUIState.Error -> {
                    binding.apply {
                        greetingsButton.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                    }
                    showSnackBar(state.message)
                }
            }
        }
    }

    private fun showGreetingDialog(name: String){
        val dialog = UserGreetingDialogFragment()
        dialog.arguments = bundleOf(
            UserGreetingDialogFragment.USER_NAME to name
        )

        dialog.show(parentFragmentManager, UserGreetingDialogFragment.TAG)
    }

    private fun setOnButtonClickListener(){
        binding.greetingsButton.setOnClickListener {
            viewModel.getUser()
        }
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(binding.contentLayout, message, 3000).show()
    }
}