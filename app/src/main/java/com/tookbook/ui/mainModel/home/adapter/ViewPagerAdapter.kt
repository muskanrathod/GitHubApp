package com.tookbook.ui.mainModel.home.adapter

import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter public constructor(supportFragmentManager: FragmentManager) :
    FragmentPagerAdapter(supportFragmentManager) {

    private final var fragmentList: ArrayList<Fragment> = ArrayList()
    private final var fragmentTitleList: ArrayList<String> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getPageTitle(position: Int): CharSequence {
        return fragmentTitleList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        fragmentTitleList.add(title)
    }
}