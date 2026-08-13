package com.example.todoapp.tasks

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.R
import com.example.todoapp.data.Task
import com.example.todoapp.data.TaskRepository
import com.example.todoapp.util.Async
import com.example.todoapp.util.WhileUiSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


// UiState for list screen

data class TasksUiState(
    val items: List<Task> = emptyList(),
    val isLoading: Boolean = false,
    val userMessage: Int? = null
)

// ViewModel for the task list screer
@HiltViewModel
class TasksViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _isLoading = MutableStateFlow(false)

    private val _tasksAsync = taskRepository.getTasksStream().map { tasks ->
        Async.Success(tasks)
    }.catch<Async<List<Task>>> {
        emit(Async.Error(R.string.loading_tasks_error))
    }

    private val _userMessage: MutableStateFlow<Int?> = MutableStateFlow(null)

    private val _uiState: StateFlow<TasksUiState> = combine(_tasksAsync, _isLoading, _userMessage) {
        itemsAsync, isLoading, userMessage ->
            when(itemsAsync) {
                Async.Loading -> {
                    TasksUiState(isLoading = true)
                }
                is Async.Error -> {
                    TasksUiState(userMessage = itemsAsync.errorMessage)
                }
                is Async.Success -> {
                    TasksUiState(
                        items = itemsAsync.data,
                        isLoading = isLoading,
                        userMessage = userMessage
                    )
                }
            }
    }.stateIn( viewModelScope, WhileUiSubscribed, TasksUiState(isLoading = true))

}