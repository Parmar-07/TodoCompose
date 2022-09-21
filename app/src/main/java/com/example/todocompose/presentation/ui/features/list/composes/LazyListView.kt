package com.example.todocompose.presentation.ui.features.list.composes

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todocompose.presentation.ui.composes.CreateCompose

class LazyListView<T>(private val listItems:List<T>) : CreateCompose<CreateCompose<T>>() {
    @Composable
    override fun Generate(navController: NavController, data: CreateCompose<T>) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(listItems){ itemValue->
                data.Generate(navController, itemValue)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}