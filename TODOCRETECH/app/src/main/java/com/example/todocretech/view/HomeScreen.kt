package com.example.todocretech.view

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todocretech.model.Todo
import com.example.todocretech.viewModel.TodoViewModel

@Composable
fun HomeScreen(viewModel: TodoViewModel){
    val todo by viewModel.todo.collectAsState()
    var addDialog by remember { mutableStateOf(false) }
    var updateDialog by remember { mutableStateOf(false) }
    var title by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }
    var selectedTodo by remember { mutableStateOf<Todo?>(null) }
    val isAndroid12OrAbove = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val backgroundColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
    val contentColor = MaterialTheme.colorScheme.primaryContainer

    fun reset(){
        title =""
        desc = ""
    }

    if(addDialog){
        AddTodo(
            title = title,
            onTitleChange = { title = it.uppercase() },
            desc = desc,
            onDescChange = { desc = it },
            onConfirm = {
                viewModel.insertTodo(title, desc)
                addDialog = false
                reset()
            },
            onDismiss = { addDialog = false},
            containerColor = contentColor,
            isAndroid12OrAbove = isAndroid12OrAbove
        )
    }

    if (updateDialog){
        UpdateTodo(
            title = title,
            onTitleChange = { title = it.uppercase() },
            desc = desc,
            onDescChange = { desc = it },
            onConfirm = {
                selectedTodo?.let { todo -> // Null safety check
                    viewModel.updateTodo(
                        id = todo.id,
                        title = title,
                        desc = desc
                    )
                    updateDialog = false
                    reset()
                }
            },
            onDismiss = { updateDialog = false},
            containerColor = contentColor,
            isAndroid12OrAbove = isAndroid12OrAbove
        )
    }
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { _ ->
        Box(modifier = Modifier
            .fillMaxSize().padding(vertical = 50.dp)){
            Text(
                "TO-DO APP",
                fontSize = 42.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.TopCenter)
            )
            Box(modifier = Modifier.fillMaxWidth().heightIn(max = 800.dp).padding(horizontal = 10.dp, vertical = 50.dp).clip(RoundedCornerShape(30.dp)).background(
                if (todo.isEmpty()) {Color.Transparent} else backgroundColor)){
                if (todo.isEmpty()) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Click the + icon to add subject",
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.primary,
                        )
                    }
                }
                LazyColumn(
                    state = rememberLazyListState()
                ) {
                    items(todo, key = {it.id}){todo->
                        TodoItem(
                            todo.title,todo.desc,
                            onDelete = {
                                viewModel.deleteTodo(todo)
                            },
                            onUpdate = {
                                updateDialog = true
                                selectedTodo = todo
                                title = todo.title
                                desc = todo.desc
                            },
                            color = contentColor
                        )
                    }
                }
            }
            IconButton(
                onClick = { addDialog = true},
                modifier = Modifier.align(Alignment.BottomEnd).padding(end = 50.dp).size(50.dp),
                colors = IconButtonDefaults.iconButtonColors(contentColor)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        }
    }
}