package com.example.KaranGreendzineApp

import android.R
import android.app.Activity
import android.graphics.Rect
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener


class KeyboardVisibilityUtils {
    interface KeyboardVisibilityListener {
        fun onKeyboardVisibilityChanged(isVisible: Boolean)
    }

    fun setKeyboardVisibilityListener(activity: Activity, listener: KeyboardVisibilityListener) {
        val activityRootView = activity.findViewById<View>(R.id.content)
        activityRootView.viewTreeObserver.addOnGlobalLayoutListener(object :
            OnGlobalLayoutListener {
            private val r = Rect()
            private var wasOpened = false
            override fun onGlobalLayout() {
                activityRootView.getWindowVisibleDisplayFrame(r)
                val heightDiff = activityRootView.rootView.height - (r.bottom - r.top)
                val isOpen = heightDiff > 200
                if (isOpen == wasOpened) {
                    return
                }
                wasOpened = isOpen
                listener.onKeyboardVisibilityChanged(isOpen)
            }
        })
    }
}