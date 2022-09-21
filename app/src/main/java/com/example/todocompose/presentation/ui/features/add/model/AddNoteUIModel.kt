package com.example.todocompose.presentation.ui.features.add.model

import com.example.todocompose.presentation.ui.theme.*


class AddNoteUIModel {
    var title = NoteTitle()
    var titleHint = TitleHint()
    var content = Content()
    var contentHint = ContentHint()
    var noteColor = NoteColor()
    val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    var noteId:Int?=null

}