package com.example.todocompose.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todocompose.data.tbl_notes

@Entity(tableName = tbl_notes)
data class NoteDataModel(
    val title: String,
    val content: String,
    val timeStamp: Long,
    val color: Int,
) {
    @PrimaryKey
    var id: Int? = null
}
