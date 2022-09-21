package com.example.todocompose.presentation.ui.features.add

data class AddNoteTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true,
)