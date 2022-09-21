package com.example.todocompose.presentation.ui.composes

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

abstract class CreateCompose<Data> {

    private var args: Bundle? = null

    fun putArgs(args: Bundle?) {
        this.args = args
    }

    fun getArgs(): Bundle? = args


    @Composable
    abstract fun Generate(navController: NavController, data: Data)

}