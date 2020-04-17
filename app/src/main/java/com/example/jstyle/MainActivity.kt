package com.example.jstyle

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log.d
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val sharedPrefFile = "kotlinsharedpreference"


    private lateinit var tabLayout : TabLayout
    private lateinit var viewPager : ViewPager2

    private lateinit var myAdapter : TabAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,
            Context.MODE_PRIVATE)

        //confusing why boolean ni mcm terbalik
        val sharedMenValue = sharedPreferences.getBoolean("men",false)
        val sharedWomenValue = sharedPreferences.getBoolean("women",true)

        setContentView(R.layout.activity_main)
//        setSupportActionBar(toolbar)
//        supportActionBar?.setDisplayShowTitleEnabled(false)



//        tabLayout = findViewById(R.id.tabLayout)
//        viewPager = findViewById(R.id.viewPager)
//
//        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
//
                supportFragmentManager.beginTransaction().replace(R.id.frameLayout, MainFragment()).commit()
//
//        myAdapter = TabAdapter(supportFragmentManager, lifecycle)
//
//
//        // add Fragments in your ViewPagerFragmentAdapter class
//        myAdapter.addFragment(HomeFragment())
//
//
//        if(sharedMenValue && !sharedWomenValue){
//            myAdapter.addFragment(CategoryFragmentWomen())
//            men.visibility = View.GONE
//            women.visibility = View.VISIBLE
//        }else{
//            myAdapter.addFragment(CategoryFragmentMen())
//            men.visibility = View.VISIBLE
//            women.visibility = View.GONE
//        }
//        myAdapter.addFragment(TrendingFragment())
//
//
//        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL;
//        viewPager.adapter = myAdapter
//
//        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
//        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
//            when (position) {
//                0 -> { tab.text = "Home"}
//                1 -> { tab.text = "Categories"}
//                2 -> { tab.text = "Trending"}
//            }
//        }.attach()
//
//
//        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//            override fun onTabReselected(tab: TabLayout.Tab) {
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab) {
//            }
//
//            override fun onTabSelected(tab: TabLayout.Tab) {
//                viewPager.currentItem = tab.position
//
//            }
//        })
//
//
//
//
//
//
//            val popupMenu = PopupMenu(this, women,Gravity.CENTER)
//        popupMenu.setOnMenuItemClickListener { item ->
//            when (item.itemId){
//                R.id.female -> {
//                    val editor:SharedPreferences.Editor =  sharedPreferences.edit()
//                    //confusing why boolean ni mcm terbalik
//                    editor.putBoolean("women",false)
//                    editor.putBoolean("men",true)
//                    editor.apply()
//
//                    myAdapter.replaceSexFragment(CategoryFragmentWomen())
//                    myAdapter.createFragment(1)
//                    viewPager.adapter = myAdapter
//
//////                    editor.commit()
//
////                    popupMenu.menu.findItem(item.itemId).isChecked = true
//                    men.visibility = View.GONE
//                    women.visibility = View.VISIBLE
//
//                    true
//                }
//                R.id.male -> {
//                    val editor:SharedPreferences.Editor =  sharedPreferences.edit()
//                    //confusing why boolean ni mcm terbalik
//                    editor.putBoolean("men",false)
//                    editor.putBoolean("women",true)
//                    editor.apply()
//
//                    myAdapter.replaceSexFragment(CategoryFragmentMen())
//                    myAdapter.createFragment(1)
//                    viewPager.adapter = myAdapter
//
//
////                    editor.commit()
//
////                    popupMenu.menu.findItem(item.itemId).isChecked = true
//                    men.visibility = View.VISIBLE
//                    women.visibility = View.GONE
//                    true
//                }
//                else -> false
//            }
//        }
//
//
//        popupMenu.inflate(R.menu.menu_popup)
//
//
//        try {
//            val fieldMPopup = PopupMenu::class.java.getDeclaredField("mPopup")
//            fieldMPopup.isAccessible = true
//            val mPopup = fieldMPopup.get(popupMenu)
//            mPopup.javaClass
//                .getDeclaredMethod("setForceShowIcon", Boolean::class.java)
//                .invoke(mPopup, true)
//        } catch (e: Exception){
//            Log.e("Main", "Error showing menu icons.", e)
//        } finally {
//        }
//
//
//        women.setOnClickListener {
////            popupMenu.menu.getItem(0).isChecked = true
//            popupMenu.show()
//
//        }
//        men.setOnClickListener {
////            popupMenu.menu.getItem(0).isChecked = true
//
//            popupMenu.show()
//
//        }






        navigationView.itemIconTintList = null;



        val hView = navigationView.getHeaderView(0)
        hView.findViewById<View>(R.id.navSignIn).setOnClickListener{
            drawerLayout.closeDrawers()
            startActivity(Intent(this,RegisterActivity::class.java))
        }
//        val nav_user = hView.findViewById<View>(R.id.nav_name) as TextView
//        nav_user.setText(user)

        navigationView.setNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.actionHome -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, MainFragment()).commit()
                }
                R.id.actionOrder -> {
//                    startActivity(Intent(this,RegisterActivity::class.java))
                    //dalam Register Activity dah ada yang below ni
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.frameLayout, SignInFragment()).addToBackStack(null).commit()
                }
                R.id.actionWant -> d("daniel", "jeans was pressed")



            }
            it.isChecked = false
            drawerLayout.closeDrawers()
            true
        }





    }


    override fun onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }

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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_action, menu)
        return super.onCreateOptionsMenu(menu)
    }




}

