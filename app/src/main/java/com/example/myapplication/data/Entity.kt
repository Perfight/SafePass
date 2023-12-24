package com.example.myapplication.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Account",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["idUser"],
            childColumns = ["user"]
        )
    ]
)
data class Account(
    @ColumnInfo(name = "username") var username: String,
    @ColumnInfo(name = "password") var password: String,
    @ColumnInfo(name = "category") var category: String,
    @ColumnInfo(name = "site") var site: String,
    @ColumnInfo(name = "user") val user: Int,
    @PrimaryKey(autoGenerate = true) val id: Int
    )

@Entity(tableName = "User")
data class User(
    @PrimaryKey(autoGenerate = true) val idUser: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "password") val password: String
)