package com.sun.suntouch.consumer

import androidx.recyclerview.widget.RecyclerView

/**
 * @author hengyangji
 * on 2022/10/7
 */
class RecyclerViewScrollConsumer(
    private val recyclerView: RecyclerView
) : IScrollConsumer {
    private val recyclerViewTracker = object : RecyclerView.OnScrollListener() {
        var consumeY = 0
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            consumeY = dy
        }
    }

    init {
        recyclerView.addOnScrollListener(recyclerViewTracker)
    }

    override fun onScroll(scrollState: IntArray) {
        recyclerView.scrollBy(0, scrollState.getY())
        scrollState.consumeY(recyclerViewTracker.consumeY)
    }
}