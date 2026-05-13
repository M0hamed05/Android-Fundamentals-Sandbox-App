package com.depi.testapp.ui.features.notes.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.depi.testapp.ui.features.notes.data.data_entity.Note
import com.depi.testapp.ui.features.todolist.view_model.InstanceDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel : ViewModel(){
    val notesDao = InstanceDatabase.Companion.notesDatabase.getNotesDao()

    val notes = notesDao.getAllNotes()

    fun addNote(content:String){
        viewModelScope.launch(Dispatchers.IO) { //for input/output
            notesDao.addNote(Note(content = content))
        }
    }

    fun updateNote(note: Note){
        viewModelScope.launch (Dispatchers.IO){
            notesDao.updateNote(note)
        }
    }

    fun deleteNote(note: Note){
        viewModelScope.launch (Dispatchers.IO){
            notesDao.deleteNote(note)
        }
    }

}