package com.example.mobilesecuritynotes.repositories

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.password4j.Password

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

    fun checkPassword(probablePassword: CharSequence): Boolean {
        val currentPassword = this.sharedPreference.getString("APP_PASSWORD", "")
        return Password.check(probablePassword, currentPassword).withArgon2()
    }

    fun setNewPassword(newPassword: CharSequence) {
        val newPasswordHashed = Password.hash(newPassword).withArgon2()

        val editor = this.sharedPreference.edit()
        editor.putString("APP_PASSWORD", newPasswordHashed.result)
        editor.commit()
    }

    fun getDBPassword(): String {
        if (!this.sharedPreference.contains("DB_PASSWORD")) {
            val editor = this.sharedPreference.edit()
            editor.putString("DB_PASSWORD", this.randomCharSeq(16))
            editor.commit()
        }
        return this.sharedPreference.getString("DB_PASSWORD", "")!!
    }

    private val chars = ('a'..'z') + ('A'..'Z') + ('0'..'9')
    private fun randomCharSeq(n: Int): String = List(n) { chars.random() }.joinToString("")
}
