package com.example.todocompose.presentation.ui.features.list.composes

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todocompose.presentation.ui.composes.CreateCompose
import com.example.todocompose.presentation.ui.composes.FloatingIconButton
import com.example.todocompose.presentation.ui.features.list.NoteListEvents
import com.example.todocompose.presentation.ui.features.list.NoteListViewModel
import com.example.todocompose.presentation.ui.features.list.model.NoteListUIModel
import com.example.todocompose.presentation.ui.navigators.AppRoute
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class NoteListScreen : CreateCompose<NoteListUIModel>() {


    @Composable
    override fun Generate(navController: NavController, data: NoteListUIModel) {
        val viewModel: NoteListViewModel = hiltViewModel()

        val scaffoldState = rememberScaffoldState()
        val addIcon = FloatingIconButton(bgColor = MaterialTheme.colors.primary) {
            navController.navigate(AppRoute.AddNoteRoute.routeName)
        }
        val scope = rememberCoroutineScope()
        viewModel.getNotes(data.sortVia)
        LaunchedEffect(key1 = true) {
            viewModel.noteListEvents.collectLatest { event ->
                when (event) {

                    is NoteListEvents.OnSortChange -> {

                        if (
                            event.sortVia::class.java == data.sortVia.uiData.value!!::class.java
                            && event.sortVia.sortOrderBy::class.java == data.sortVia.uiData.value!!.sortOrderBy::class.java
                        ) {
                            return@collectLatest
                        }
                        data.sortVia.onObserveDataValue(event.sortVia)
                        viewModel.getNotes(data.sortVia)
                    }

                    is NoteListEvents.OnNoteListFetched -> {
                        data.noteList.onObserveDataValue(event.list)
                    }
                    is NoteListEvents.OnNoteListError -> {
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(message = event.error ?: "")
                        }
                    }
                    is NoteListEvents.OnDelete -> {
                        scope.launch {
                            val callback = scaffoldState.snackbarHostState.showSnackbar(
                                message = "Note Deleted",
                                actionLabel = "Undo"
                            )
                            if (callback == SnackbarResult.ActionPerformed) {
                                viewModel.restoreNote(event.noteItemModel)
                            }
                        }

                    }
                    is NoteListEvents.Init -> Unit

                }
            }
        }

        Scaffold(
            scaffoldState = scaffoldState,
            floatingActionButton = {
                addIcon.Generate(
                    navController,
                    data = Icons.Default.Add
                )
            }
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    NoteListTitleText.Generate(navController, Unit)
                    SortIcon.Generate(navController, data)
                }

                AnimatedVisibility(
                    visible = data.showSort.getStateValue()!!,
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut() + slideOutVertically()
                ) {

                    SortSection(viewModel).Generate(navController, data)


                }
                Spacer(modifier = Modifier.height(16.dp))
                LazyListView(data.noteList.getStateValue()!!).Generate(
                    navController,
                    data = NoteItem(viewModel)
                )
            }


        }
    }


}
