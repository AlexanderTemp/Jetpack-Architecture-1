package com.example.todoapp.di

import android.content.Context
import androidx.room.Room
import com.example.todoapp.data.DefaultTaskRepository
import com.example.todoapp.data.TaskRepository
import com.example.todoapp.data.source.local.TaskDao
import com.example.todoapp.data.source.local.ToDoDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    // Esta implementación satisface esta interfaz.
    @Binds
    // Cuando alguien pida un TaskRepository, entrégale un DefaultTaskRepository
    abstract fun bindTaskRepository(repository: DefaultTaskRepository): TaskRepository
}

@Module
// una única instancia de determinada dependencia durante toda la vida de la aplicación
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): ToDoDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ToDoDatabase::class.java,
            "Tasks.db"
        ).build()
    }

    @Provides // Esta anotación se coloca en funciones que crean o proporcionan dependencias
    //  si alguien necesita un TaskDao, usa esta función para conseguirlo.
    fun provideTaskDao(database: ToDoDatabase): TaskDao = database.taskDao()
}