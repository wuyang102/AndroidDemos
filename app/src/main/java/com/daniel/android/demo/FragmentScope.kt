package com.daniel.android.demo

import javax.inject.Scope

/**
 * @author wuyang
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
annotation class FragmentScope