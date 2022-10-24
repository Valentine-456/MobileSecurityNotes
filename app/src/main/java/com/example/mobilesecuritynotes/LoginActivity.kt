package com.example.mobilesecuritynotes

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var loginButton: Button
    private lateinit var passwordInput: EditText
    private lateinit var usernameInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        this.loginButton = findViewById<Button>(R.id.button)
        this.usernameInput = findViewById<EditText>(R.id.etUsername)
        this.passwordInput = findViewById<EditText>(R.id.etPassword)

        this.loginButton.setOnClickListener {
            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}
