package com.sun.suntouch.consumer

/**
 * @author hengyangji
 * on 2022/10/7
 */
interface IScrollConsumer {
    fun onScroll(scrollState: IntArray)
}

private const val SCROLL_BY_X_INDEX = 0
private const val SCROLL_BY_Y_INDEX = 1
fun createScrollState(scrollByX: Int, scrollByY: Int): IntArray {
    return IntArray(2).apply {
        this[SCROLL_BY_X_INDEX] = scrollByX
        this[SCROLL_BY_Y_INDEX] = scrollByY
    }
}

fun IntArray.getY() = this[SCROLL_BY_Y_INDEX]
fun IntArray.consumeY(consume: Int) {
    this[SCROLL_BY_Y_INDEX] = this[SCROLL_BY_Y_INDEX] - consume
}

fun IntArray.getX() = this[SCROLL_BY_X_INDEX]