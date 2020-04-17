package com.example.jstyle

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private val arrayList: ArrayList<Fragment> = ArrayList()


    override fun getItemCount(): Int {
        return arrayList.size
    }

    fun addFragment(fragment:Fragment) {
        arrayList.add(fragment)
    }

    fun replaceSexFragment(fragment:Fragment) {
        arrayList[1] = fragment
    }

    override fun createFragment(position: Int): Fragment {
        return arrayList[position];
    }
}