package com.example.registrationapp.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
class CoroutineModule {
    @Provides
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}