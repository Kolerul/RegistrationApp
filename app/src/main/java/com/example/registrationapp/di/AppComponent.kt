package com.example.registrationapp.di

import android.content.Context
import com.example.registrationapp.presentation.viewmodel.ViewModelFactory
import com.example.registrationapp.ui.fragment.RegistrationFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DomainModule::class,
        PresentationModule::class,
        CoroutineModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(fragment: RegistrationFragment)
    fun viewModelFactory(): ViewModelFactory
}