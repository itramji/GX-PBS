package com.ramji.intercom.status.fragment

import android.app.Activity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ramji.intercom.status.R
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mobile_number.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                mobile_number.viewTreeObserver.removeOnPreDrawListener(this)
                mobile_number.setText(PreferenceManager.getDefaultSharedPreferences(requireContext()).getString("MobileNumber", "9894008739"))
                mobile_number.setSelection(10)
                return false
            }
        })

        PreferenceManager.getDefaultSharedPreferences(requireContext()).edit().putString("pbx", "Gx 206").apply()

        selector.setOnCheckedChangeListener { group, checkedId ->
            PreferenceManager.getDefaultSharedPreferences(requireContext()).edit().putString("pbx", selector.findViewById<RadioButton>(checkedId).text.toString()).apply() }

        mobile_number.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                if (mobile_number.length() < 10) {
                    Toast.makeText(requireContext(), "Mobile number should be in 10 digit", Toast.LENGTH_SHORT).show()
                } else {
                    PreferenceManager.getDefaultSharedPreferences(requireContext()).edit().putString("MobileNumber", mobile_number.text.toString()).apply()
                }
            }
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isAdded && !isVisibleToUser) {
            if (mobile_number.length() < 10) {
                Toast.makeText(requireContext(), "Mobile number should be in 10 digit", Toast.LENGTH_SHORT).show()
            } else {
                PreferenceManager.getDefaultSharedPreferences(requireContext()).edit().putString("MobileNumber", mobile_number.text.toString()).apply()
            }
            hideKeyboard()
        }
    }

    private fun hideKeyboard() {
        val inputMethodManager = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
    }
}
