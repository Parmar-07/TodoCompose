package com.example.todocompose.presentation.ui.features.add

import com.example.todocompose.domain.model.NoteItemModel
import com.example.todocompose.presentation.ui.features.add.model.AddNoteUIModel

sealed class AddEvents {
    object Init : AddEvents()
    object OnAddNoteSuccess : AddEvents()
    data class GetNoteById(val noteId:Int) : AddEvents()
    data class OnAddNoteFetched(val note:NoteItemModel) : AddEvents()
    data class OnAddNoteFailed(val error: String?) : AddEvents()
}