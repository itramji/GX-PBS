package com.ramji.intercom.status.fragment

import android.Manifest
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.preference.PreferenceManager
import android.telephony.SmsManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.PermissionChecker.checkSelfPermission
import androidx.fragment.app.Fragment
import com.ramji.intercom.status.R
import kotlinx.android.synthetic.main.fragment_board_view.*

class BoardViewFragment : Fragment(), SharedPreferences.OnSharedPreferenceChangeListener {

    private val PERMISSIONS_REQUEST_SMS = 101

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_board_view2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateViews()
        update_button.setOnClickListener { sendSMSMessage() }
        PreferenceManager.getDefaultSharedPreferences(requireContext()).registerOnSharedPreferenceChangeListener(this)
        checkForSmsPermission()
    }

    private fun updateViews() {
        val isModelOneSelected = PreferenceManager.getDefaultSharedPreferences(context).getString("pbx", "Gx 206") == "Gx 206"
        val msg = PreferenceManager.getDefaultSharedPreferences(requireContext()).getString(if(isModelOneSelected) "GX206" else "GX308", if(isModelOneSelected) "%111000110$" else "%11100011010$")
        val data = msg.subSequence(1, msg.lastIndex)

        e1.isChecked = data[0].toString() == "1"
        e2.isChecked = data[1].toString() == "1"
        e3.isChecked = data[2].toString() == "1"
        e4.isChecked = data[3].toString() == "1"
        e5.isChecked = data[4].toString() == "1"
        e6.isChecked = data[5].toString() == "1"

        if (PreferenceManager.getDefaultSharedPreferences(requireContext()).getString("pbx", "Gx 206").equals("Gx 206")) {
            trunk1.isChecked = data[6].toString() == "1"
            trunk2.isChecked = data[7].toString() == "1"
        }else{
            e7.isChecked = data[6].toString() == "1"
            e8.isChecked = data[7].toString() == "1"

            trunk1.isChecked = data[8].toString() == "1"
            trunk2.isChecked = data[9].toString() == "1"
            trunk3.isChecked = data[10].toString() == "1"
        }
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == "GX206" || key == "GX308") {
            updateViews()
        }else if(key == "pbx"){
            if (PreferenceManager.getDefaultSharedPreferences(requireContext()).getString("pbx", "Gx 206").equals("Gx 206")) {
                e7.visibility = View.GONE
                e8.visibility = View.GONE
                trunk3.visibility = View.GONE
            }else{
                e7.visibility = View.VISIBLE
                e8.visibility = View.VISIBLE
                trunk3.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        PreferenceManager.getDefaultSharedPreferences(requireContext()).unregisterOnSharedPreferenceChangeListener(this)
    }

    private fun sendSMSMessage() {

        try {
            val smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(PreferenceManager.getDefaultSharedPreferences(requireContext()).getString("MobileNumber", "9894008739"), null, "%R$", null, null)
            Toast.makeText(requireContext(), "SMS sent.", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "SMS failed, please try again.", Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSIONS_REQUEST_SMS -> if (permissions[0] == Manifest.permission.RECEIVE_SMS && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                update_button.isEnabled = true
            } else {
                update_button.isEnabled = false
                Toast.makeText(requireContext(), "App requires sms permission to work!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun checkForSmsPermission() {
        if (checkSelfPermission(requireContext(),
                        Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(
                    arrayOf(Manifest.permission.RECEIVE_SMS),
                    PERMISSIONS_REQUEST_SMS)
        } else {
            update_button.isEnabled = true
        }
    }

}
