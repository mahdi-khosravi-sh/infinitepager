package com.mahdikh.infinitepager

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mahdikh.vision.infinitepager.widget.Callback
import com.mahdikh.vision.infinitepager.widget.InfinitePager
import com.mahdikh.vision.infinitepager.widget.InfiniteAdapter

class MainActivity : AppCompatActivity() {
    private val adapter = ViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pager = findViewById<InfinitePager>(R.id.infinitePager)
        pager.adapter = adapter

        pager.registerCallback(object : Callback {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                Toast.makeText(
                    applicationContext,
                    "${pager.currentItem} Selected",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    class ViewAdapter : InfiniteAdapter() {
        private val items: MutableList<String> = mutableListOf("A")
        private val colors: MutableList<Int> = mutableListOf(
            Color.LTGRAY,
        )

        init {
            items.add("B")
            colors.add(Color.BLUE)
            items.add("C")
            colors.add(Color.CYAN)
            items.add("D")
            colors.add(Color.DKGRAY)
            items.add("E")
            colors.add(Color.YELLOW)
        }

        override fun getItemCount(): Int {
            return items.size
        }

        override fun instantiateItem(
            container: ViewGroup,
            pagePosition: Int,
            adapterPosition: Int
        ): Any {
            val view = TextView(container.context.applicationContext)
            view.gravity = Gravity.CENTER
            view.textSize = 50.0F
            view.setBackgroundColor(colors[adapterPosition])
            view.text = items[adapterPosition]
            container.addView(view)
            return view
        }
    }
}