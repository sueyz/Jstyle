package com.example.jstyle

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.fragment.app.Fragment


class CategoryFragmentWomen : Fragment() {

    private lateinit var gridLayout: GridLayout


    @SuppressLint("NewApi")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.category_grid, container ,false)

        gridLayout = root.findViewById(R.id.gridview)

        gridLayout.rowCount = 4
        gridLayout.columnCount = 2

        val outValue = TypedValue()
        context!!.theme
            .resolveAttribute(android.R.attr.selectableItemBackground, outValue, true)


        for (i in 1..8){

            val oImageView = Button(this.context)
            oImageView.foreground = ContextCompat.getDrawable(context!!,outValue.resourceId)




            when (i) {
                1 -> oImageView.setBackgroundResource(R.drawable.hijab1)
                2 -> oImageView.setBackgroundResource(R.drawable.dress1)
                3 -> oImageView.setBackgroundResource(R.drawable.makp1)
                4 -> oImageView.setBackgroundResource(R.drawable.shoesw1)
                5 -> oImageView.setBackgroundResource(R.drawable.pantsw1)
                6 -> oImageView.setBackgroundResource(R.drawable.artistw1)
                7 -> oImageView.setBackgroundResource(R.drawable.brandw1)
                8 -> oImageView.setBackgroundResource(R.drawable.outfitw1)
            }

            val layoutParams: GridLayout.LayoutParams = GridLayout.LayoutParams(
                GridLayout.spec(GridLayout.UNDEFINED, 1f),
                GridLayout.spec(GridLayout.UNDEFINED, 1f)).apply {
                width = 0
                height = 0
            }


            gridLayout.addView(oImageView, layoutParams)


        }

        gridLayout[0].setOnClickListener {
            gridLayout[0].setBackgroundResource(R.drawable.hijab)
        }
        gridLayout[1].setOnClickListener {
            gridLayout[1].setBackgroundResource(R.drawable.dress)
        }
        gridLayout[2].setOnClickListener {
            gridLayout[2].setBackgroundResource(R.drawable.makp)
        }
        gridLayout[3].setOnClickListener {
            gridLayout[3].setBackgroundResource(R.drawable.shoesw)
        }
        gridLayout[4].setOnClickListener {
            gridLayout[4].setBackgroundResource(R.drawable.pantsw)
        }
        gridLayout[5].setOnClickListener {
            gridLayout[5].setBackgroundResource(R.drawable.artistw)
        }
        gridLayout[6].setOnClickListener {
            gridLayout[6].setBackgroundResource(R.drawable.brandw)
        }
        gridLayout[7].setOnClickListener {
            gridLayout[7].setBackgroundResource(R.drawable.outfitw)
        }



        return root
    }
}