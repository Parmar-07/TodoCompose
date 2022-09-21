package com.example.todocompose.presentation.ui.features.add

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todocompose.domain.ResultWrapper
import com.example.todocompose.domain.params.ByIdParams
import com.example.todocompose.domain.params.NoteParams
import com.example.todocompose.domain.usecases.NoteUseCases
import com.example.todocompose.presentation.ui.features.add.model.AddNoteUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _addEvents = MutableSharedFlow<AddEvents>()
    val addEvents = _addEvents.asSharedFlow()

   /* init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            getNoteById(noteId)
        }
    }*/

     fun getNoteById(noteId: Int) {
         if (noteId <=0) return

        viewModelScope.launch(Dispatchers.IO) {

            when (val result = noteUseCases.getSingleNoteUseCase.execute(ByIdParams(noteId))) {
                is ResultWrapper.Success -> {
                    _addEvents.emit(AddEvents.OnAddNoteFetched(result.value))
                }
                is ResultWrapper.Error -> {
                    _addEvents.emit(AddEvents.OnAddNoteFailed(result.error.message))
                }
            }


        }
    }


    fun addNote(addNoteUIModel: AddNoteUIModel) {
        viewModelScope.launch(Dispatchers.IO) {
            val noteParam = NoteParams().apply {
                title = addNoteUIModel.title.uiData.value ?: ""
                content = addNoteUIModel.content.uiData.value ?: ""
                color = addNoteUIModel.noteColor.uiData.value?.toArgb() ?: -1
                noteId = addNoteUIModel.noteId
            }

            if (noteParam.title.isEmpty()) {
                _addEvents.emit(AddEvents.OnAddNoteFailed("Kindly Enter Title!"))
                return@launch
            }

            when (val result = noteUseCases.addNoteUseCase.execute(noteParam)) {
                is ResultWrapper.Success -> {
                    _addEvents.emit(AddEvents.OnAddNoteSuccess)
                }
                is ResultWrapper.Error -> {
                    _addEvents.emit(AddEvents.OnAddNoteFailed(result.error.message))
                }
            }


        }


    }


}