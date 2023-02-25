package com.example.jetnote.repository

import com.example.jetnote.data.dao.NoteDatabaseDao
import com.example.jetnote.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NotesRepository @Inject constructor(private val noteDatabaseDao: NoteDatabaseDao) {
    //Define the crud operations with suspend

    //Insert
    suspend fun addNote(note: Note) = noteDatabaseDao.insert(note)

    //Update
    suspend fun updateNote(note: Note) = noteDatabaseDao.update(note)

    //Delete
    suspend fun deleteNote(note: Note) = noteDatabaseDao.deleteNote(note)

    //DeleteAll
    suspend fun deleteAllNotes() = noteDatabaseDao.deleteAll()

    //GetNotes this will take care of everything in the background thread, since your getting a Flow
    //Conflates flow emissions via conflated channel and runs collector in a separate coroutine.
    // The effect of this is that emitter is never suspended due to a slow collector, but collector
    // always gets the most recent value emitted
    fun getAllNotes(): Flow<List<Note>> = noteDatabaseDao.getNotes()
        .flowOn(Dispatchers.IO).conflate()
}