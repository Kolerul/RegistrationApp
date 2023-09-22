package com.example.registrationapp

import android.app.Application
import com.example.registrationapp.di.AppComponent
import com.example.registrationapp.di.DaggerAppComponent

class RegistrationApp : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }
}