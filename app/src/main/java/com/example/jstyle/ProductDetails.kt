package com.example.jstyle

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewTreeObserver
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.product_details.*



class ProductDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.do_not_move, R.anim.do_not_move);
        setContentView(R.layout.product_details)

        val title = intent.getStringExtra("title")
        product_name.text = title

        availability.setOnClickListener {
            AlertDialog.Builder(this)
                .setMessage("Hey, $title in stock")
                .setPositiveButton("OK"
                ) { p0, p1 -> }
                .create()
                .show()
        }

//      Circle animation punya bahagian
        if (savedInstanceState == null) {
            photo.visibility = View.INVISIBLE

            val viewTreeObserver: ViewTreeObserver = photo.viewTreeObserver

            if (viewTreeObserver.isAlive) {
                viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {

                    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
                    override fun onGlobalLayout() {
                        circularRevealActivity()
                        photo.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    }
                })
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun circularRevealActivity() {
        val cx: Int = photo.width / 2
        val cy: Int = photo.height / 2
        val finalRadius: Int =
            photo.width.coerceAtLeast(photo.height)

        // create the animator for this view (the start radius is zero)
        val circularReveal =
            ViewAnimationUtils.createCircularReveal(photo, cx, cy, 0f, finalRadius.toFloat())
        circularReveal.duration = 1000

        // make the view visible and start the animation
        photo.visibility = View.VISIBLE
        circularReveal.start()
    }
}