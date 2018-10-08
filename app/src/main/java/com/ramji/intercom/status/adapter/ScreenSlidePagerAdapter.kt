package com.ramji.intercom.status.adapter

import android.util.SparseArray
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.ramji.intercom.status.fragment.BoardViewFragment
import com.ramji.intercom.status.fragment.SettingsFragment
import com.ramji.intercom.status.fragment.TrunkListFragment


/**
 * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
 * sequence.
 */
internal class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val fragmentList: SparseArray<Fragment> = SparseArray()

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> TrunkListFragment()
            1 -> BoardViewFragment()
            else -> SettingsFragment()
        }
    }

    override fun getCount(): Int {
        return 3
    }

    // Register the fragment when the item is instantiated
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position) as Fragment
        fragmentList.put(position, fragment)
        return fragment
    }

    // Unregister when the item is inactive
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        fragmentList.remove(position)
        super.destroyItem(container, position, `object`)
    }

    fun getCurrentItem(pos: Int): Fragment {
        return fragmentList[pos]
    }
}