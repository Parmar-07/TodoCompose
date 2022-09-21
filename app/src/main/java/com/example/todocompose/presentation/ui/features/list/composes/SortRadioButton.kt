package com.example.todocompose.presentation.ui.features.list.composes

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todocompose.presentation.ui.composes.CreateCompose

data class SortRadioButton(val radioText: String, val selected: Boolean, val onClick: () -> Unit) :
    CreateCompose<Unit>() {
    @Composable
    override fun Generate(navController: NavController, data: Unit) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected,
                onClick,
                colors = RadioButtonDefaults.colors(
                    selectedColor = MaterialTheme.colors.primary,
                    unselectedColor = MaterialTheme.colors.onBackground
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = radioText,
                style = MaterialTheme.typography.body1
            )
        }

    }
}