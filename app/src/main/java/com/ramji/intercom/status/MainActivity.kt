package com.ramji.intercom.status

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ramji.intercom.status.adapter.ScreenSlidePagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                title = getString(R.string.title_home)
                view_pager.currentItem = 0
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                title = "BoardView"
                view_pager.currentItem = 1
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
                title = getString(R.string.title_settings)
                view_pager.currentItem = 2
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

        view_pager.addOnPageChangeListener(this)
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        navigation.selectedItemId = when(position){
            0-> R.id.navigation_home
            1-> R.id.navigation_dashboard
            else -> R.id.navigation_settings
        }
    }

    override fun onBackPressed() {
        if (view_pager.currentItem == 0) {
            val childFragmentManager = (view_pager.adapter as ScreenSlidePagerAdapter).getCurrentItem(view_pager.currentItem).childFragmentManager
            if (childFragmentManager.backStackEntryCount > 0) {
                childFragmentManager.popBackStack()
                return
            }
        }
        super.onBackPressed()
    }
}
