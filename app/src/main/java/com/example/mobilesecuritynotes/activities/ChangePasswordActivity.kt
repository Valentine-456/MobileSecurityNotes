package com.example.mobilesecuritynotes.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mobilesecuritynotes.databinding.ActivityChangePasswordBinding
import com.example.mobilesecuritynotes.repositories.AuthRepository
import com.example.mobilesecuritynotes.utils.ToastMessages
import com.example.mobilesecuritynotes.utils.showShortToast

class ChangePasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChangePasswordBinding
    private lateinit var authRepository: AuthRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.authRepository = AuthRepository(this.applicationContext)

        binding.ChangePasswordButton.setOnClickListener {
            val newPassword = binding.ETNewPassword1.text.toString()

            if (!validateNewPassword(newPassword)) return@setOnClickListener
            if (!checkIfRepeatPasswordMatch(newPassword)) return@setOnClickListener
            if (!checkIfOldPasswordCorrect()) return@setOnClickListener

            authRepository.setNewPassword(newPassword)
            showShortToast(this.applicationContext, ToastMessages.SUCCESSFUL_PASSWORD_CHANGE)
            finish()
        }
    }

    private fun checkIfRepeatPasswordMatch(newPassword: String): Boolean {
        val repeatPassword = this.binding.ETNewPassword2.text.toString()
        if (newPassword != repeatPassword) {
            showShortToast(this.applicationContext, ToastMessages.REPEAT_PASSWORD_DOESNT_CORRESPEND)
            return false
        }
        return true
    }
    private fun checkIfOldPasswordCorrect(): Boolean {
        if (authRepository.doesPasswordExist()) {
            val oldPassword = binding.ETOldPassword.text.toString()
            if (!authRepository.checkPassword(oldPassword)) {
                showShortToast(this.applicationContext, ToastMessages.WRONG_PASSWORD)
                return false
            }
        }
        return true
    }

    private fun validateNewPassword(newPassword: String): Boolean {
        if (newPassword.length < 6) {
            showShortToast(this.applicationContext, ToastMessages.PASSWORD_TOO_SHORT)
            return false
        }
        return true
    }
}
