package com.example.todocompose.presentation.ui.features.list.composes

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todocompose.presentation.ui.composes.CreateCompose
import com.example.todocompose.presentation.ui.features.list.NoteListViewModel
import com.example.todocompose.presentation.ui.features.list.model.NoteListUIModel
import com.example.todocompose.presentation.ui.features.list.model.OrderBy
import com.example.todocompose.presentation.ui.features.list.model.SortBy
import com.example.todocompose.presentation.ui.features.list.model.SortVia


data class SortSection(val noteListViewModel: NoteListViewModel) : CreateCompose<NoteListUIModel>() {


    @Composable
    private fun CreateTitleRadio(
         navController: NavController,
        data: NoteListUIModel
    ) {

        val radioButton = SortRadioButton("Title", data.sortVia.getStateValue() is OrderBy.Title) {
            val selected = OrderBy.Title(data.sortVia.uiData.value!!.sortOrderBy)
            noteListViewModel.onOrderSelect(selected)

        }
        radioButton.Generate(navController, Unit)
    }



    @Composable
    private fun CreateDateRadio(navController: NavController, data: NoteListUIModel) {

        val radioButton = SortRadioButton("Date", data.sortVia.getStateValue() is OrderBy.Date) {
            val selected = OrderBy.Date(data.sortVia.uiData.value!!.sortOrderBy)
            noteListViewModel.onOrderSelect(selected)
        }
        radioButton.Generate(navController, Unit)
    }

    @Composable
    private fun CreateColorRadio(navController: NavController, data: NoteListUIModel) {

        val radioButton = SortRadioButton("Color", data.sortVia.getStateValue() is OrderBy.Color) {
            val selected = OrderBy.Color(data.sortVia.uiData.value!!.sortOrderBy)
            noteListViewModel.onOrderSelect(selected)
        }
        radioButton.Generate(navController, Unit)
    }

    @Composable
    private fun CreateAscRadio(navController: NavController, data: NoteListUIModel) {

        val titleRadio = SortRadioButton("ASC", data.sortVia.getStateValue()!!.sortOrderBy is SortBy.ASC) {
            val selected = data.sortVia.uiData.value!!.copyCurrent(SortBy.ASC)
            noteListViewModel.onOrderSelect(selected)
        }
        titleRadio.Generate(navController, Unit)
    }
    @Composable
    private fun CreateDescRadio(navController: NavController, data: NoteListUIModel) {

        val radioButton = SortRadioButton("DESC", data.sortVia.getStateValue()!!.sortOrderBy is SortBy.DESC) {
            val selected = data.sortVia.uiData.value!!.copyCurrent(SortBy.DESC)
            noteListViewModel.onOrderSelect(selected)
        }
        radioButton.Generate(navController, Unit)
    }


    @Composable
    override fun Generate(
        navController: NavController, data: NoteListUIModel
    ) {


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .testTag(this::class.java.simpleName)
        ) {

            Row(modifier = Modifier.fillMaxWidth()) {
                CreateTitleRadio(navController, data)
                Spacer(modifier = Modifier.width(8.dp))
                CreateDateRadio(navController, data)
                Spacer(modifier = Modifier.width(8.dp))
                CreateColorRadio(navController, data)
            }
            Spacer(modifier = Modifier.width(10.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                CreateAscRadio(navController, data)
                Spacer(modifier = Modifier.width(8.dp))
                CreateDescRadio(navController, data)
            }
        }

    }

}