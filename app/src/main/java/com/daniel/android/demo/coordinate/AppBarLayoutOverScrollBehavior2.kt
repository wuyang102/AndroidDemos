package com.daniel.android.demo.coordinate

import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.view.View
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.warn


/**
 * @author wuyang
 */
class AppBarLayoutOverScrollBehavior2(context: Context?, attrs: AttributeSet?) : AppBarLayout.Behavior(context, attrs),
    AnkoLogger {

    constructor(context: Context?) : this(context, null)

    override fun onStartNestedScroll(
        parent: CoordinatorLayout,
        child: AppBarLayout,
        directTargetChild: View,
        target: View,
        nestedScrollAxes: Int,
        type: Int
    ): Boolean {
        warn("onStartNestedScroll: nestedScrollAxes = $nestedScrollAxes, target = ${target.javaClass}")
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
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
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
        super.onStopNestedScroll(coordinatorLayout, abl, target, type)
    }

}