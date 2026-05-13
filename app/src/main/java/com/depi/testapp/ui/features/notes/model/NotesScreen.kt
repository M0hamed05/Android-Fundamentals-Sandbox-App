package com.depi.testapp.ui.features.notes.model

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.depi.testapp.ui.features.notes.data.data_entity.Note
import com.depi.testapp.ui.features.notes.view_model.NotesViewModel
import com.depi.testapp.ui.features.todolist.data.dao.data_entity.Todo
import com.depi.testapp.ui.features.todolist.model.TodoItem
import com.depi.testapp.ui.features.todolist.view_model.TodoViewModel
import com.depi.testapp.ui.theme.mainBlackColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

@Composable
fun NoteScreen(viewModel: NotesViewModel = viewModel()){
    //should be by here
    val notes by viewModel.notes.collectAsStateWithLifecycle(emptyList()) //up to date function
    var showAddDialog by remember { mutableStateOf(false) }

    var content by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    if(showAddDialog) {
        AlertDialog(
            onDismissRequest = { showAddDialog = false },
            title = { Text(text = "Add Note") },
            text = {
                OutlinedTextField(
                    value = content,
                    onValueChange = { content = it },
                    placeholder = { Text("Note content...") },
                    singleLine = true,//make the text in one single line
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                Button(onClick = {
                    if(content.isNotBlank()){
                        viewModel.addNote(content)
                        content = ""
                        showAddDialog = false
                    }
                }) {Text("Add") }
            },
            dismissButton = {
                Button(onClick = {showAddDialog = false}) {
                    Text("Dismiss")
                }
            }
        )
    }

    Scaffold(
        modifier = Modifier.padding(bottom = 80.dp).padding(top = 70.dp),//for the top and bottom navigationbar
        containerColor = Color.White,
        snackbarHost = {SnackbarHost(hostState = snackbarHostState)},
        floatingActionButton = {
            FloatingActionButton(
                onClick = {showAddDialog = true},
                shape = CircleShape,
            ) {
                Icon(Icons.Default.Add, contentDescription = "add note")
            }
        }
    ) { PaddingValues ->
        Column(
            modifier = Modifier.padding(PaddingValues).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            LazyColumn {
                items(items = notes, key = {it.id}){ note->
                    NoteItem(note,viewModel,snackbarHostState, scope)
                }
            }
        }
    }
}

@Composable
fun NoteItem(item: Note,viewModel: NotesViewModel,snackbarHostState: SnackbarHostState,scope: CoroutineScope) {
    var showEditDialog by remember { mutableStateOf(false) }
    var content by remember { mutableStateOf("") }
    val context = LocalContext.current

    var lastDeletednoteContent:String

    if (showEditDialog) {
        AlertDialog(
            onDismissRequest = { showEditDialog = false },
            title = { Text(text = "Edit Note") },
            text = {
                OutlinedTextField(
                    value = content,
                    onValueChange = { content = it },
                    placeholder = { Text("Note content...") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (content.isNotBlank()) {
                            viewModel.updateNote(item.copy(content = content))
                            content = ""
                            showEditDialog = false
                            Toast.makeText(
                                context,
                                "Note Updated successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                ) { Text("Ok") }
            },
            dismissButton = {
                Button(onClick = { showEditDialog = false }) { Text("Cancel") }
            }
        )
    }

    Row(
        modifier = Modifier.fillMaxWidth().padding(12.dp).clip(RoundedCornerShape(13.dp))
            .background(MaterialTheme.colorScheme.surfaceBright).padding(16.dp)
            .clickable(enabled = true, onClick = { showEditDialog = true }),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = item.content, fontSize = 14.sp, color = mainBlackColor)
        }
        IconButton(onClick = {
            lastDeletednoteContent = item.content
            viewModel.deleteNote(item)
            scope.launch {
                val result = snackbarHostState.showSnackbar(
                    "Note deleted successfully", actionLabel = "UNDO",
                    duration = SnackbarDuration.Short
                )
                when (result) {
                    SnackbarResult.ActionPerformed -> {
                        viewModel.addNote(lastDeletednoteContent)
                    }

                    SnackbarResult.Dismissed -> {

                    }
                }
            }
        }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "")
        }
    }
}


