package com.example.myapplication.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository (private val managerDao: ManagerDAO) {

    suspend fun insertData(username : String, password: String, category: String, site: String, user: Int){
        return withContext(Dispatchers.IO){
            managerDao.insertData(username, password, category, site, user)
        }
    }

    suspend fun getData(user: Int): List<Account>{
        return withContext(Dispatchers.IO){
            return@withContext managerDao.getData(user)
        }
    }

    /*suspend fun deleteAllData(){
        return withContext(Dispatchers.IO){
            managerDao.deleteAllData()
        }
    }*/

    suspend fun deleteData(account: Account){
        return withContext(Dispatchers.IO){
            managerDao.deleteData(account)
        }
    }

    suspend fun getAccountData(id : Int) : Account{
        return withContext(Dispatchers.IO){
            return@withContext managerDao.getAccountData(id)
        }
    }

    suspend fun updateAccount(account: Account){
        return withContext(Dispatchers.IO){
            managerDao.updateAccount(account)
        }
    }

    suspend fun getUserInfo(id: Int) : User{
        return withContext(Dispatchers.IO){
            return@withContext managerDao.getUserInfo(id)
        }
    }

    suspend fun getUserList() : List<User>{
        return withContext(Dispatchers.IO){
            return@withContext managerDao.getUserList()
        }
    }

    suspend fun insertUser(name : String, email : String, password: String){
        return withContext(Dispatchers.IO){
            managerDao.insertUser(name, email, password)
        }
    }

    suspend fun getAuthInfo(name: String) : User{
        return withContext(Dispatchers.IO){
            managerDao.getAuthInfo(name)
        }
    }
    suspend fun getCounters(): Map<String, Int> {
        return withContext(Dispatchers.IO){
            return@withContext mapOf<String, Int>(
                Pair("Application", managerDao.getCount("Application")),
                Pair("Cloud", managerDao.getCount("Cloud")),
                Pair("Payment", managerDao.getCount("Payment")),
                Pair("Website", managerDao.getCount("Website"))
            )
        }
    }
    suspend fun divideByCategory(category: String, user: Int) : List<Account> {
        return withContext(Dispatchers.IO){
            return@withContext managerDao.divideByCategory(category, user)
        }
    }
}