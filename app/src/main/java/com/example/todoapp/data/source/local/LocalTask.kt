package com.example.todoapp.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "Task"
)
data class LocalTask(
    @PrimaryKey val id: String,
    var title: String,
    var description: String,
    val isCompleted: Boolean
)