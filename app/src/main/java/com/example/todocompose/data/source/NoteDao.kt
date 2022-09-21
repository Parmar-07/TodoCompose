package com.example.todocompose.data.source

import androidx.room.*
import com.example.todocompose.data.model.NoteDataModel
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("select * from tbl_notes")
    fun fetchAllNotes(): Flow<List<NoteDataModel>>

    @Query("select * from tbl_notes where id=:id")
    fun fetchNotesById(id: Int): NoteDataModel?

    @Query("delete from tbl_notes where id=:id")
    fun deleteNoteById(id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(model: NoteDataModel)

}