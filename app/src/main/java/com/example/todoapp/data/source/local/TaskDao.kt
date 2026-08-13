package com.example.todoapp.data.source.local

import androidx.room.Dao
import androidx.room.Upsert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

// Data Access Object for table task -> Lo que se pued ehacer con los datos
@Dao
interface TaskDao {

    // Solo List<LocalTask> te obtiene la lista de tareas
    // Con Flow te avisa cuando las tareas cambien
    @Query("SELECT * FROM Task")
    fun observeAll(): Flow<List<LocalTask>>

}

