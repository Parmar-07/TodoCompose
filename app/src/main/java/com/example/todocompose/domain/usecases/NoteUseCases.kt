package com.example.todocompose.domain.usecases

import com.example.todocompose.domain.usecases.note.AddNoteUseCase
import com.example.todocompose.domain.usecases.note.DeleteNoteUseCase
import com.example.todocompose.domain.usecases.note.GetAllNoteUseCase
import com.example.todocompose.domain.usecases.note.GetSingleNoteUseCase

data class NoteUseCases (
    val addNoteUseCase: AddNoteUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase,
    val getSingleNoteUseCase: GetSingleNoteUseCase,
    val getAllNoteUseCase: GetAllNoteUseCase
)