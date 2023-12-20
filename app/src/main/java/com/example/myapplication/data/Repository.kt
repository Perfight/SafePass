package com.example.myapplication.data

import com.example.myapplication.data.ManagerDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository (private val managerDao: ManagerDAO) {

    suspend fun getCategories(): List<String> {
        return withContext(Dispatchers.IO){//для чтения или записи файлов, сетевых операций и т.д.
            return@withContext managerDao.getCategories() // удобные операторы для комбинирования и управления последовательностями асинхронных операций
        }
    }

    suspend fun insertData(username : String, password: String, category: Int, site: String, user: Int){
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
}