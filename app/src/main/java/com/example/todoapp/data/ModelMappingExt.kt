package com.example.todoapp.data

import com.example.todoapp.data.source.local.LocalTask

/**
 * Data model mapping extension functions. There are three model types:
 *
 * - Task: External model exposed to other layers in the architecture.
 * Obtained using `toExternal`.
 *
 * - NetworkTask: Internal model used to represent a task from the network. Obtained using
 * `toNetwork`.
 *
 * - LocalTask: Internal model used to represent a task stored locally in a database. Obtained
 * using `toLocal`.
 *
 */

// External to local

// Local to External
fun LocalTask.toExternal() = Task(
    id = id,
    title = title,
    description = description,
    isCompleted = isCompleted
)

// JvmName provee un nombre único por cada función extensión con el mismo nombre
@JvmName("localToExternal")
fun List<LocalTask>.toExternal() = map(LocalTask::toExternal)