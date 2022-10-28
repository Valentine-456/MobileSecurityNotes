package com.example.mobilesecuritynotes.repositories

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class AuthRepository(context: Context) {
    private var masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
    private val sharedPreference = EncryptedSharedPreferences.create(
        "mobile-security-notes",
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun doesPasswordExist(): Boolean {
        return this.sharedPreference.contains("APP_PASSWORD")
    }

    fun checkPassword(probablePassword: String): Boolean {
        val currentPassword = this.sharedPreference.getString("APP_PASSWORD", "")
        return currentPassword == probablePassword
    }

    fun setNewPassword(newPassword: String) {
        val editor = this.sharedPreference.edit()
        editor.putString("APP_PASSWORD", newPassword)
        editor.commit()
    }
}
