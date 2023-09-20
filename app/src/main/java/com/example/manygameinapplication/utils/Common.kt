package com.example.manygameinapplication.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.LayoutInflater
import android.widget.Toast
import com.example.manygameinapplication.databinding.ToastCustomBinding
import com.example.manygameinapplication.network.Repository

@SuppressLint("StaticFieldLeak")
object Common {
    val repository = Repository()

    fun openActivitySendData(
        context: Context,
        name: String,
        data: String,
        destinationClass: Class<*>
    ) {
        val intent = Intent(context, destinationClass)
        intent.putExtra(name, data)
        context.startActivity(intent)
    }

    fun createCustomToast(
        activity: Activity,
        message: String,
        layoutInflater: LayoutInflater,
    ) {
        val toast = Toast(activity)
        toast.apply {
            val mBinding = ToastCustomBinding.inflate(layoutInflater)
            mBinding.tvMessageCustomToast.text = message
            duration = Toast.LENGTH_SHORT
            view = mBinding.root
            show()
        }
    }
}