package com.example.registrationapp.ui.fragment

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import java.util.Calendar

class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(requireContext(), this, year, month, day)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        Log.d("DatePicker", "$year $month $dayOfMonth")
        parentFragmentManager.setFragmentResult(
            REQUEST_KEY, bundleOf(
                YEAR_KEY to year,
                MONTH_KEY to month,
                DAY_KEY to dayOfMonth
            )
        )
    }


    companion object {
        val TAG = DatePickerFragment::class.java.simpleName
        const val REQUEST_KEY = "date"
        const val YEAR_KEY = "year"
        const val MONTH_KEY = "month"
        const val DAY_KEY = "day"
    }
}