package com.example.todocompose.domain.repository

import com.example.todocompose.data.model.NoteDataModel
import com.example.todocompose.domain.model.NoteItemModel
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    suspend fun getAllNotes(): Flow<List<NoteDataModel>>

    suspend fun getNoteById(noteId: Int?): NoteDataModel?

    suspend fun insertOrUpdateNewNote(noteItemModel: NoteItemModel)

    suspend fun deleteNoteById(noteId: Int?)

}