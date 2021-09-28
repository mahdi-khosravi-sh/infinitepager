package com.mahdikh.infinitepager

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.mahdikh.vision.infinitepager.widget.InfiniteAdapter
import com.mahdikh.vision.infinitepager.widget.InfinitePager

class MainActivity : AppCompatActivity() {
    private val adapter = ViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pager = findViewById<InfinitePager>(R.id.infinitePager)
        pager.adapter = adapter
    }

    class ViewAdapter : InfiniteAdapter() {
        private val colors: MutableList<Int> = mutableListOf(
            Color.parseColor("#303F9F"),
            Color.parseColor("#00796B"),
            Color.parseColor("#FFA000"),
            Color.parseColor("#D32F2F")
        )

        override fun getItemCount(): Int {
            return 4
        }

        override fun instantiateItem(
            container: ViewGroup,
            pagePosition: Int,
            adapterPosition: Int
        ): Any {
            val view = LayoutInflater
                .from(container.context.applicationContext)
                .inflate(R.layout.item, container, false)

            view.findViewById<TextView>(R.id.textView).text = "$adapterPosition"

            view.findViewById<CardView>(R.id.cardView)
                .setCardBackgroundColor(colors[adapterPosition])

            container.addView(view)
            return view
        }
    }
}