package com.example.mobilesecuritynotes.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.mobilesecuritynotes.R
import com.example.mobilesecuritynotes.repositories.AuthRepository
import com.example.mobilesecuritynotes.utils.ToastMessages
import com.example.mobilesecuritynotes.utils.showShortToast

class ChangePasswordActivity : AppCompatActivity() {
    private lateinit var changePasswordButton: Button
    private lateinit var oldPasswordInput: EditText
    private lateinit var newPasswordInput: EditText
    private lateinit var repeatPasswordInput: EditText
    private lateinit var authRepository: AuthRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        this.changePasswordButton = findViewById(R.id.ChangePasswordButton)
        this.oldPasswordInput = findViewById(R.id.etOldPassword)
        this.newPasswordInput = findViewById(R.id.etNewPassword1)
        this.repeatPasswordInput = findViewById(R.id.etNewPassword2)
        this.authRepository = AuthRepository(this.applicationContext)

        this.changePasswordButton.setOnClickListener {
            val newPassword = this.newPasswordInput.text.toString()

            if (!checkIfRepeatPasswordMatch(newPassword)) return@setOnClickListener
            if (!checkIfOldPasswordCorrect()) return@setOnClickListener

            authRepository.setNewPassword(newPassword)
            showShortToast(this.applicationContext, ToastMessages.SUCCESSFUL_PASSWORD_CHANGE)
            finish()
        }
    }

    private fun checkIfRepeatPasswordMatch(newPassword: String): Boolean {
        val repeatPassword = this.repeatPasswordInput.text.toString()
        if (newPassword != repeatPassword) {
            showShortToast(this.applicationContext, ToastMessages.REPEAT_PASSWORD_DOESNT_CORRESPEND)
            return false
        }
        return true
    }
    private fun checkIfOldPasswordCorrect(): Boolean {
        if (authRepository.doesPasswordExist()) {
            val oldPassword = oldPasswordInput.text.toString()
            if (!authRepository.checkPassword(oldPassword)) {
                showShortToast(this.applicationContext, ToastMessages.WRONG_PASSWORD)
                return false
            }
        }
        return true
    }
}
