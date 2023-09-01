package com.foreverrafs.sample

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppComponent {
    @Provides
    fun provideRandomString(): String = "Hello There!"
}
