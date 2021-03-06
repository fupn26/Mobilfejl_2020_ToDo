package hu.unideb.todo.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import hu.unideb.todo.database.ToDoDatabase
import hu.unideb.todo.database.asDomainModel
import hu.unideb.todo.model.ToDoModel
import hu.unideb.todo.model.asToDoEntity
import hu.unideb.todo.network.ToDoApi
import hu.unideb.todo.network.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class ToDoRepository(private val database: ToDoDatabase) {

    val toDos: LiveData<List<ToDoModel>> = map(database.toDoDatabaseDao.findAll()) {
        it.asDomainModel()
    }

    private val _chosenToDo = MutableLiveData<ToDoModel>()
    val chosenToDo: LiveData<ToDoModel>
        get() = _chosenToDo

    suspend fun refreshToDos() {
        withContext(Dispatchers.IO) {
            Timber.d("refreshToDos function is called")
            val toDos = ToDoApi.retrofitService.getProperties()
            database.toDoDatabaseDao.insertAll(toDos.asDomainModel())
        }
    }

    suspend fun getToDoById(id: Long) {
        withContext(Dispatchers.IO) {
            Timber.d("get ToDo by Id: $id")
            _chosenToDo.postValue(database.toDoDatabaseDao.findById(id)?.asDomainModel())
        }
    }

    suspend fun updateToDo(toDo: ToDoModel) {
        withContext(Dispatchers.IO) {
            Timber.d("update ToDo: $toDo")
            database.toDoDatabaseDao.update(toDo.asToDoEntity())
        }
    }

    suspend fun insertToDo(toDo: ToDoModel) {
        withContext(Dispatchers.IO) {
            Timber.d("insertToDo function is called")
            database.toDoDatabaseDao.insert(
                toDo.asToDoEntity()
            )
        }
    }
}