package com.example.todocompose.presentation.ui.navigators

sealed class AppRoute(val routeName : String,val argsRoute:String ="") {
    object NoteListRoute : AppRoute(NOTE_LIST)
    object AddNoteRoute : AppRoute(ADD_NOTE)
    object EditNoteRoute : AppRoute(String.format(EDIT_NOTE_ARGS), EDIT_NOTE_ARGS_VALUE)
}

