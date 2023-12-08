package com.example.applookstyle.ui.activity.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.example.applookstyle.ui.activity.MainActivity
import com.example.applookstyle.databinding.ActivityLoginBinding
import com.example.cronodepro.core.Resource
import com.example.shinyapplication.domain.model.login.Login
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel by viewModels<LoginViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    private fun initUI() {
        initListener()
    }

    private fun initListener() {
        binding.btnLogin.setOnClickListener {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
            if (binding.etEmail.text.isNullOrEmpty() || binding.etPassword.text.isNullOrEmpty()){
                return@setOnClickListener
            }else{
                val email = binding.etEmail.text.toString()
                val password = binding.etPassword.text.toString()
                initViewModel(email,password)
            }
        }
    }

    private fun initViewModel(email: String, password: String) {
        val login = Login(email, password)
        viewModel.authLogin.observe(this) {
            when (it) {
                is Resource.Error -> {
                    println("Error ${it.message}")

                    if (it.message == "Error in the service"){
                        binding.tvErrorMesagge.text = "Usuario y/o Contraseña incorrecta"
                    }
                    else{
                        binding.tvErrorMesagge.text = "Intenta mas tarde"
                    }
                    binding.tvErrorMesagge.isVisible = true
                    binding.progressBar.isVisible = false

                }

                is Resource.Loading -> {
                    binding.progressBar.isVisible = true
                    binding.tvErrorMesagge.isVisible = false

                }

                is Resource.Success -> {
                    binding.progressBar.isVisible = false
                    binding.tvErrorMesagge.isVisible = false
                    if (it.data != null) {
                        println("success")
                        startActivity(Intent(this, MainActivity::class.java))
                    }
                }
            }
        }
        viewModel.getAuthLogin(login)
    }
}