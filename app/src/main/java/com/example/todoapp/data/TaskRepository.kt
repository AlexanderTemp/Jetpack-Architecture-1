package com.example.todoapp.data

import kotlinx.coroutines.flow.Flow


// Interface Data Layer
interface TaskRepository {

    fun getTasksStream(): Flow<List<Task>>
}