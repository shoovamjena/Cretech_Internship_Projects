package com.example.todocretech.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("TODO")
class Todo (
    @PrimaryKey(autoGenerate = true)val id : Int=0,
    val title:String,
    val desc:String
)