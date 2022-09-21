package com.example.todocompose.domain.usecases.note

import com.example.todocompose.domain.ResultWrapper
import com.example.todocompose.domain.model.NoteItemModel
import com.example.todocompose.domain.params.NoteParams
import com.example.todocompose.domain.repository.NoteRepository
import com.example.todocompose.domain.usecases.BuildUseCase

class AddNoteUseCase(private val noteRepository: NoteRepository) :
    BuildUseCase<NoteParams, Boolean>() {

    override suspend fun execute(parmas: NoteParams): ResultWrapper<Throwable, Boolean> {
        return ResultWrapper.build {
            val createNote = NoteItemModel(
                parmas.title,
                parmas.content,
                System.currentTimeMillis(),
                parmas.color
            ).apply {
                noteId = parmas.noteId
            }
            noteRepository.insertOrUpdateNewNote(createNote)
            true
        }
    }
}