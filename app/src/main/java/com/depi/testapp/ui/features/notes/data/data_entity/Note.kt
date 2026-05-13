package com.depi.testapp.ui.features.notes.data.data_entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,//must equals zero not 1
    val content: String,
    val createdAt: Long = System.currentTimeMillis()
)