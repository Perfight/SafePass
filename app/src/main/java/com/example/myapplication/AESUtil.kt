package com.example.myapplication

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.SecretKey

object AESUtil {
    private const val AES_TRANSFORMATION = "AES/ECB/PKCS5Padding"

    @RequiresApi(Build.VERSION_CODES.O)
    fun encrypt(text: String, key: SecretKey): String {
        val cipher = Cipher.getInstance(AES_TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, key)
        val encryptedBytes = cipher.doFinal(text.toByteArray())
        return Base64.getEncoder().encodeToString(encryptedBytes)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun decrypt(encryptedText: String, key : SecretKey): String {
        val cipher = Cipher.getInstance(AES_TRANSFORMATION)
        cipher.init(Cipher.DECRYPT_MODE, key)

        val decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText))
        return String(decryptedBytes)
    }
}