package com.example.todocompose.presentation.ui.composes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class ObservableData<T>(data:T?=null) {
    private val _uiData = MutableStateFlow(data)
    val uiData: StateFlow<T?> = _uiData

    fun onObserveDataValue(data: T?) {
        _uiData.value = data
    }

    @Composable
    fun getStateValue():T?{
        return uiData.collectAsState().value!!
    }



}
