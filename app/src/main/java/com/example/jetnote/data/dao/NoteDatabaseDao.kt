package com.example.jetnote.data.dao

import androidx.room.*
import com.example.jetnote.model.Note
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDatabaseDao {

    //Skeleton functions
    @Query("Select * from notes_tbl")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * from notes_tbl  where id = :id")
    suspend fun getNoteById(id: String): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note: Note)

    @Query("DELETE from notes_tbl")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteNote(note: Note)
}
