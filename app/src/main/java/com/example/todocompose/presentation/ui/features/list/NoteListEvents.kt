package com.example.todocompose.presentation.ui.features.list

import com.example.todocompose.domain.model.NoteItemModel
import com.example.todocompose.presentation.ui.features.list.model.OrderBy
import com.example.todocompose.presentation.ui.features.list.model.SortVia

sealed class NoteListEvents {

    object Init : NoteListEvents()
    data class OnSortChange(val sortVia : OrderBy) : NoteListEvents()
    data class OnDelete(val noteItemModel: NoteItemModel) : NoteListEvents()
    data class OnNoteListFetched(val list : List<NoteItemModel>) : NoteListEvents()
    data class OnNoteListError(val error : String?) : NoteListEvents()

}