package com.example.myapplication.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainVM(private val managerRepository: Repository) : ViewModel() {
    var accounts: MutableLiveData<List<Account>> = MutableLiveData()
    var accountCounterList: MutableLiveData<Map<String, Int>> = MutableLiveData()
    var accountInformation: MutableLiveData<Account> = MutableLiveData()
    var userData: MutableLiveData<User> = MutableLiveData()
    var userList: MutableLiveData<List<User>> = MutableLiveData()
    var listCategory : MutableLiveData<List<Account>> = MutableLiveData()
    var currentUser : MutableLiveData<User> = MutableLiveData()

    fun insertData(username: String, password: String, category: String, site: String, user: Int) {
        viewModelScope.launch {
            managerRepository.insertData(username, password, category, site, user)
        }
    }

    fun getData(user: Int) {
        viewModelScope.launch {
            accounts.value = managerRepository.getData(user)
        }
    }

    /*fun deleteAllData(){
        viewModelScope.launch {
            managerRepository.deleteAllData()
        }
    }*/

    fun deleteData(account: Account) {
        viewModelScope.launch {
            managerRepository.deleteData(account)
        }
    }

    fun getAccountCounter(id: Int){
        viewModelScope.launch {
            accountCounterList.value = managerRepository.getCounters(id)
        }
    }

    fun getAccountData(id: Int) {
        viewModelScope.launch {
            accountInformation.value = managerRepository.getAccountData(id)
        }
    }

    fun updateAccount(account: Account) {
        viewModelScope.launch {
            managerRepository.updateAccount(account)
        }
    }

    fun getUserInfo(id: Int) {
        viewModelScope.launch {
            userData.value = managerRepository.getUserInfo(id)
        }
    }

    fun getUserList() {
        viewModelScope.launch {
            userList.value = managerRepository.getUserList()
        }
    }

    fun insertUser(name: String, email: String, password: String) {
        viewModelScope.launch {
            managerRepository.insertUser(name, email, password)
        }
    }

    fun getAuthInfo(name: String) {
        viewModelScope.launch {
            currentUser.value = managerRepository.getAuthInfo(name)
        }
    }
}