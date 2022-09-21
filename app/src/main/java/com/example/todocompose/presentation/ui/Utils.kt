package com.example.todocompose.presentation.ui

import android.content.Context
import android.widget.Toast


fun Context?.showToast(msg:String?){
    this?.let {
        Toast.makeText(it,msg,Toast.LENGTH_SHORT).show()
    }
}