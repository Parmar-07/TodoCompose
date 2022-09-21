package com.example.todocompose.presentation.ui.features.list.composes

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.todocompose.presentation.ui.composes.CreateCompose


object NoteListTitleText : CreateCompose<Unit>() {

    @Composable
    override fun Generate(
        navController: NavController, data: Unit
    ) {
        Text(
            text = "Your Notes",
            style = MaterialTheme.typography.h4
        )
    }


}