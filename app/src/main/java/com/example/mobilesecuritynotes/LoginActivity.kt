package com.example.mobilesecuritynotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class LoginActivity : AppCompatActivity() {
    private lateinit var loginButton: Button;
    private lateinit var passwordInput: EditText;
    private lateinit var usernameInput: EditText;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        this.loginButton = findViewById<Button>(R.id.button);
        this.usernameInput = findViewById<EditText>(R.id.etUsername);
        this.passwordInput = findViewById<EditText>(R.id.etPassword);

        this.loginButton.setOnClickListener {
//            Toast
//                .makeText(
//                    this@LoginActivity,
//                    "Username: ${usernameInput.text}\nPassword: ${passwordInput.text}",
//                    Toast.LENGTH_LONG
//                )
//                .show()

            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}