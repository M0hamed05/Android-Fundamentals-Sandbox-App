package com.depi.testapp.ui.features.todolist.data.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.depi.testapp.ui.features.todolist.data.dao.data_entity.Todo

@Database(entities =[Todo::class], version = 1)
abstract class TodoDatabase : RoomDatabase(){
    companion object {
        const val NAME = "TODO_DB"

        @Volatile
        private var INSTANCE: TodoDatabase? = null

        fun getDatabase(context: Context): TodoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    NAME
                ).fallbackToDestructiveMigration().build() //if there change in the schema
                INSTANCE = instance
                instance
            }
        }
    }
    abstract fun getTodoDAO(): TodoDao
}