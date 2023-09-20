package com.example.manygameinapplication.ui

import android.app.Application
import com.example.manygameinapplication.local.Preference

class MainApp : Application() {

    var preference: Preference? = null
    override fun onCreate() {
        super.onCreate()
        instant = this
        preference = Preference.buildInstance(this)
        if (preference?.firstInstall == false) {
            preference?.firstInstall = true
            preference?.setValueCoin(10)
        }
    }

    companion object {
        private var  instant: MainApp? = null
        fun getInstant(): MainApp? {
            if (instant == null) {
                instant = MainApp()
            }
            return instant
        }
    }


}