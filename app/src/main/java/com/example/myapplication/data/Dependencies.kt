package com.example.myapplication.data

import android.content.Context
import androidx.room.Room

object Dependencies {
    private lateinit var applicationContext: Context

    fun init(context: Context) { //конструктор
        applicationContext = context
    }

    private val appDatabase: AppDatabase by lazy { //ленивая инициализаци
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "passmanager.db") //название под которым он сохранит внутри устроиства
            .createFromAsset("manager.db") //создает из шаблона
            .build()
    }

    val managerRepository: Repository by lazy { Repository(appDatabase.getDao()) }
}