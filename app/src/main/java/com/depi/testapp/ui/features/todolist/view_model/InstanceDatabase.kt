package com.depi.testapp.ui.features.todolist.view_model

import android.app.Application
import androidx.room.Room
import com.depi.testapp.ui.features.notes.data.dao.NotesDatabase
import com.depi.testapp.ui.features.notes.model.NoteScreen
import com.depi.testapp.ui.features.todolist.data.dao.TodoDatabase

//creates the database
class InstanceDatabase : Application() { //inheritance form Application so we make sure
    // it's which works first before any other activity
    // and should be define in manifest the name of the class and path
    companion object { //like static
        lateinit var todoDatabase: TodoDatabase
        lateinit var notesDatabase: NotesDatabase
    }

    override fun onCreate() {
        super.onCreate()

        InstanceDatabase.todoDatabase = TodoDatabase.getDatabase(this)

        //for note apps don't know where to put the file
        InstanceDatabase.notesDatabase = NotesDatabase.getDatabase(this)
    }
}