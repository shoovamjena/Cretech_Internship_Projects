package com.example.todocretech.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todocretech.model.Todo
import com.example.todocretech.model.TodoDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TodoViewModel(private val todoDao: TodoDao): ViewModel(){
    private val _todo = MutableStateFlow<List<Todo>>(emptyList())
    val todo = _todo.asStateFlow()

    init {
        loadTodo()
    }

    private fun loadTodo(){
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.getAllTodo().collect{todolist->
                _todo.value=todolist
            }
        }
    }

    fun insertTodo(title:String,desc:String){
        viewModelScope.launch(Dispatchers.IO) {
            val todo =Todo(title = title, desc = desc)
            todoDao.InsertTodo(todo)
        }
    }
    fun deleteTodo(todo: Todo){
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.DeleteTodo(todo)
        }
    }
    fun updateTodo(title: String,desc: String){
        viewModelScope.launch(Dispatchers.IO) {
            val todo = Todo(title = title, desc = desc)
            todoDao.UpdateTodo(todo)
        }
    }
}