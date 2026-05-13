package com.depi.testapp.ui.features.todolist.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.depi.testapp.ui.features.todolist.data.dao.data_entity.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("SELECT * FROM TODO ORDER BY createdAt DESC")
    fun getAllTodo(): Flow<List<Todo>>

    @Query("SELECT * FROM TODO WHERE id = :id")
    fun getTodoById(id: Int): Todo

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTodo(todo: Todo)

    @Delete
     fun deleteTodo(todo: Todo)

    @Query("DELETE FROM TODO WHERE id = :id")
     fun deleteTodoById(id:Int)

    @Update
     fun updateTodo(todo: Todo)

}