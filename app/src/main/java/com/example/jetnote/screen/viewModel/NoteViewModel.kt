package com.example.jetnote.screen.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetnote.data.NotesDataSource
import com.example.jetnote.model.Note
import com.example.jetnote.repository.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NotesRepository) : ViewModel() {
    // var noteList = mutableStateListOf<Note>()
    //This means it is private _

    private val _notesList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _notesList.asStateFlow()

    init {
//        noteList.addAll(NotesDataSource().loadNotes())
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged()
                .collect{ listOfNotes ->
                    if(listOfNotes.isNullOrEmpty()){
                        Log.d("Empty", "EmptyList")
                    }else{
                        _notesList.value = listOfNotes
                    }

                }
        }
    }

     fun addNote(note: Note) = viewModelScope.launch {
        repository.addNote(note)
    }

     fun updateNote(note: Note) = viewModelScope.launch {
        repository.updateNote(note)
    }

     fun removeNote(note: Note) = viewModelScope.launch {
        repository.deleteNote(note)
    }
}