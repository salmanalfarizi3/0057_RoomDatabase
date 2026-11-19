
package com.example.prak9.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prak9.repositori.RepositoriSiswa
import com.example.prak9.view.route.DestinasiDetailSiswa
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriSiswa: RepositoriSiswa
) : ViewModel() {

    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    private val idSiswa: Int =
        checkNotNull(savedStateHandle[DestinasiDetailSiswa.itemIdArg])

    init {
        viewModelScope.launch {
            uiStateSiswa = repositoriSiswa
                .getSiswaStream(idSiswa)
                .filterNotNull()
                .first()
                .toUiStateSiswa(true)
        }
    }

    fun updateUiState(detailsSiswa: DetailsSiswa) {
        uiStateSiswa = UIStateSiswa(
            detailsSiswa = detailsSiswa,
            isEntryValid = validasiInput(detailsSiswa)
        )
    }

    private fun validasiInput(
        uiState: DetailsSiswa = uiStateSiswa.detailsSiswa
    ): Boolean {
        return with(uiState) {
            nama.isNotBlank() &&
                    alamat.isNotBlank() &&
                    telpon.isNotBlank()
        }
    }

    suspend fun updateSiswa() {
        if (validasiInput(uiStateSiswa.detailsSiswa)) {
            repositoriSiswa.updateSiswa(
                uiStateSiswa.detailsSiswa.toSiswa()
            )
        }
    }
}
