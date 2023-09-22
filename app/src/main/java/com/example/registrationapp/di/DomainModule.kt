package com.example.registrationapp.di

import com.example.registrationapp.data.repository.UserDataRepositoryImpl
import com.example.registrationapp.domain.repository.UserDataRepository
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {
    @Binds
    fun bindUserDataRepository(repositoryImpl: UserDataRepositoryImpl): UserDataRepository


}