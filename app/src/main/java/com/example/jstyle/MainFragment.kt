package com.example.jstyle

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat.getDrawable
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main.*
import kotlinx.android.synthetic.main.main.view.*


class MainFragment : Fragment() {

    private val sharedPrefFile = "kotlinsharedpreference"

    private lateinit var tabLayout : TabLayout
    private lateinit var viewPager : ViewPager2

    private lateinit var myAdapter : TabAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_main, container ,false)

        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)
        (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayShowTitleEnabled(false)


        val sharedPreferences: SharedPreferences? = activity!!.getSharedPreferences(sharedPrefFile,
            Context.MODE_PRIVATE)

        //confusing why boolean ni mcm terbalik
        val sharedMenValue = sharedPreferences!!.getBoolean("men",false)
        val sharedWomenValue = sharedPreferences.getBoolean("women",true)
//
        tabLayout = root.findViewById(R.id.tabLayout)
        viewPager = root.findViewById(R.id.viewPager)

        tabLayout.tabGravity = TabLayout.GRAVITY_FILL


        myAdapter = TabAdapter(activity!!.supportFragmentManager, lifecycle)


        // add Fragments in your ViewPagerFragmentAdapter class
        myAdapter.addFragment(HomeFragment())


        if(sharedMenValue && !sharedWomenValue){
            myAdapter.addFragment(CategoryFragmentWomen())
            root.men.visibility = View.GONE
            root.women.visibility = View.VISIBLE
        }else{
            myAdapter.addFragment(CategoryFragmentMen())
            root.men.visibility = View.VISIBLE
            root.women.visibility = View.GONE
        }
        myAdapter.addFragment(TrendingFragment())


        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL;
        viewPager.adapter = myAdapter

        val tabLayout = root.findViewById<TabLayout>(R.id.tabLayout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> { tab.text = "Home"}
                1 -> { tab.text = "Categories"}
                2 -> { tab.text = "Trending"}
            }
        }.attach()


        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position

            }
        })






            val popupMenu = this.context?.let { PopupMenu(it, root.women, Gravity.CENTER) }
        popupMenu?.setOnMenuItemClickListener { item ->
            when (item.itemId){
                R.id.female -> {
                    val editor:SharedPreferences.Editor =  sharedPreferences.edit()
                    //confusing why boolean ni mcm terbalik
                    editor.putBoolean("women",false)
                    editor.putBoolean("men",true)
                    editor.apply()

                    myAdapter.replaceSexFragment(CategoryFragmentWomen())
                    myAdapter.createFragment(1)
                    viewPager.adapter = myAdapter

////                    editor.commit()

//                    popupMenu.menu.findItem(item.itemId).isChecked = true
                    root.men.visibility = View.GONE
                    root.women.visibility = View.VISIBLE

                    true
                }
                R.id.male -> {
                    val editor:SharedPreferences.Editor =  sharedPreferences.edit()
                    //confusing why boolean ni mcm terbalik
                    editor.putBoolean("men",false)
                    editor.putBoolean("women",true)
                    editor.apply()

                    myAdapter.replaceSexFragment(CategoryFragmentMen())
                    myAdapter.createFragment(1)
                    viewPager.adapter = myAdapter


//                    editor.commit()

//                    popupMenu.menu.findItem(item.itemId).isChecked = true
                    root.men.visibility = View.VISIBLE
                    root.women.visibility = View.GONE
                    true
                }
                else -> false
            }
        }


        popupMenu?.inflate(R.menu.menu_popup)


        try {
            val fieldMPopup = PopupMenu::class.java.getDeclaredField("mPopup")
            fieldMPopup.isAccessible = true
            val mPopup = fieldMPopup.get(popupMenu)
            mPopup.javaClass
                .getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                .invoke(mPopup, true)
        } catch (e: Exception){
            Log.e("Main", "Error showing menu icons.", e)
        } finally {
        }


        root.women.setOnClickListener {
//            popupMenu.menu.getItem(0).isChecked = true
            popupMenu?.show()

        }
        root.men.setOnClickListener {
//            popupMenu.menu.getItem(0).isChecked = true

            popupMenu?.show()

        }


        (activity as AppCompatActivity?)!!.supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_white)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //if session men the men
                setBackgroundDrawable(context?.let { getDrawable(it,R.drawable.gradient_action_bar) })
            }
        }





        return root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        drawerLayout.openDrawer(GravityCompat.START)

        when (item.itemId) {
            R.id.actionSearch -> {
                drawerLayout.openDrawer(GravityCompat.START)
                return true
            }
            R.id.actionWant -> {
                drawerLayout.openDrawer(GravityCompat.START)
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // Inflate the menu to use in the action bar
        inflater.inflate(R.menu.menu_action, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

}