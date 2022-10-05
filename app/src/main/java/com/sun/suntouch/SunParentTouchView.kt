package com.sun.suntouch

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout

/**
 * @author hengyangji
 * on 2022/10/5
 */
class SunParentTouchView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        SunLog.log("parent dispatchTouchEvent")
        return super.dispatchTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        SunLog.log("parent onInterceptTouchEvent")
        return super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        SunLog.log("parent onTouchEvent")
//        requestDisallowInterceptTouchEvent()
        return super.onTouchEvent(event)
    }
}