package hu.unideb.todo.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ToDoDatabaseDao {

    @Insert
    fun insert(toDo: ToDoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( videos: List<ToDoEntity>)

    @Query("SELECT * from todo_table WHERE id = :key")
    fun findById(key: Long): ToDoEntity?

    @Query("SELECT * from todo_table")
    fun findAll(): LiveData<List<ToDoEntity>>

    @Update
    fun update(toDo: ToDoEntity)
}