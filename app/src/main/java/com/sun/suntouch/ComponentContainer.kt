package com.sun.suntouch

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import android.view.ViewConfiguration
import android.widget.FrameLayout
import android.widget.Scroller
import androidx.core.view.ViewCompat
import com.sun.suntouch.consumer.IScrollConsumer
import com.sun.suntouch.consumer.createScrollState
import kotlin.math.abs

/**
 * @author hengyangji
 * on 2022/10/5
 */
class ComponentContainer @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {
    var scrollConsumers: List<IScrollConsumer> = emptyList()

    private var lastTouchX = 0.0f
    private var lastTouchY = 0.0f
    private var lastFlingY = 0
    private val touchSlop by lazy { ViewConfiguration.get(context).scaledTouchSlop }
    private val maxScrollVelocity by lazy { ViewConfiguration.get(context).scaledMaximumFlingVelocity }
    private val minScrollVelocity by lazy { ViewConfiguration.get(context).scaledMinimumFlingVelocity }
    private var velocityTracker: VelocityTracker? = null
    private val scroller by lazy { Scroller(context) }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        ev ?: return false
        when (ev.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                lastTouchX = ev.x
                lastTouchY = ev.y
                lastFlingY = 0
                scroller.abortAnimation()
                return false
            }

            MotionEvent.ACTION_MOVE -> {
                val diffY = lastTouchY - ev.y
                if (abs(diffY) < touchSlop) {
                    return false
                }
                return true
            }

            MotionEvent.ACTION_UP,
            MotionEvent.ACTION_CANCEL -> {
            }
        }

        return super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        ev ?: return true
        if (velocityTracker == null) {
            velocityTracker = VelocityTracker.obtain()
        }
        velocityTracker?.addMovement(ev)
//        log("onTouchEvent, action:${ev.action}, y:${ev.y}")
        when (ev.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                return true
            }

            MotionEvent.ACTION_MOVE -> {
                customScrollBy(0, (lastTouchY - ev.y).toInt())
                lastTouchX = ev.x
                lastTouchY = ev.y
            }

            MotionEvent.ACTION_UP -> {
                velocityTracker!!.computeCurrentVelocity(1000, maxScrollVelocity.toFloat())
                val velocity = velocityTracker!!.yVelocity
                if (abs(velocity) > minScrollVelocity) {
                    //do fling
                    log("start fling, velocity:${-velocity.toInt()}")
                    scroller.fling(0, 0, 0, -velocity.toInt(), 0, Int.MAX_VALUE, Int.MIN_VALUE, Int.MAX_VALUE)
                    ViewCompat.postInvalidateOnAnimation(this)
                }
                velocityTracker!!.recycle()
                velocityTracker = null
            }
        }
        return super.onTouchEvent(ev)
    }

    override fun computeScroll() {
        log("computeScroll, currY:${scroller.currY}")
        if (!scroller.computeScrollOffset()) {
            return
        }
        customScrollBy(0, scroller.currY - lastFlingY)
        ViewCompat.postInvalidateOnAnimation(this)
        lastFlingY = scroller.currY
    }

    fun customScrollBy(x: Int, y: Int) {
        val scrollState = createScrollState(x, y)
        if (y > 0) {
            scrollConsumers.forEach {
                it.onScroll(scrollState)
            }
        } else {
            scrollConsumers.asReversed().forEach {
                it.onScroll(scrollState)
            }
        }
    }

    private fun log(str: String) {
        Log.i("ComponentContainer", str)
    }

}