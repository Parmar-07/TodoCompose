package com.example.todocompose.presentation.ui.features.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todocompose.domain.ResultWrapper
import com.example.todocompose.domain.model.NoteItemModel
import com.example.todocompose.domain.params.ByIdParams
import com.example.todocompose.domain.params.NoteParams
import com.example.todocompose.domain.params.SortByParams
import com.example.todocompose.domain.usecases.NoteUseCases
import com.example.todocompose.presentation.ui.features.list.model.OrderBy
import com.example.todocompose.presentation.ui.features.list.model.SortVia
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val _noteListEvents = MutableSharedFlow<NoteListEvents>()
    val noteListEvents = _noteListEvents.asSharedFlow()
    private var notesListJob: Job? = null

    init {
        getNotes(SortVia)
    }


    fun getNotes(shortVia: SortVia) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = noteUseCases.getAllNoteUseCase.execute(SortByParams(shortVia))) {
                is ResultWrapper.Success -> {
                    notesListJob?.cancel()
                    notesListJob = result.value.onEach {
                        _noteListEvents.emit(NoteListEvents.OnNoteListFetched(it))
                    }.launchIn(viewModelScope)
                }
                is ResultWrapper.Error -> {
                    _noteListEvents.emit(NoteListEvents.OnNoteListError(result.error.message))
                }
            }
        }

    }

    private fun createNoteParams(data: NoteItemModel): NoteParams {
        return NoteParams().apply {
            this.noteId = data.noteId
            this.title = data.title
            this.content = data.content
            this.timeStamp = data.timeStamp
            this.color = data.color
        }
    }


    fun deleteNote(data: NoteItemModel) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = noteUseCases.deleteNoteUseCase.execute(ByIdParams(data.noteId))) {
                is ResultWrapper.Success -> {
                    _noteListEvents.emit(NoteListEvents.OnDelete(data))
                }
                is ResultWrapper.Error -> {
                    _noteListEvents.emit(NoteListEvents.OnNoteListError(result.error.message))
                }
            }
        }
    }


    fun restoreNote(data: NoteItemModel) {

        viewModelScope.launch(Dispatchers.IO) {
            val noteParam = createNoteParams(data)
            when (val result = noteUseCases.addNoteUseCase.execute(noteParam)) {
                is ResultWrapper.Success -> {

                }
                is ResultWrapper.Error -> {
                    _noteListEvents.emit(NoteListEvents.OnNoteListError(result.error.message))
                }
            }
        }

    }

    fun onOrderSelect(sort: OrderBy) {
        viewModelScope.launch {
            _noteListEvents.emit(NoteListEvents.OnSortChange(sort))
        }
    }


}