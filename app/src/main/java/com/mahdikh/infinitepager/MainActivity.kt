package com.mahdikh.infinitepager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
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
        override fun getItemCount(): Int {
            return 3
        }

        override fun instantiateItem(
            container: ViewGroup,
            pagePosition: Int,
            adapterPosition: Int
        ): Any {
            val view: View = LayoutInflater.from(container.context.applicationContext)
                .inflate(R.layout.item, container, false)
            view.findViewById<TextView>(R.id.textView).text = "$adapterPosition"
            container.addView(view)
            return view
        }
    }
}