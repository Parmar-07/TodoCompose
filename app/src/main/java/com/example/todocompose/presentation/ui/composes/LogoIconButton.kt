package com.example.todocompose.presentation.ui.composes

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController


data class LogoIconButton(
    private val iconModifier: Modifier = Modifier,
    private val iconTintColor: Color? = null,
    private val onClick: () -> Unit
) :
    CreateCompose<ImageVector>() {
    @Composable
    override fun Generate(navController: NavController, data: ImageVector) {
        IconButton(
            modifier = iconModifier,
            onClick = onClick
        ) {
            Icon(
                tint = iconTintColor ?: data.tintColor,
                imageVector = data,
                contentDescription = this::class.simpleName
            )
        }
    }


}