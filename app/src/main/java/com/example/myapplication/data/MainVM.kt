package com.example.myapplication.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainVM (private val managerRepository: Repository): ViewModel() {
    var accounts : MutableLiveData<List<Account>> = MutableLiveData()

    fun insertData(username : String, password: String, category: String, site: String, user: Int){
        viewModelScope.launch {
            managerRepository.insertData(username, password, category, site, user)
        }
    }

    fun getData(user: Int){
        viewModelScope.launch {
            accounts.value = managerRepository.getData(user)
        }
    }

    /*fun deleteAllData(){
        viewModelScope.launch {
            managerRepository.deleteAllData()
        }
    }*/

    fun deleteData(account: Account){
        viewModelScope.launch {
            managerRepository.deleteData(account)
        }
    }
}