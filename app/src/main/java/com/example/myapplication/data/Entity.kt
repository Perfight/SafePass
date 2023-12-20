package com.example.myapplication.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Account",
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["category"]
        ),
        ForeignKey(
            entity = User::class,
            parentColumns = ["idUser"],
            childColumns = ["user"]
        )
    ]
)
data class Account(
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "category") val category: Int,
    @ColumnInfo(name = "site") val site: String,
    @ColumnInfo(name = "user") val user: Int,
    @PrimaryKey(autoGenerate = true) val id: Int
    )

@Entity(tableName = "Category")
data class Category(
    @ColumnInfo(name = "category") val category: String,
    @PrimaryKey(autoGenerate = true) val id: Int
)

@Entity(tableName = "User")
data class User(
    @PrimaryKey(autoGenerate = true) val idUser: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "password") val password: String
)