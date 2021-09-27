package com.mahdikh.vision.infinitepager.widget

import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.viewpager.widget.PagerAdapter

abstract class InfiniteAdapter : PagerAdapter() {
    final override fun getCount(): Int {
        return getItemCount() + EXTRA_PAGE_COUNT
    }

    abstract fun getItemCount():Int

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    final override fun instantiateItem(container: ViewGroup, position: Int): Any {
        if (getItemCount() > 0)
            return instantiateItem(container, position, getAdapterPosition(position))
        return container
    }

    open fun instantiateItem(container: ViewGroup, pagePosition: Int, adapterPosition: Int): Any {
        return super.instantiateItem(container, pagePosition)
    }

    @CallSuper
    fun getAdapterPosition(pos: Int): Int {
        val adapterItemCount: Int = getItemCount()
        val pageCount: Int = count
        if (adapterItemCount >= 3) {
            return when (pos) {
                0 -> adapterItemCount - 3
                1 -> adapterItemCount - 2
                2 -> adapterItemCount - 1
                pageCount - 3 -> 0
                pageCount - 2 -> 1
                pageCount - 1 -> 2
                else -> pos - 3
            }
        } else if (adapterItemCount < 3) {
            if (adapterItemCount == 1) return 0
            return when (pos) {
                0, 2, pageCount - 2 -> {
                    1
                }
                1, pageCount - 3, pageCount - 1 -> {
                    0
                }
                else -> {
                    pos - 3
                }
            }
        }
        return pos
    }

    companion object {
        const val EXTRA_PAGE_COUNT = 6
    }
}