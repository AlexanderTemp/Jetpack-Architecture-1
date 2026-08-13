package com.example.todoapp.data

data class Task(
    val title: String = "",
    val description: String = "",
    val isCompleted: Boolean = false,
    val id: String
) {
    val titleForList: String
        get() = if(title.isNotEmpty()) title else description

    val isActive: Boolean
        get() = !isCompleted

    val isEmpty: Boolean
        get() = title.isEmpty() || description.isEmpty()
}

