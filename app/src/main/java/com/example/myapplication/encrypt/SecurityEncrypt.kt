package com.example.myapplication.encrypt

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class SecurityEncrypt(context: Context){

    private var masterKey: MasterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private var sharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
        context,
        "secret_shared_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun putDataPass(value: String?) : String {
        val editor = sharedPreferences.edit()
        val key = generateRandomString()
        editor.putString(key, value).apply()
        editor.commit()
        return key
    }

    fun putData(key : String, value: String?) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value).apply()
        editor.commit()
    }

    fun getData(key: String, defaultValue: String?): String? {
        return sharedPreferences.getString(key, defaultValue)
    }

    fun containsKey(key: String): Boolean {
        return sharedPreferences.contains(key)
    }

    fun clearData() {
        val editor = sharedPreferences.edit()
        editor.clear().apply()
    }
}
fun generateRandomString(): String {
    val alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
    val uniqueChars = alphabet.toList().shuffled().distinct().joinToString("")

    return (1..8).map { uniqueChars.random() }.joinToString("")
}