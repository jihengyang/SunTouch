package com.sun.suntouch.consumer

import android.view.View
import kotlin.math.abs

/**
 * @author hengyangji
 * on 2022/10/7
 */
class HeaderScrollConsumer(
    private val headerView: View,
    private val hangingView: View,
    private val contentView: View
) : IScrollConsumer {
    private var scrollY = 0

    override fun onScroll(scrollState: IntArray) {
        if (scrollState.getY() > 0) {   //上划
            if (headerView.height - scrollY > hangingView.height) {
                val remainY = headerView.height - scrollY - hangingView.height
                val consumeY = remainY.coerceAtMost(abs(scrollState.getY()))
                scrollY += consumeY
                scrollState.consumeY(consumeY)
            }
        } else {    //下滑
            if (scrollY > 0) {
                val consumeY = scrollY.coerceAtMost(abs(scrollState.getY()))
                scrollY -= consumeY
                scrollState.consumeY(-consumeY)
            }
        }

        headerView.translationY = (-scrollY).toFloat()
        contentView.translationY = (headerView.height - scrollY).toFloat()
    }
}