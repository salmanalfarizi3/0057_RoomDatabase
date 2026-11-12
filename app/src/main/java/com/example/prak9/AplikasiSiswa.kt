package com.example.prak9


import android.app.Application
import com.example.prak9.repositori.ContainerApp
import com.example.prak9.repositori.ContainerDataApp


class AplikasiSiswa : Application() {

    /**
     * AppContainer instance digunakan oleh kelas-kelas lainnya
     * untuk mendapatkan dependensi.
     */
    lateinit var container: ContainerApp

    override fun onCreate() {
        super.onCreate()
        container = ContainerDataApp(this)
    }
}