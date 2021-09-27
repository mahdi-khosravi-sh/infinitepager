package com.mahdikh.vision.infinitepager.widget

interface Callback {
    fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

    fun onPageSelected(position: Int) {}

    fun onPageScrollStateChanged(state: Int) {}
}