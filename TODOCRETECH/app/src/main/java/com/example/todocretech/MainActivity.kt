package com.example.todocretech

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.todocretech.model.TodoDatabase
import com.example.todocretech.ui.theme.TODOCRETECHTheme
import com.example.todocretech.view.HomeScreen
import com.example.todocretech.viewModel.TodoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val todoDatabase = TodoDatabase.getDatabase(applicationContext)
        val viewModel = TodoViewModel(todoDatabase.todoDao())
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TODOCRETECHTheme {
                HomeScreen(viewModel)
            }
        }
    }
}