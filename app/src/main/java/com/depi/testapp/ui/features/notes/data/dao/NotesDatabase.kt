package com.depi.testapp.ui.features.notes.data.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.depi.testapp.ui.features.notes.data.data_entity.Note
import com.depi.testapp.ui.features.notes.model.NoteScreen
import com.depi.testapp.ui.features.todolist.data.dao.TodoDatabase

@Database(entities = [Note::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {//should be inherited
    companion object {
    const val NAME = "Notes_db"

    @Volatile
    private var INSTANCE: NotesDatabase? = null

    fun getDatabase(context: Context): NotesDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                NotesDatabase::class.java,
                NotesDatabase.Companion.NAME
            ).fallbackToDestructiveMigration()
                .//because if there a change in the schema/or delete the app and reinstall it again
                build()
            INSTANCE = instance
            instance
        }
    }
}
    abstract fun getNotesDao(): NotesDao
}