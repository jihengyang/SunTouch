package com.sun.suntouch

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.widget.FrameLayout
import kotlin.math.abs

/**
 * @author hengyangji
 * on 2022/10/5
 */
class ComponentContainer @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {
    var headView: View? = null
    var hangingView: View? = null
    var contentView: View? = null

    private var lastTouchX = 0.0f
    private var lastTouchY = 0.0f
    private val touchSlop by lazy { ViewConfiguration.get(context).scaledTouchSlop }
    private var scrollY = 0.0f

    fun doBinding() {
        contentView?.translationY = headView?.height?.toFloat() ?: 0.0f
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        ev ?: return false
        log("onInterceptTouchEvent, action:${ev.action}")
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                lastTouchX = ev.x
                lastTouchY = ev.y
                return false
            }

            MotionEvent.ACTION_MOVE -> {
                if (canScroll()) {
                    val diffY = ev.y - lastTouchY
                    if (diffY < 0) { //上划
                        scrollY -= diffY
                        headView!!.translationY = -scrollY
                        contentView!!.translationY = headView!!.height - scrollY
                    }
                    return true
                }
            }

            MotionEvent.ACTION_UP,
            MotionEvent.ACTION_CANCEL -> {

            }
        }

        return super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        ev ?: return true
        log("onTouchEvent, action:${ev.action}")
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
//                lastTouchX = ev.x
//                lastTouchY = ev.y
//                if (canScroll()) {
//                    return true
//                }
            }

            MotionEvent.ACTION_MOVE -> {
                val diffY = ev.y - lastTouchY
//                Log.i("ComponentContainer", "diffY:$diffY")
//                val canScroll = headView!!.height - hangingView!!.height - scrollY > diffY
//                if (canScroll) {
//                    scrollY = scaleY
//                    headView!!.translationY = scrollY + diffY
//                    contentView?.translationY = headView!!.height + headView!!.translationY
//                }
                lastTouchX = ev.x
                lastTouchY = ev.y
                return true
            }

            MotionEvent.ACTION_UP,
            MotionEvent.ACTION_CANCEL -> {

            }
        }
        return super.onTouchEvent(ev)
    }

    private fun canScroll() = headView!!.height - hangingView!!.height > scrollY

    fun log(str: String) {
        Log.i("ComponentContainer", str)
    }


}