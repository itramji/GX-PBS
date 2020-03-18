package com.ramji.intercom.status.fragment

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ramji.intercom.status.IPAddressValidator
import com.ramji.intercom.status.R
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlin.properties.Delegates


class SettingsFragment : Fragment() {

    var selectedPosition = 0

    private val items = arrayOf("4/48", "4/64", "4/80", "4/96", "8/48", "8/64", "8/80", "8/96")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        config_selected.text = PreferenceManager.getDefaultSharedPreferences(requireContext()).getString("Config", "4/48")

        selectedPosition = items.indexOf(config_selected.text)

        config_selected.setOnClickListener {

            AlertDialog.Builder(context)
                    .setSingleChoiceItems(items, selectedPosition, null)
                    .setPositiveButton("Save") { dialog, _ ->
                        dialog.dismiss()
                        selectedPosition = (dialog as AlertDialog).listView.checkedItemPosition
                        config_selected.text = items[selectedPosition]
                        PreferenceManager.getDefaultSharedPreferences(requireContext()).edit().putString("Config", items[selectedPosition]).apply()
                    }.show()
        }

        /* mobile_number.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
             override fun onPreDraw(): Boolean {
                 mobile_number.viewTreeObserver.removeOnPreDrawListener(this)
                 mobile_number.setText(PreferenceManager.getDefaultSharedPreferences(requireContext()).getString("MobileNumber", "9894008739"))
                 mobile_number.setSelection(10)
                 return false
             }
         })

         mobile_number.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
             if (!hasFocus) {
                 if (mobile_number.length() < 10) {
                     Toast.makeText(requireContext(), "Mobile number should be in 10 digit", Toast.LENGTH_SHORT).show()
                 } else {
                     PreferenceManager.getDefaultSharedPreferences(requireContext()).edit().putString("MobileNumber", mobile_number.text.toString()).apply()
                 }
             }
         }*/

        ip_address_et.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                ip_address_et.viewTreeObserver.removeOnPreDrawListener(this)
                ip_address_et.setText(PreferenceManager.getDefaultSharedPreferences(requireContext()).getString("IpAddress", "192.168.1.43"))
                return false
            }
        })

        ip_address_et.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                if (!IPAddressValidator.validate(ip_address_et.text.toString())) {
                    Toast.makeText(requireContext(), "Please enter valid IpAddress", Toast.LENGTH_SHORT).show()
                } else {
                    PreferenceManager.getDefaultSharedPreferences(requireContext()).edit().putString("IpAddress", ip_address_et.text.toString()).apply()
                }
            }
        }

        port_address_et.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                port_address_et.viewTreeObserver.removeOnPreDrawListener(this)
                port_address_et.setText(PreferenceManager.getDefaultSharedPreferences(requireContext()).getString("PortNumber", "3276"))
                return false
            }
        })

        port_address_et.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                if (port_address_et.length() < 4) {
                    Toast.makeText(requireContext(), "Port number should be in 4 digit", Toast.LENGTH_SHORT).show()
                } else {
                    PreferenceManager.getDefaultSharedPreferences(requireContext()).edit().putString("PortNumber", port_address_et.text.toString()).apply()
                }
            }
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isAdded && !isVisibleToUser) {
            /* if (mobile_number.length() < 10) {
                 Toast.makeText(requireContext(), "Mobile number should be in 10 digit", Toast.LENGTH_SHORT).show()
             } else {
                 PreferenceManager.getDefaultSharedPreferences(requireContext()).edit().putString("MobileNumber", mobile_number.text.toString()).apply()
             }*/
            hideKeyboard()
        }
    }

    private fun hideKeyboard() {
        val inputMethodManager = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
    }
}
