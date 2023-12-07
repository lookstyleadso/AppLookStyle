package com.example.applookstyle.ui.activity.loading

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.applookstyle.databinding.ActivityLoadingBinding
import com.example.applookstyle.ui.activity.login.LoginActivity

class LoadingActivity : AppCompatActivity() {


    private lateinit var binding : ActivityLoadingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initUI()
    }

    private fun initUI() {
        initLoading()
        initViewModel()
    }

    private fun initViewModel() {

    }

    private fun initLoading() {
        binding.ivLogoLoading.animate().alpha(0f).setDuration(0).withEndAction {
            binding.ivLogoLoading.animate().alpha(1f).setDuration(2000).withEndAction {
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }
    }
}