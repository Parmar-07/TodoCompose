package com.example.todocompose.presentation.ui.features.list.composes

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todocompose.domain.model.NoteItemModel
import com.example.todocompose.presentation.ui.composes.CreateCompose


data class NoteItemTexts(val textColor : Color) : CreateCompose<NoteItemModel>() {

    @Composable
    override fun Generate(
        navController: NavController, data: NoteItemModel
    ) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(end = 32.dp)
        ) {
            Text(
                text = data.title,
                style = MaterialTheme.typography.h6,
                color = textColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = data.content,
                style = MaterialTheme.typography.body1,
                color = textColor,
                maxLines = 10,
                overflow = TextOverflow.Ellipsis
            )
        }


    }


}