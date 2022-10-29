package com.example.mobilesecuritynotes.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.mobilesecuritynotes.R
import com.example.mobilesecuritynotes.repositories.AuthRepository
import com.example.mobilesecuritynotes.utils.ToastMessages
import com.example.mobilesecuritynotes.utils.showShortToast

class LoginActivity : AppCompatActivity() {
    private lateinit var loginButton: Button
    private lateinit var changePasswordButton: Button
    private lateinit var passwordInput: EditText
    private lateinit var authRepository: AuthRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        this.changePasswordButton = findViewById(R.id.ChangePasswordButton)
        this.loginButton = findViewById(R.id.LoginButton)
        this.passwordInput = findViewById(R.id.etOldPassword)
        this.authRepository = AuthRepository(this.applicationContext)

        this.loginButton.setOnClickListener {
            if (!checkIfPasswordSet()) return@setOnClickListener
            checkIfPasswordCorrect()
        }

        this.changePasswordButton.setOnClickListener {
            val intent = Intent(this@LoginActivity, ChangePasswordActivity::class.java)
            startActivity(intent)
        }
    }

    private fun checkIfPasswordSet(): Boolean {
        if (!authRepository.doesPasswordExist()) {
            showShortToast(this.applicationContext, ToastMessages.NO_PASSWORD_SET)
            return false
        }
        return true
    }
    private fun checkIfPasswordCorrect() {
        val isPasswordCorrect = authRepository.checkPassword(passwordInput.text.toString())
        if (isPasswordCorrect) {
            passwordInput.text.clear()
            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
            startActivity(intent)
        } else {
            showShortToast(this.applicationContext, ToastMessages.WRONG_PASSWORD)
        }
    }
}
