package com.example.todocompose.domain.params

class NoteParams : UseCaseParams() {
    var title:String=""
    var content:String= ""
    var color:Int = 0
    var timeStamp : Long=0L
    var noteId : Int? = null

}