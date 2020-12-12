package hu.unideb.todo.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import hu.unideb.todo.database.ToDoDatabase
import hu.unideb.todo.repository.ToDoRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class UpdateViewModel(application: Application, toDoId: Long): AndroidViewModel(application) {

    private val toDoRepository = ToDoRepository(ToDoDatabase.getDatabaseInstance(application))
    val toDo = toDoRepository.chosenToDo

    init {
        refreshToDoObjectFromRepository(toDoId)
        Timber.i("UpdateViewModel created")
    }

    private fun refreshToDoObjectFromRepository(toDoId: Long) {
        viewModelScope.launch {
            toDoRepository.getToDoById(toDoId)
        }
    }

    class Factory(val app: Application, val toDoId: Long) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(UpdateViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return UpdateViewModel(app, toDoId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}