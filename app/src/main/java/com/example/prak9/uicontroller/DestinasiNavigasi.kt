package com.example.prak9.uicontroller



interface DestinasiNavigasi {

    /**
     * Nama unik untuk menentukan jalur composable.
     */
    val route: String

    /**
     * String resource id yang berisi judul halaman.
     */
    val titleRes: Int
}