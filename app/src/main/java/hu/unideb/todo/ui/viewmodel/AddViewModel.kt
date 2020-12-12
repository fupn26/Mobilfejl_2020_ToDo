package hu.unideb.todo.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import hu.unideb.todo.database.ToDoDatabase
import hu.unideb.todo.model.ToDoModel
import hu.unideb.todo.repository.ToDoRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class AddViewModel(application: Application): AndroidViewModel(application) {

    private val toDoRepository = ToDoRepository(ToDoDatabase.getDatabaseInstance(application))

    init {
        Timber.i("AddViewModel created")
    }

    fun addToDo(toDo: ToDoModel) {
        viewModelScope.launch {
            toDoRepository.insertToDo(toDo)
        }
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AddViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AddViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}