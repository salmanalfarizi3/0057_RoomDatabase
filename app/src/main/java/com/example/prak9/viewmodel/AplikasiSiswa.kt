package com.example.prak9.viewmodel

import android.app.Application
import com.example.prak9.repositori.AppDataContainer
import com.example.prak9.repositori.ContainerApp

class AplikasiSiswa : Application() {
    /**
     * AppContainer instance digunakan oleh kelas-kelas lainnya untuk mendapatkan dependensi.
     */
    lateinit var container: ContainerApp

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}