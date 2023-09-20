package com.example.manygameinapplication.ui

import android.os.Handler
import com.example.cookingguide.base.BaseActivity
import com.example.manygameinapplication.R
import com.example.manygameinapplication.databinding.ActivityMainBinding
import com.example.manygameinapplication.ui.fragment.main.FragmentMain
import com.example.manygameinapplication.utils.Common

class MainActivity : BaseActivity<ActivityMainBinding>(
    ActivityMainBinding::inflate
) {
    private var isOnBackPress = 0

    override fun initAction() {
        supportFragmentManager.beginTransaction()
            .add(R.id.FragmentLayout, FragmentMain.newInstance())
            .commit()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        isOnBackPress++
        Common.createCustomToast(
            this@MainActivity,
            "Nhấn quay lại một lần nữa để thoát ứng dụng",
            layoutInflater
        )
        Handler().postDelayed({
            isOnBackPress = 0
        }, 3000)
        if (isOnBackPress == 2) {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        isOnBackPress = 0
    }

    override fun onRestart() {
        super.onRestart()
        isOnBackPress = 0
    }
}