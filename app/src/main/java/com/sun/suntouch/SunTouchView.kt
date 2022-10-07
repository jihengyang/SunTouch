package com.sun.suntouch

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast

/**
 * @author hengyangji
 * on 2022/10/5
 */
class SunTouchView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    init {
//        setOnTouchListener(object : OnTouchListener {
//            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//                Toast.makeText(context, "点我了", Toast.LENGTH_LONG).show()
//                return true
//            }
//        })
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        SunLog.log("dispatchTouchEvent, action:${ev.actionMasked}")
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        SunLog.log("onTouchEvent, action:${ev.actionMasked}")
        if (ev.actionMasked == MotionEvent.ACTION_DOWN) {
            return true
        }
        return super.onTouchEvent(ev)
    }
}