package com.example.todocompose.domain.usecases.note

import com.example.todocompose.domain.ResultWrapper
import com.example.todocompose.domain.params.ByIdParams
import com.example.todocompose.domain.repository.NoteRepository
import com.example.todocompose.domain.usecases.BuildUseCase

class DeleteNoteUseCase(private val noteRepository: NoteRepository) :
    BuildUseCase<ByIdParams, Boolean>() {

    override suspend fun execute(parmas: ByIdParams): ResultWrapper<Throwable, Boolean> {
        return ResultWrapper.build {
            noteRepository.deleteNoteById(parmas.id)
            true
        }
    }
}