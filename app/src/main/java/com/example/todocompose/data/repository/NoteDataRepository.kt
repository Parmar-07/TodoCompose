package com.example.todocompose.data.repository

import com.example.todocompose.data.model.NoteDataModel
import com.example.todocompose.data.source.NoteDao
import com.example.todocompose.domain.model.NoteItemModel
import com.example.todocompose.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

data class NoteDataRepository(val noteDao: NoteDao) : NoteRepository {

    @Throws(Throwable::class)
    override suspend fun getAllNotes(): Flow<List<NoteDataModel>> = noteDao.fetchAllNotes()

    @Throws(Throwable::class)
    override suspend fun getNoteById(noteId: Int?): NoteDataModel {
        if (noteId == null || noteId == -1)
            throw Throwable("Note is Null or Empty!")

        return noteDao.fetchNotesById(noteId) ?: throw Throwable("No Note Found!")
    }

    @Throws(Throwable::class)
    override suspend fun insertOrUpdateNewNote(noteItemModel: NoteItemModel) {
        val note = NoteDataModel(
            noteItemModel.title,
            noteItemModel.content,
            noteItemModel.timeStamp,
            noteItemModel.color,
        ).apply {
            id = noteItemModel.noteId
        }
        noteDao.insertNote(note)
    }

    @Throws(Throwable::class)
    override suspend fun deleteNoteById(noteId: Int?) {
        if (noteId == null || noteId == -1)
            throw Throwable("Note is Null or Empty!")

        noteDao.deleteNoteById(noteId)
    }

}