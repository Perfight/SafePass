package com.example.myapplication.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ManagerDAO {

    @Query("INSERT INTO Account (username, password, category, site, user) VALUES (:username, :password, :category, :site, :user)")
    fun insertData(username : String, password: String, category: String, site: String, user: Int)
    //user = 1, site, category, username, password

    @Query("SELECT * FROM Account where user = :user")
    suspend fun getData(user: Int) : List<Account>

    //@Query("DELETE * FROM Account")
    //suspend fun deleteAllData()

    @Delete
    suspend fun deleteData(account: Account)

    @Query("SELECT * FROM Account where id = :id")
    suspend fun getAccountData(id: Int) : Account

    @Update
    suspend fun updateAccount(account: Account)
}