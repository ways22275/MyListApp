package com.my.myredsoapp.listRedSo

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter (fm : FragmentManager, private val mTitles: List<String>) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return SubFragment.newInstance(mTitles[position])
    }

    override fun getCount(): Int {
        return if (mTitles.isEmpty()) 0 else mTitles.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mTitles[position]
    }
}