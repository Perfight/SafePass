package com.example.myapplication.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update

@Dao
interface ManagerDAO {

    @Query("INSERT INTO Account (username, password, category, site, user) VALUES (:username, :password, :category, :site, :user)")
    fun insertData(username : String, password: String, category: String, site: String, user: Int)
    //user = 1, site, category, username, password

    @Query("SELECT * FROM Account where user = :user")
    suspend fun getData(user: Int) : List<Account>

    @Delete
    suspend fun deleteData(account: Account)

    @Query("SELECT * FROM Account where id = :id")
    suspend fun getAccountData(id: Int) : Account

    @Update
    suspend fun updateAccount(account: Account)

    @Query("SELECT * FROM User WHERE idUser = :id")
    suspend fun getUserInfo(id : Int) : User

    @Query("SELECT * FROM User")
    suspend fun getUserList() : List<User>

    @Query("INSERT INTO User (name, email, password) VALUES (:name, :email, :password)")
    suspend fun insertUser(name : String, email : String, password: String)

    @Query("SELECT COUNT(*) from account WHERE category=:category AND user = :user")
    suspend fun getCount(category: String, user : Int): Int

    @Query("SELECT * FROM User WHERE name = :name")
    suspend fun getAuthInfo(name : String) : User
}