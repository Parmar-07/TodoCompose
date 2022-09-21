package com.example.todocompose.presentation.ui.features.add.model

import androidx.compose.ui.graphics.Color
import com.example.todocompose.presentation.ui.composes.ObservableData

class NoteTitle : ObservableData<String>("")
class TitleHint : ObservableData<Boolean>(true)
class Content : ObservableData<String>("")
class ContentHint : ObservableData<Boolean>(true)
class NoteColor : ObservableData<Color>(Color.White)