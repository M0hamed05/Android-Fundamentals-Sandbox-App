package com.depi.testapp.ui.features.todolist.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.depi.testapp.ui.features.todolist.data.dao.data_entity.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel : ViewModel() {

    val toDoDao = InstanceDatabase.Companion.todoDatabase.getTodoDAO()

    val toDoList = toDoDao.getAllTodo()

    fun addTodo(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            toDoDao.addTodo(Todo(content = title, createdAt = System.currentTimeMillis(), isDone = false))
        }
    }

    fun deleteTodo(id: Int) {
        viewModelScope.launch(Dispatchers.IO){
            toDoDao.deleteTodoById(id)
        }
    }
    fun updateTodo(Todo: Todo){
        viewModelScope.launch(Dispatchers.IO) {
            toDoDao.updateTodo(Todo)
        }
    }
}