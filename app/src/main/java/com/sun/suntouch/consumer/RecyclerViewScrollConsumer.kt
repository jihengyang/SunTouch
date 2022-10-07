package com.sun.suntouch.consumer

import androidx.recyclerview.widget.RecyclerView

/**
 * @author hengyangji
 * on 2022/10/7
 */
class RecyclerViewScrollConsumer(
    private val recyclerView: RecyclerView
) : IScrollConsumer {
    override fun onScroll(scrollState: IntArray) {
        recyclerView.scrollBy(0, scrollState.getY())
        scrollState.consumeY(scrollState.getY())
    }
}