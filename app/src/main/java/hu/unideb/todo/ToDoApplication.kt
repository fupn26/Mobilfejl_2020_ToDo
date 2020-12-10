package hu.unideb.todo

import android.app.Application
import timber.log.Timber

class ToDoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}