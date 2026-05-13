package com.depi.testapp.ui.features.todolist.model


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
import com.depi.testapp.ui.features.todolist.data.dao.data_entity.Todo
import com.depi.testapp.ui.features.todolist.view_model.TodoViewModel
import com.depi.testapp.ui.theme.mainBlackColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TodoListScreen(viewModel: TodoViewModel = viewModel()) {
    val todos by viewModel.toDoList.collectAsStateWithLifecycle(emptyList())
    var showAddDialog by remember { mutableStateOf(false) }

    var title by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    if(showAddDialog) {
        AlertDialog(
            onDismissRequest = { showAddDialog = false },
            title = { Text(text = "Add task") },
            text = {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    placeholder = { Text("Task title...") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                Button(onClick = {
                    if(title.isNotBlank()){
                        viewModel.addTodo(title)
                        title = ""
                        showAddDialog = false
                    }
                }) {Text("Add") }
            }
        )
    }

    Scaffold(
        modifier = Modifier.padding(bottom = 80.dp).padding(top = 70.dp),
                containerColor = Color.White,
        snackbarHost = {SnackbarHost(hostState = snackbarHostState)},
        floatingActionButton = {
            FloatingActionButton(
                onClick = {showAddDialog = true},
                shape = CircleShape,
            ) {
                Icon(Icons.Default.Add, contentDescription = "add task")
            }
        }
    ) { PaddingValues ->
        Column(
            modifier = Modifier.padding(PaddingValues).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            LazyColumn {
                items(todos, key = {it.id}){ todo->
                TodoItem(todo,viewModel,snackbarHostState,scope)
                }
            }
        }
    }
}

@Composable
fun TodoItem(item:Todo,viewModel: TodoViewModel,SnakbarHostState: SnackbarHostState,scope: CoroutineScope) {
    var showEditDialog by remember { mutableStateOf(false) }

    var title by remember { mutableStateOf(item.content) }
    val context = LocalContext.current

    var lastDeletedTaskTitle: String


    if (showEditDialog) {
        AlertDialog(
            onDismissRequest = { showEditDialog = false },
            title = { Text(text = "Edit task") },
            text = {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    placeholder = { Text("Task title...") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (title.isNotBlank()) {
                            viewModel.updateTodo(item.copy(content = title))
                            title = ""
                            showEditDialog = false
                            Toast.makeText(context, "Task Updated successfully", Toast.LENGTH_SHORT)
                                .show()

                        }

                    },
                ) { Text("Ok") }
            },
        )
    }

    Row(
        modifier = Modifier.fillMaxWidth().padding(12.dp).clip(RoundedCornerShape(13.dp))
            .background(MaterialTheme.colorScheme.surfaceBright).padding(16.dp)
            .clickable(enabled = true, onClick = { showEditDialog = true }),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = item.isDone, onCheckedChange = { isChecked ->
            viewModel.updateTodo(
                item.copy(isDone = isChecked)
            )
        })
        Column(modifier = Modifier.weight(1f)) {
            Text(text = item.content, fontSize = 14.sp, color = mainBlackColor)
        }
        IconButton(onClick = {
            lastDeletedTaskTitle = item.content
            viewModel.deleteTodo(item.id)
            scope.launch {
                val result = SnakbarHostState.showSnackbar(
                    "Task Deleted Successfully",
                    actionLabel = "UNDO",
                    duration = SnackbarDuration.Short
                )
                when (result) {
                    SnackbarResult.ActionPerformed -> {
                        viewModel.addTodo(lastDeletedTaskTitle)
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

