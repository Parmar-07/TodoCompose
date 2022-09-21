package com.example.todocompose.presentation.ui.composes

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController


data class FloatingIconButton(
    private val bgColor: Color,
    private val onClick: () -> Unit
) :
    CreateCompose<ImageVector>() {
    @Composable
    override fun Generate(navController: NavController, data: ImageVector) {
        FloatingActionButton(
            onClick, backgroundColor = bgColor
        ) {
            Icon(imageVector = data, contentDescription = data::class.java.simpleName)
        }
    }


}