package com.example.todocompose.presentation.ui.features.list.model

import com.example.todocompose.domain.model.NoteItemModel
import com.example.todocompose.presentation.ui.composes.ObservableData

object ShowSort : ObservableData<Boolean>(false)
object SortVia : ObservableData<OrderBy>(OrderBy.Title(SortBy.ASC))
object NoteListData : ObservableData<List<NoteItemModel>>(emptyList())


sealed class SortBy {
    object ASC : SortBy()
    object DESC : SortBy()
}

sealed class OrderBy(val sortOrderBy: SortBy) {
    data class Title(val sortBy: SortBy) : OrderBy(sortBy)
    data class Date(val sortBy: SortBy) : OrderBy(sortBy)
    data class Color(val sortBy: SortBy) : OrderBy(sortBy)

    fun copyCurrent(sortOrderBy: SortBy): OrderBy {
        return when (this) {
            is Title -> Title(sortOrderBy)
            is Date -> Date(sortOrderBy)
            is Color -> Color(sortOrderBy)
        }
    }

}


