package com.example.todocompose.domain.usecases.note

import com.example.todocompose.domain.ResultWrapper
import com.example.todocompose.domain.model.NoteItemModel
import com.example.todocompose.domain.params.SortByParams
import com.example.todocompose.domain.repository.NoteRepository
import com.example.todocompose.domain.usecases.BuildUseCase
import com.example.todocompose.presentation.ui.features.list.model.OrderBy
import com.example.todocompose.presentation.ui.features.list.model.SortBy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllNoteUseCase(private val noteRepository: NoteRepository) :
    BuildUseCase<SortByParams, Flow<List<NoteItemModel>>>() {

    override suspend fun execute(parmas: SortByParams): ResultWrapper<Throwable, Flow<List<NoteItemModel>>> {

        return ResultWrapper.build {
            val flow = noteRepository.getAllNotes().map { list ->
                val listItems = ArrayList<NoteItemModel>()
                list.forEach { noteDataModel ->
                    val noteItemModel = NoteItemModel(
                        noteDataModel.title,
                        noteDataModel.content,
                        noteDataModel.timeStamp,
                        noteDataModel.color
                    ).apply {
                        noteId = noteDataModel.id
                    }
                    listItems.add(noteItemModel)
                }

                if (listItems.isEmpty()) return@map listItems

                val shortVia = parmas.shortVia.uiData.value!!
                val sortedList = when (shortVia.sortOrderBy) {
                    is SortBy.ASC -> {
                        when (shortVia) {
                            is OrderBy.Title -> listItems.sortedBy { it.title.lowercase() }
                            is OrderBy.Date -> listItems.sortedBy { it.timeStamp }
                            is OrderBy.Color -> listItems.sortedBy { it.color }
                        }
                    }
                    is SortBy.DESC -> {
                        when (shortVia) {
                            is OrderBy.Title -> listItems.sortedByDescending { it.title.lowercase() }
                            is OrderBy.Date -> listItems.sortedByDescending { it.timeStamp }
                            is OrderBy.Color -> listItems.sortedByDescending { it.color }
                        }
                    }
                }

                sortedList
            }
            flow
        }

    }
}