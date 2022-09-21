package com.example.todocompose.presentation.di

import com.example.todocompose.data.repository.NoteDataRepository
import com.example.todocompose.data.source.AppDataBase
import com.example.todocompose.domain.repository.NoteRepository
import com.example.todocompose.domain.usecases.NoteUseCases
import com.example.todocompose.domain.usecases.note.AddNoteUseCase
import com.example.todocompose.domain.usecases.note.DeleteNoteUseCase
import com.example.todocompose.domain.usecases.note.GetAllNoteUseCase
import com.example.todocompose.domain.usecases.note.GetSingleNoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NoteModules {

    @Provides
    @Singleton
    fun provideNoteRepository(appDataBase: AppDataBase): NoteRepository {
        return NoteDataRepository(appDataBase.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(noteRepository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            AddNoteUseCase(noteRepository),
            DeleteNoteUseCase(noteRepository),
            GetSingleNoteUseCase(noteRepository),
            GetAllNoteUseCase(noteRepository)
        )
    }


}