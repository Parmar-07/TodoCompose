package com.example.todocompose.presentation.ui.features.add.composes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavController
import com.example.todocompose.presentation.ui.composes.CreateCompose
import com.example.todocompose.presentation.ui.features.add.model.AddNoteUIModel


object ContentEditText : CreateCompose<AddNoteUIModel>() {

    @Composable
    override fun Generate(
        navController: NavController,
        data: AddNoteUIModel
    ) {
        Box(
            modifier = Modifier.fillMaxHeight(),
        ) {
            BasicTextField(
                value = data.content.getStateValue() ?: "",
                onValueChange = {
                    data.content.onObserveDataValue(it)
                    data.contentHint.onObserveDataValue(it.isEmpty())

                },
                singleLine = true,
                textStyle = MaterialTheme.typography.body1,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(this::class.java.simpleName)


            )
            if (data.contentHint.getStateValue()!!) {
                Text(
                    text = "Enter Content",
                    style = MaterialTheme.typography.body1,
                    color = Color.DarkGray
                )
            }
        }
    }


}