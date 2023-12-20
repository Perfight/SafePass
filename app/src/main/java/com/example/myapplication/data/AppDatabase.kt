package com.example.myapplication.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Account::class, User::class], version = 3)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getDao(): ManagerDAO
}