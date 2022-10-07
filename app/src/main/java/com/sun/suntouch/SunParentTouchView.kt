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
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        SunLog.log("parent dispatchTouchEvent, action:${ev.actionMasked}")
        return super.dispatchTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        SunLog.log("parent onInterceptTouchEvent, action:${ev.actionMasked}")
//        if (ev.actionMasked == MotionEvent.ACTION_DOWN) {
//            return true
//        }
        if (ev.actionMasked == MotionEvent.ACTION_DOWN) {
            return true
        }
        return super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        SunLog.log("parent onTouchEvent, action:${ev.actionMasked}")
//        requestDisallowInterceptTouchEvent()
        if (ev.actionMasked == MotionEvent.ACTION_DOWN) {
            return true
        }
        return super.onTouchEvent(ev)
    }
}