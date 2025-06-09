package com.example.todocretech.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert
    fun InsertTodo(todo: Todo)

    @Update
    fun UpdateTodo(todo: Todo)

    @Delete
    fun DeleteTodo(todo: Todo)

    @Query("SELECT * FROM todo ORDER BY id DESC")
    fun getAllTodo(): Flow<List<Todo>>
}