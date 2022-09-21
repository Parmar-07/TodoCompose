package com.example.todocompose.presentation.ui.features.list.composes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.todocompose.presentation.ui.composes.CreateCompose
import com.example.todocompose.presentation.ui.composes.LogoIconButton
import com.example.todocompose.presentation.ui.features.list.model.NoteListUIModel


object SortIcon : CreateCompose<NoteListUIModel>() {

    @Composable
    override fun Generate(
        navController: NavController, data: NoteListUIModel
    ) {

        LogoIconButton(
            iconTintColor = Color.White,
            onClick = {
            data.showSort.onObserveDataValue(!data.showSort.uiData.value!!)

        }).Generate(navController, Icons.Default.Sort)

    }


}