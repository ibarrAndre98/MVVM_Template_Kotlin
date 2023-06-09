package com.example.openpayprueba

import android.app.Application
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import com.example.openpayprueba.ui.MainActivity
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        val intent = Intent(applicationContext, MainActivity::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
            PendingIntent.getActivity(this@BaseApplication, System.currentTimeMillis().toInt(), intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE)
        } else{
            PendingIntent.getActivity(this@BaseApplication, System.currentTimeMillis().toInt(), intent, PendingIntent.FLAG_UPDATE_CURRENT)
        }
    }
}