package com.example.todocompose.domain.model

data class NoteItemModel(
    val title: String,
    val content: String,
    val timeStamp: Long,
    val color: Int
) {
    var noteId: Int? = null
}