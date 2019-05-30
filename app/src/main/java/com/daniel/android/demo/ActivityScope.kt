package com.daniel.android.demo

import javax.inject.Scope

/**
 * @author wuyang
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class ActivityScope