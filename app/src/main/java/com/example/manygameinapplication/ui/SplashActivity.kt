package com.example.manygameinapplication.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import com.example.cookingguide.base.BaseActivity
import com.example.manygameinapplication.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<ActivitySplashBinding>(
    ActivitySplashBinding::inflate
) {
    override fun initAction() {
        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }, 2000)
    }
}