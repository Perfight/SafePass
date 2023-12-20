package com.example.myapplication.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Account::class, Category::class, User::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getDao(): ManagerDAO
}