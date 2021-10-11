package com.mahdikh.vision.infinitepager.widget

interface OnPageChangeCallback {
    fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

    fun onPageSelected(position: Int) {}

    fun onPageScrollStateChanged(state: Int) {}
}