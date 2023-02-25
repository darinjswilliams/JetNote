package com.example.jetnote

import androidx.room.TypeConverter
import java.sql.Date


public class Converters {
    @TypeConverter
     fun fromTimestamp(value: Long?): Date {
        return Date(value ?: 0)
    }

    @TypeConverter
     fun dateToTimestamp(date: Date): Long {
        return date?.time ?: 0
    }
}