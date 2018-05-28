package com.tapptitude.mvpsample.ui.common.widgets

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar

private const val MIN_SHOW_TIME_MS = 500
private const val MIN_DELAY_MS = 500

private const val PROGRESS_BAR_SIZE_DP = 55
private const val BACKGROUND_COLOR = Color.BLACK
private const val BACKGROUND_OPACITY_PERCENTAGE = 60

class ContentLoadingProgressLayout : FrameLayout {

    private var startTime: Long = -1

    private var postedHide = false
    private var postedShow = false
    private var dismissed = false

    private val delayedHide = Runnable {
        postedHide = false
        startTime = -1
        visibility = View.GONE
    }

    private val delayedShow = Runnable {
        postedShow = false
        if (!dismissed) {
            startTime = System.currentTimeMillis()
            visibility = View.VISIBLE
        }
    }

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        val progressBar = ProgressBar(context)
        val sizePx = context.resources.displayMetrics.density.toInt() * PROGRESS_BAR_SIZE_DP
        progressBar.isIndeterminate = true
        progressBar.layoutParams = LayoutParams(sizePx, sizePx, Gravity.CENTER)
        addView(progressBar)

        setBackgroundColor(BACKGROUND_COLOR)
        background.alpha = 255 * BACKGROUND_OPACITY_PERCENTAGE / 100
        isClickable = true
        visibility = View.GONE
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        removeCallbacks()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        removeCallbacks()
    }

    private fun removeCallbacks() {
        removeCallbacks(delayedHide)
        removeCallbacks(delayedShow)
    }

    /**
     * Hide the progress view if it is visible. The progress view will not be
     * hidden until it has been shown for at least a minimum show time. If the
     * progress view was not yet visible, cancels showing the progress view.
     */
    @Synchronized
    fun hide() {
        dismissed = true
        removeCallbacks(delayedShow)
        postedShow = false
        val diff = System.currentTimeMillis() - startTime
        if (diff >= MIN_SHOW_TIME_MS || startTime == -1L) {
            // The progress spinner has been shown long enough
            // OR was not shown yet. If it wasn't shown yet,
            // it will just never be shown.
            visibility = View.GONE
        } else {
            // The progress spinner is shown, but not long enough,
            // so put a delayed message in to hide it when its been
            // shown long enough.
            if (!postedHide) {
                postDelayed(delayedHide, MIN_SHOW_TIME_MS - diff)
                postedHide = true
            }
        }
    }

    /**
     * Show the progress view after waiting for a minimum delay. If
     * during that time, hide() is called, the view is never made visible.
     */
    @Synchronized
    fun show() {
        // Reset the start time.
        startTime = -1
        dismissed = false
        removeCallbacks(delayedHide)
        postedHide = false
        if (!postedShow) {
            postDelayed(delayedShow, MIN_DELAY_MS.toLong())
            postedShow = true
        }
    }
}