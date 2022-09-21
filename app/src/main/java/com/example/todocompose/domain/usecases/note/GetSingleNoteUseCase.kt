package com.example.todocompose.domain.usecases.note

import com.example.todocompose.domain.ResultWrapper
import com.example.todocompose.domain.model.NoteItemModel
import com.example.todocompose.domain.params.ByIdParams
import com.example.todocompose.domain.repository.NoteRepository
import com.example.todocompose.domain.usecases.BuildUseCase

class GetSingleNoteUseCase(private val noteRepository: NoteRepository) :
    BuildUseCase<ByIdParams, NoteItemModel>() {

    override suspend fun execute(parmas: ByIdParams): ResultWrapper<Throwable, NoteItemModel> {

        val note = noteRepository.getNoteById(parmas.id)
            ?: return ResultWrapper.Error(Throwable("No Note Found!"))

        val noteItemModel =
            NoteItemModel(note.title, note.content, note.timeStamp, note.color).apply {
                noteId = note.id
            }
        return ResultWrapper.build {
            noteItemModel
        }

        /*return try {
            val note= noteRepository.getNoteById(parmas.id)
            if (note==null)
            {
                ResultWrapper.Error()
            }
            else{
                val noteItemModel = NoteItemModel(note.title,note.content,note.timeStamp,note.color).apply {
                    noteId = note.id
                }
                ResultWrapper.Success(noteItemModel)
            }

        } catch (e: Exception) {
            ResultWrapper.Error(e)
        }*/
    }
}