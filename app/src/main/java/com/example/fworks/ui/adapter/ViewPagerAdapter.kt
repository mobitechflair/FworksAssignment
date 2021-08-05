package com.example.fworks.ui.adapter

import android.content.Context

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.fworks.ui.views.FirstFragment
import com.example.fworks.ui.views.SecondFragment

class ViewPagerAdapter(manager: FragmentManager, private val mContext: Context) : FragmentPagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
{
    // Returns total number of pages
    override fun getCount(): Int {
        return NUM_ITEMS
    }

    // Returns the fragment to display for that page
    override fun getItem(position: Int): Fragment {
        return when (position)
        {
            0 -> FirstFragment()
            1 -> SecondFragment()
           // 0 -> SecondFragment()
            else-> FirstFragment()
        }
    }

    // Returns the page title for the top indicator
    override fun getPageTitle(position: Int): CharSequence? {
        var title = ""
        when (position) {
            0 -> title="Trending"
            1 -> title="Favourite"
        }
        return title
    }

    companion object {
        private const val NUM_ITEMS = 2
    }

    override fun getItemPosition(a: Any): Int {

        return POSITION_NONE;
    }
}