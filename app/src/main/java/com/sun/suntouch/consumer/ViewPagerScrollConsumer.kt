package com.sun.suntouch.consumer

import android.view.FocusFinder

/**
 * @author hengyangji
 * on 2022/10/7
 */
class ViewPagerScrollConsumer(
    private val scrollConsumerFinder: () -> IScrollConsumer
) : IScrollConsumer {
    override fun onScroll(scrollState: IntArray) {
        scrollConsumerFinder().onScroll(scrollState)
    }
}