package com.depi.testapp.ui.features.todolist.data.dao.data_entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val content: String,
    val createdAt: Long = System.currentTimeMillis(),
    var isDone: Boolean
)