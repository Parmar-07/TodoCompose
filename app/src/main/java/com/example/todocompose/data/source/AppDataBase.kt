package com.example.todocompose.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todocompose.data.model.NoteDataModel

@Database(entities = [NoteDataModel::class], version = 1,exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract val noteDao: NoteDao

}