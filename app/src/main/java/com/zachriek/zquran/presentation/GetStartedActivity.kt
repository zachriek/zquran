package com.zachriek.zquran.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.zachriek.zquran.databinding.ActivityGetStartedBinding
import com.zachriek.zquran.presentation.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GetStartedActivity : AppCompatActivity() {
    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, GetStartedActivity::class.java))
        }
    }

    private lateinit var binding: ActivityGetStartedBinding

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseCrashlytics.getInstance().sendUnsentReports()

        super.onCreate(savedInstanceState)
        binding = ActivityGetStartedBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        loginViewModel.checkAuth()

        loginViewModel.authentication.observe(this, ::handleAuthentication)

        binding.getStartedButton.setOnClickListener {
            LoginActivity.startActivity(this)
        }
    }

    private fun handleAuthentication(token: String) {
        if (token.isNotEmpty() && token.isNotBlank()) {
            loginViewModel.setToken(token)
            HomeActivity.startActivity(this)
            this.finish()
        }
    }
}