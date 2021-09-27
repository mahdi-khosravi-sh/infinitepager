package com.mahdikh.vision.infinitepager.widget

interface Infinite {
    var callbacks: MutableList<Callback>?

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

    fun getAdapterItemCount(): Int

    fun getPagerItemCount(): Int

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
        if (position in 0..getAdapterItemCount()) {
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
}