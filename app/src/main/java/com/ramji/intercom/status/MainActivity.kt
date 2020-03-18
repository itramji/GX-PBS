package com.ramji.intercom.status

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ramji.intercom.status.adapter.ScreenSlidePagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_settings.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket


class MainActivity : AppCompatActivity(), ViewPager.OnPageChangeListener, SharedPreferences.OnSharedPreferenceChangeListener {

    private var SERVERPORT = 3689
    private var SERVER_IP = "192.168.1.113"


    var backgroundThread: Thread? = null
    var serverThread: Thread? = null

    override fun onStart() {
        super.onStart()
        restartServer()
    }

    private fun startServer() {
        if (backgroundThread == null) {
            backgroundThread = Thread(Thread1())
            backgroundThread!!.start()
        }
    }

    override fun onStop() {
        super.onStop()
        stopSever()
    }

    private fun stopSever() {
        if (backgroundThread != null) {
            backgroundThread!!.interrupt()
        }

        if (serverThread != null) {
            serverThread!!.interrupt()
        }

        backgroundThread = null
        serverThread = null
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            /* R.id.navigation_home -> {
                 title = getString(R.string.title_home)
                 view_pager.currentItem = 0
                 return@OnNavigationItemSelectedListener true
             }*/
            R.id.navigation_dashboard -> {
                title = "BoardView"
                view_pager.setCurrentItem(0, false)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
                title = getString(R.string.title_settings)
                view_pager.setCurrentItem(1, false)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        view_pager.adapter = ScreenSlidePagerAdapter(supportFragmentManager)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = R.id.navigation_dashboard
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this)
        SERVER_IP = PreferenceManager.getDefaultSharedPreferences(this).getString("IpAddress", "192.168.1.43")
        SERVERPORT = PreferenceManager.getDefaultSharedPreferences(this).getString("PortNumber", "3276").toInt()
        view_pager.addOnPageChangeListener(this)
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        navigation.selectedItemId = when (position) {
            //0-> R.id.navigation_home
            0 -> R.id.navigation_dashboard
            else -> R.id.navigation_settings
        }
    }

    override fun onBackPressed() {
        /* if (view_pager.currentItem == 0) {
             val childFragmentManager = (view_pager.adapter as ScreenSlidePagerAdapter).getCurrentItem(view_pager.currentItem).childFragmentManager
             if (childFragmentManager.backStackEntryCount > 0) {
                 childFragmentManager.popBackStack()
                 return
             }
         }*/
        super.onBackPressed()
    }

    override fun onSharedPreferenceChanged(p0: SharedPreferences?, key: String?) {
        if (key == "IpAddress") {
            SERVER_IP = PreferenceManager.getDefaultSharedPreferences(this).getString("IpAddress", "192.168.1.43")
            restartServer()
        } else if (key == "PortNumber") {
            SERVERPORT = PreferenceManager.getDefaultSharedPreferences(this).getString("PortNumber", "3276").toInt()
            restartServer()
        } else if (key == "position") {
            val position = PreferenceManager.getDefaultSharedPreferences(this).getInt("position", -1)
            sendMsgToServer("%E00$position\$")
        }
    }

    private fun restartServer() {
        stopSever()
        startServer()
        sendMsgToServer("%H\$")
    }


    var toServer: PrintWriter? = null
    var fromServer: BufferedReader? = null

    inner class Thread1 : Runnable {
        override fun run() {
            val socket: Socket
            try {
                socket = Socket(SERVER_IP, SERVERPORT)
                toServer = PrintWriter(socket.getOutputStream())
                fromServer = BufferedReader(InputStreamReader(socket.getInputStream()))
                runOnUiThread(Runnable {
                    //  tvMessages.setText("Connected\n");
                    Toast.makeText(this@MainActivity, "Connected", Toast.LENGTH_SHORT).show()
                })

                serverThread = Thread(Thread2())
                serverThread!!.start()

                sendMsgToServer("%H\$")
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    inner class Thread2 : Runnable {
        override fun run() {
            while (true) {
                try {
                    val message: String? = fromServer?.readLine()
                    if (message != null) {
                        runOnUiThread(Runnable {
                            // tvMessages.append("server: " + message + "\n");
                            if (message.startsWith("%") && message.endsWith("$")) {
                                PreferenceManager.getDefaultSharedPreferences(this@MainActivity).edit().putString("serverData", message).apply()
                                Toast.makeText(this@MainActivity, "Received $message from server - updating UI", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(this@MainActivity, "Received $message from server - wrong format", Toast.LENGTH_SHORT).show()
                            }
                        })
                    } else {
                        Thread(Thread1()).start()
                        return
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    inner class Thread3(private val message: String) : Runnable {
        override fun run() {
            toServer?.write(message)
            toServer?.flush()
            runOnUiThread {
                //tvMessages.append("server: " + message + "\n");
                Toast.makeText(this@MainActivity, "sending $message to server..", Toast.LENGTH_SHORT).show()
                //  et.setText("");
            }
        }

    }

    fun sendMsgToServer(msg: String) {
        Thread(Thread3(msg)).start()
    }

}
