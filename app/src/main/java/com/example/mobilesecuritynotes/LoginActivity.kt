package com.example.mobilesecuritynotes

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mobilesecuritynotes.repositories.AuthRepository

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
        this.passwordInput = findViewById(R.id.etPassword)
        this.authRepository = AuthRepository(this.applicationContext)

        this.loginButton.setOnClickListener {
            if (!authRepository.doesPasswordExist()) {
                Toast.makeText(
                    this,
                    "You don't have a password yet. Change Password:)",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val isPasswordCorrect = authRepository.checkPassword(passwordInput.text.toString())
            if (isPasswordCorrect) {
                val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Wrong password! Try again :)", Toast.LENGTH_SHORT).show()
            }
        }

        this.changePasswordButton.setOnClickListener {
            val intent = Intent(this@LoginActivity, ChangePasswordActivity::class.java)
            startActivity(intent)
        }
    }
}
