package com.sun.suntouch

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * @author hengyangji
 * on 2022/10/5
 */
class SunTouchView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        SunLog.log("dispatchTouchEvent, action:${ev.actionMasked}")
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        SunLog.log("onTouchEvent, action:${ev.actionMasked}")
        return super.onTouchEvent(ev)
    }
}