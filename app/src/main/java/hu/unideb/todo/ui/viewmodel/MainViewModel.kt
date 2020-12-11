package hu.unideb.todo.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import hu.unideb.todo.database.ToDoDatabase.Companion.getDatabaseInstance
import hu.unideb.todo.repository.ToDoRepository
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val toDoRepository = ToDoRepository(getDatabaseInstance(application))
    val toDoList = toDoRepository.toDos

    init {
        refreshDataFromRepository()
        Timber.i("MainViewModel created!")
    }

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                toDoRepository.refreshToDos()
            } catch (networkError: IOException) {
                Timber.e("Internet error")
            }
        }
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}