package com.example.registrationapp.presentation

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText

class RegistrationTextWatcher(
    private val textsList: List<EditText>,
    private val enableView: View
) : TextWatcher {

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(s: Editable?) {
        for (text: EditText in textsList) {
            if (text.text.isEmpty()) {
                enableView.isEnabled = false
                return
            }
        }
        enableView.isEnabled = true
    }
}