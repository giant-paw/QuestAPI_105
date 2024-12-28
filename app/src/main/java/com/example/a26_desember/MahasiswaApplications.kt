package com.example.a26_desember

import android.app.Application
import com.example.a26_desember.repository.AppContainer
import com.example.a26_desember.repository.MahasiswaContainer

class MahasiswaApplications:Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container= MahasiswaContainer()
    }
}