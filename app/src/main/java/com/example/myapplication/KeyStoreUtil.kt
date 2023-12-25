package com.example.myapplication

import java.security.KeyStore
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import java.security.UnrecoverableKeyException
import java.security.cert.CertificateException
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

object KeyStoreUtil {
    private const val KEYSTORE_ALIAS = "myKeyAlias"
    private const val KEYSTORE_TYPE = "AndroidKeyStore"
    private const val KEY_ALGORITHM = "AES"
    private const val KEY_SIZE = 256

    fun generateKey(): SecretKey {
        val keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM)
        keyGenerator.init(KEY_SIZE)
        return keyGenerator.generateKey()
    }

    fun saveKeyToKeyStore(key: SecretKey, password: String) {
        try {
            val keyStore = KeyStore.getInstance(KEYSTORE_TYPE)
            keyStore.load(null)
            keyStore.setKeyEntry(KEYSTORE_ALIAS, key, password.toCharArray(), null)
        } catch (e: KeyStoreException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: CertificateException) {
            e.printStackTrace()
        }
    }

    fun getKeyFromKeyStore(password: String): SecretKey? {
        try {
            val keyStore = KeyStore.getInstance(KEYSTORE_TYPE)
            keyStore.load(null)
            return keyStore.getKey(KEYSTORE_ALIAS, password.toCharArray()) as SecretKey?
        } catch (e: KeyStoreException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: UnrecoverableKeyException) {
            e.printStackTrace()
        } catch (e: CertificateException) {
            e.printStackTrace()
        }
        return null
    }
}