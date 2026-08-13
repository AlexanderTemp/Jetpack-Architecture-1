package com.example.todoapp.tasks

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.todoapp.data.Task
import com.example.todoapp.data.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject


// UiState for list screen

data class TasksUiState(
    val items: List<Task> = emptyList(),
    val isLoading: Boolean = false
)

// ViewModel for the task list screer
@HiltViewModel
class TasksViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _isLoading = MutableStateFlow(false)

    private val _simpleTasksAsync = taskRepository.getTasksStream()

}