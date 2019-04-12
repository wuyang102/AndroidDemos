package com.daniel.android.demo.coordinate

import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.view.View
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import android.animation.ValueAnimator
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import org.jetbrains.anko.warn


/**
 * @author wuyang
 */
const val TAG = "overScroll"
const val TARGET_HEIGHT = 260f

class AppBarLayoutOverScrollBehavior(context: Context?, attrs: AttributeSet?) : AppBarLayout.Behavior(context, attrs),
    AnkoLogger {

    constructor(context: Context?) : this(context, null)

    private var mTargetView: View? = null
    private var mTargetViewTopMargin: Int = 0
    private var mTotalDy: Float = 0f

    private var count = 0

    override fun onLayoutChild(parent: CoordinatorLayout?, abl: AppBarLayout?, layoutDirection: Int): Boolean {
        val result = super.onLayoutChild(parent, abl, layoutDirection)
        if (null == mTargetView) {
            mTargetView = parent!!.findViewWithTag(TAG)
            if (null != mTargetView) {
                mTargetViewTopMargin = getTargetViewTopMargin()
            }
        }
        return result
    }

    override fun onStartNestedScroll(
        parent: CoordinatorLayout,
        child: AppBarLayout,
        directTargetChild: View,
        target: View,
        nestedScrollAxes: Int,
        type: Int
    ): Boolean {
        warn("onStartNestedScroll: nestedScrollAxes = $nestedScrollAxes, target = ${target.javaClass}")
        count++
        return super.onStartNestedScroll(parent, child, directTargetChild, target, nestedScrollAxes, type)
    }


    override fun onNestedScrollAccepted(
        coordinatorLayout: CoordinatorLayout,
        child: AppBarLayout,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ) {
        info("onNestedScrollAccepted: axes = $axes")
        super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, axes, type)
    }

    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: AppBarLayout,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {
        info("onNestedPreScroll: dx = $dx, dy = $dy, consumed = ${consumed.contentToString()}")
        if (mTargetView != null && ((dy < 0 && getTargetViewTopMargin() >= mTargetViewTopMargin) || (dy > 0 && getTargetViewTopMargin() > mTargetViewTopMargin))) {
            handleOverScroll(child, target, dy / 2)
        } else {
            super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
        }
    }

    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: AppBarLayout,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int
    ) {
        info("onNestedScroll: dxConsumed = $dxConsumed,  dyConsumed = $dyConsumed, dxUnconsumed = $dxUnconsumed, dyUnconsumed = $dyUnconsumed")
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type)
    }

    override fun onNestedPreFling(
        coordinatorLayout: CoordinatorLayout,
        child: AppBarLayout,
        target: View,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        info("onNestedPreFling: velocityX = $velocityX, velocityY = $velocityY")
        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY)
    }

    override fun onNestedFling(
        coordinatorLayout: CoordinatorLayout,
        child: AppBarLayout,
        target: View,
        velocityX: Float,
        velocityY: Float,
        consumed: Boolean
    ): Boolean {
        info("onNestedFling: velocityX = $velocityX, velocityY = $velocityY")
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed)
    }

    override fun onStopNestedScroll(coordinatorLayout: CoordinatorLayout, abl: AppBarLayout, target: View, type: Int) {
        warn("onStopNestedScroll")
        count--
        if (null != mTargetView && count == 0) recovery(abl)
        super.onStopNestedScroll(coordinatorLayout, abl, target, type)
    }

    private fun handleOverScroll(abl: AppBarLayout, target: View, dy: Int) {
        mTotalDy += -dy
        mTotalDy = Math.min(mTotalDy, TARGET_HEIGHT)
        setTargetViewTopMargin(mTotalDy.toInt())
        target.scrollY = 0
    }

    private fun recovery(abl: AppBarLayout) {
        if (mTotalDy > 0) {
            warn("recovery with animation")
            val anim = ValueAnimator.ofFloat(mTotalDy, 0f).setDuration(200)
            anim.interpolator = DecelerateInterpolator()
            anim.addUpdateListener { animation -> setTargetViewTopMargin((animation.animatedValue as Float).toInt()) }
            anim.start()
            mTotalDy = 0f
        }
    }

    private fun getTargetViewTopMargin(): Int {
        return (mTargetView!!.layoutParams as ViewGroup.MarginLayoutParams).topMargin
    }

    private fun setTargetViewTopMargin(top: Int) {
        (mTargetView!!.layoutParams as ViewGroup.MarginLayoutParams).topMargin = top + mTargetViewTopMargin
        mTargetView!!.requestLayout()
    }
}