package hu.unideb.todo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ToDoEntity::class], version = 1)
abstract class ToDoDatabase : RoomDatabase() {

    abstract val toDoDatabaseDao: ToDoDatabaseDao

    companion object {

        private lateinit var INSTANCE: ToDoDatabase

        fun getInstance(context: Context): ToDoDatabase {
            synchronized(this) {

                if (!::INSTANCE.isInitialized) {
                    INSTANCE  = Room.databaseBuilder(
                        context.applicationContext,
                        ToDoDatabase::class.java,
                        "todo_table"
                    )
                        .build()
                }

                return INSTANCE
            }
        }
    }
}