package com.daniel.android.library.hilt

import dagger.BindsInstance
import dagger.hilt.DefineComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Scope

@DefineComponent(parent = SingletonComponent::class)
@TestScope
interface MyHiltComponent {

    @DefineComponent.Builder
    interface MyHiltComponentBuilder {
        @BindsInstance
        fun name(name: String): MyHiltComponentBuilder

        fun build(): MyHiltComponent
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
annotation class TestScope