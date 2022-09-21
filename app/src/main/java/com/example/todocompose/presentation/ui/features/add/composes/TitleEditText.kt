package com.example.todocompose.presentation.ui.features.add.composes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
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


object TitleEditText : CreateCompose<AddNoteUIModel>() {

    @Composable
    override fun Generate(
        navController: NavController,
        data: AddNoteUIModel
    ) {
        Box(
            modifier =
            Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
        ) {
            BasicTextField(
                value = data.title.getStateValue() ?: "",
                onValueChange = {
                    data.title.onObserveDataValue(it)
                    data.titleHint.onObserveDataValue(it.isEmpty())

                },
                singleLine = true,
                textStyle = MaterialTheme.typography.h5,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(this::class.java.simpleName)

            )
            if (data.titleHint.getStateValue()!!) {
                Text(
                    text = "Enter Title",
                    style = MaterialTheme.typography.h5,
                    color = Color.DarkGray
                )
            }
        }
    }


}