package com.example.myapplication.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ManagerDAO {

    @Query("SELECT category FROM Category")
    fun getCategories(): List<String>

    @Query("INSERT INTO Account (username, password, category, site, user) VALUES (:username, :password, :category, :site, :user)")
    fun insertData(username : String, password: String, category: Int, site: String, user: Int)
    //user = 1, site, category, username, password

    @Query("SELECT * FROM Account where user = :user")
    suspend fun getData(user: Int) : List<Account>

    //@Query("DELETE * FROM Account")
    //suspend fun deleteAllData()

    @Delete
    suspend fun deleteData(account: Account)
}