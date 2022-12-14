package com.example.mobilesecuritynotes.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import com.example.mobilesecuritynotes.databinding.ActivityLoginBinding
import com.example.mobilesecuritynotes.repositories.AuthRepository
import com.example.mobilesecuritynotes.repositories.biometric.BiometricAuthListener
import com.example.mobilesecuritynotes.repositories.biometric.BiometricRepository
import com.example.mobilesecuritynotes.utils.ToastMessages
import com.example.mobilesecuritynotes.utils.showShortToast

class LoginActivity : AppCompatActivity(), BiometricAuthListener {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var authRepository: AuthRepository
    private lateinit var biometricRepository: BiometricRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.authRepository = AuthRepository(this.applicationContext)
        this.biometricRepository = BiometricRepository(this.applicationContext)

        binding.LoginButton.setOnClickListener {
            if (!checkIfPasswordSet()) return@setOnClickListener
            checkIfPasswordCorrect()
        }

        binding.ChangePasswordButton.setOnClickListener {
            val intent = Intent(this@LoginActivity, ChangePasswordActivity::class.java)
            startActivity(intent)
        }

        if (this.biometricRepository.isBiometricReady()) {
            binding.FingerprintButton.setOnClickListener {
                biometricRepository.showBiometricPrompt(
                    activity = this,
                    listener = this,
                    cryptoObject = null,
                )
            }
        } else {
            binding.FingerprintButton.setOnClickListener {
                showShortToast(applicationContext, ToastMessages.ENABLE_BIOMETRIC_FIRST)
            }
        }
    }

    override fun onBiometricAuthenticateSuccess(result: BiometricPrompt.AuthenticationResult) {
        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
        startActivity(intent)
    }
    override fun onBiometricAuthenticateError(error: Int, errMsg: String) {}

    private fun checkIfPasswordSet(): Boolean {
        if (!authRepository.doesPasswordExist()) {
            showShortToast(this.applicationContext, ToastMessages.NO_PASSWORD_SET)
            return false
        }
        return true
    }
    private fun checkIfPasswordCorrect() {
        val isPasswordCorrect = authRepository.checkPassword(
            binding.EditTextPassword.text.toString()
        )
        if (isPasswordCorrect) {
            binding.EditTextPassword.text.clear()
            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
            startActivity(intent)
        } else {
            showShortToast(this.applicationContext, ToastMessages.WRONG_PASSWORD)
        }
    }
}
