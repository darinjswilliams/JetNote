package com.example.jetnote.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.jetnote.Converters
import com.example.jetnote.data.dao.NoteDatabaseDao
import com.example.jetnote.model.Note

@Database(entities =[Note::class], version =1, exportSchema = false )
@TypeConverters(Converters::class)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDatabaseDao
}