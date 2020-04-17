package com.example.jstyle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.trending_fragment.view.*
import model.Product

class TrendingFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val root = inflater.inflate(R.layout.trending_fragment, container ,false)

        val products = arrayListOf<Product>()

        for (i in 0..100) {

            products.add(
                Product(
                    "tudung hijau",
                    "https://i.pinimg.com/originals/ef/39/ea/ef39ea96a6d99482fdd9e3aa05f9a1ba.jpg",
                    4.55
                )
            )


        }

        root.recycler_view.apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = ProductsAdapter(products)
        }


        return root
    }

}