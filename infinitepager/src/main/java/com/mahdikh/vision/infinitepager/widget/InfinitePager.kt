package com.mahdikh.vision.infinitepager.widget

import android.content.Context
import android.database.DataSetObserver
import android.util.AttributeSet
import android.util.Log
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.mahdikh.vision.infinitepager.R
import kotlin.math.max

class InfinitePager : ViewPager {
    private var callbacks: MutableList<Callback>? = null
    private var pagerItemCount = 0
    private var adapterItemCount = 0
    private var jumpPosition = -1
    private var current = ITEM_FIRST

    private val listener = object : OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            dispatchOnPageScrolled(
                position,
                positionOffset,
                positionOffsetPixels
            )
        }

        override fun onPageSelected(position: Int) {
            if (pagerItemCount >= 3) {
                when {
                    adapterItemCount >= 3 -> {
                        when {
                            position <= 2 -> {
                                jumpPosition = position + adapterItemCount
                            }
                            position >= pagerItemCount - 3 -> {
                                jumpPosition = position - adapterItemCount
                            }
                        }
                    }
                    adapterItemCount == 2 -> {
                        when (position) {
                            0, 2, 6 -> {
                                jumpPosition = 4
                            }
                            1, 5, 7 -> {
                                jumpPosition = 3
                            }
                        }
                    }
                    else -> {
                        jumpPosition = 3
                    }
                }
            }
            dispatchOnPageSelected(
                (adapter as InfiniteAdapter).getAdapterPosition(
                    position
                )
            )
        }

        override fun onPageScrollStateChanged(state: Int) {
            if (state == SCROLL_STATE_IDLE && jumpPosition > 0) {
                jump()
            }
            dispatchOnPageStateChanged(state)
        }
    }

    private val observer = object : DataSetObserver() {
        override fun onChanged() {
            resetAdapter()
            jumpPosition = -1
        }
    }

    init {
        super.addOnPageChangeListener(listener)
    }

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.InfinitePager)
            val count = a.indexCount
            var index: Int
            for (i in 0 until count) {
                index = a.getIndex(i)
                when (index) {
                    R.styleable.InfinitePager_currentItem -> {
                        current = a.getInt(index, ITEM_FIRST)
                    }
                }
            }
            a.recycle()
        }
    }

    override fun setAdapter(adapter: PagerAdapter?) {
        getAdapter()?.unregisterDataSetObserver(observer)
        if (adapter != null) {
            if (adapter !is InfiniteAdapter) {
                throw ClassCastException("the adapter must have extends from the com.mahdikh.vision.infinitepager.widget.InfinitePagerAdapter")
            }
            adapter.registerDataSetObserver(observer)
            adapterItemCount = adapter.getItemCount()
            pagerItemCount = adapter.count
        }
        super.setAdapter(adapter)
        currentItem = current
    }

    private fun resetAdapter() {
        val a = adapter
        adapterItemCount = (a as InfiniteAdapter).getItemCount()
        pagerItemCount = a.count
        val current = currentItem
        super.setAdapter(a)
        currentItem = current
    }

    override fun setOffscreenPageLimit(limit: Int) {
        super.setOffscreenPageLimit(max(InfiniteAdapter.EXTRA_PAGE_COUNT, limit))
    }

    override fun setCurrentItem(item: Int) {
        super.setCurrentItem(getPosition(item))
    }

    override fun setCurrentItem(item: Int, smoothScroll: Boolean) {
        super.setCurrentItem(getPosition(item), smoothScroll)
    }

    override fun getCurrentItem(): Int {
        return (adapter as InfiniteAdapter?)?.getAdapterPosition(super.getCurrentItem())
            ?: 0
    }

    private fun jump() {
        super.setCurrentItem(jumpPosition, false)
        jumpPosition = -1
    }

    private fun getPosition(item: Int): Int {
        if (item < 0) {
            return when (item) {
                ITEM_FIRST -> 3
                ITEM_CENTER -> pagerItemCount / 2
                ITEM_LAST -> pagerItemCount - 4
                else -> 3
            }
        } else {
            if (item >= adapterItemCount) {
                return pagerItemCount - 4
            }
            return item + 3
        }
    }

    private fun dispatchOnPageScrolled(
        position: Int,
        positionOffset: Float,
        positionOffsetPixels: Int
    ) {
        callbacks?.let {
            val size = it.size
            for (i in 0 until size) {
                it[i].onPageScrolled(position, positionOffset, positionOffsetPixels)
            }
        }
    }

    private fun dispatchOnPageSelected(position: Int) {
        if (position in 0..adapterItemCount) {
            callbacks?.let {
                val size = it.size
                for (i in 0 until size) {
                    it[i].onPageSelected(position)
                }
            }
        }
    }

    private fun dispatchOnPageStateChanged(state: Int) {
        callbacks?.let {
            val size = it.size
            for (i in 0 until size) {
                it[i].onPageScrollStateChanged(state)
            }
        }
    }

    override fun addOnPageChangeListener(listener: OnPageChangeListener) {
        Log.w(
            TAG, "InfinitePager does not support add OnPageChangeListener. " +
                    "Use registerCallback instead"
        )
    }

    override fun removeOnPageChangeListener(listener: OnPageChangeListener) {
        Log.w(
            TAG, "InfinitePager does not support removeOnPageChangeListener. " +
                    "Use unregisterCallback instead"
        )
    }

    override fun clearOnPageChangeListeners() {
        Log.w(
            TAG, "InfinitePager does not support clearOnPageChangeListener. " +
                    "Use unregisterCallbacks instead"
        )
    }

    fun registerCallback(callback: Callback) {
        if (callbacks == null) {
            callbacks = mutableListOf()
        }
        callbacks?.add(callback)
    }

    fun unregisterCallback(callback: Callback) {
        callbacks?.remove(callback)
    }

    fun unregisterCallbacks() {
        callbacks?.clear()
        callbacks = null
    }

    companion object {
        const val TAG = "InfinitePager"

        const val ITEM_FIRST = -1
        const val ITEM_CENTER = -2
        const val ITEM_LAST = -3
    }
}