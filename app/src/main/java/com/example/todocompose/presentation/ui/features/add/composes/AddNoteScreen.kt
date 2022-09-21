package com.example.todocompose.presentation.ui.features.add.composes

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todocompose.presentation.ui.composes.CreateCompose
import com.example.todocompose.presentation.ui.composes.FloatingIconButton
import com.example.todocompose.presentation.ui.features.add.AddEvents
import com.example.todocompose.presentation.ui.features.add.AddNoteViewModel
import com.example.todocompose.presentation.ui.features.add.model.AddNoteUIModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AddNoteScreen : CreateCompose<AddNoteUIModel>() {


    @Composable
    override fun Generate(
        navController: NavController,
        data: AddNoteUIModel
    ) {
        val viewModel: AddNoteViewModel = hiltViewModel()
        val scaffoldState = rememberScaffoldState()
        val noteScreenColor = getArgs()?.getInt("noteColor") ?: -1

        val randomColor = if (noteScreenColor<=0){
            Color( data.noteColors.random().toArgb())
        }else{
            Color(noteScreenColor)
        }
        data.noteColor.onObserveDataValue(randomColor)

        val noteBGAnimate = remember {
            Animatable(data.noteColor.uiData.value!!)
        }
        getArgs()?.getInt("noteId")?.let {
            viewModel.getNoteById(it)
        }

        val scope = rememberCoroutineScope()
        LaunchedEffect(key1 = true) {
            viewModel.addEvents.collectLatest { event ->
                when (event) {
                    is AddEvents.OnAddNoteSuccess -> {
                        navController.navigateUp()
                    }
                    is AddEvents.OnAddNoteFailed -> {
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = event.error ?: ""
                            )
                        }
                    }
                    is AddEvents.GetNoteById -> {
                        viewModel.getNoteById(event.noteId)
                    }
                    is AddEvents.OnAddNoteFetched -> {
                        data.apply {
                            title.onObserveDataValue(event.note.title)
                            titleHint.onObserveDataValue(event.note.title.isEmpty())
                            content.onObserveDataValue(event.note.content)
                            contentHint.onObserveDataValue(event.note.content.isEmpty())
                            noteColor.onObserveDataValue(Color(event.note.color))
                            noteId = event.note.noteId

                        }
                        noteBGAnimate.animateTo(
                            targetValue = data.noteColor.uiData.value!!,
                            animationSpec = tween(
                                durationMillis = 500
                            )
                        )

                    }
                    is AddEvents.Init -> Unit
                }
            }
        }


        Scaffold(
            scaffoldState = scaffoldState,
            floatingActionButton = { CreateSaveIcon(navController, viewModel, data) }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(noteBGAnimate.value)
                    .padding(16.dp),

                ) {
                ShowNoteColors(noteBGAnimate).Generate(navController, data)
                Spacer(modifier = Modifier.height(16.dp))
                TitleEditText.Generate(navController, data)
                Spacer(modifier = Modifier.height(16.dp))
                ContentEditText.Generate(navController, data)

            }

        }

    }


    @Composable
    private fun CreateSaveIcon(
        navController: NavController,
        viewModel: AddNoteViewModel,
        data: AddNoteUIModel
    ) {
        val saveIcon = FloatingIconButton(bgColor = MaterialTheme.colors.primary) {
            viewModel.addNote(data)
        }
        saveIcon.Generate(
            navController,
            data = Icons.Default.Save
        )
    }


}
