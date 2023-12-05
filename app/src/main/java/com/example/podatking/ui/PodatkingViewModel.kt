package com.example.podatking.ui.theme.ui

import androidx.lifecycle.ViewModel
import com.example.podatking.data.KalkulatorPodatku
import com.example.podatking.data.PodatkingState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PodatkingViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(PodatkingState())
    val uiState: StateFlow<PodatkingState> = _uiState.asStateFlow()

    fun setName(value: String) {
        _uiState.update {
            it.copy(
                imie = value
            )
        }
    }

    fun setCzyKobieta() {
        _uiState.update {
            it.copy(
                czyKobieta = !it.czyKobieta
            )
        }
    }

    fun setDochod(value: String) {
        _uiState.update {
            it.copy(
                dochod = value
            )
        }
    }

    fun setKwota(value: String) {
        _uiState.update {
            it.copy(
                kwota = value
            )
        }
    }

    fun setWiek(value: String) {
        val enum = WiekOption.values().first { it.desc == value }
        if(enum == WiekOption.Do26) {
            _uiState.update {
                it.copy(
                    ponad26 = false
                )
            }
        } else if(enum == WiekOption.Ponad26) {
            _uiState.update {
                it.copy(
                    ponad26 = true
                )
            }
        }
    }

    fun calculatePodatek() {
        _uiState.update {
            it.copy(
                podatek = KalkulatorPodatku(
                    dochod = _uiState.value.dochod.replace(",", ".").toDoubleOrNull() ?: 0.0,
                    kwotaWolna = _uiState.value.kwota.replace(",", ".").toDoubleOrNull() ?: 0.0,
                    ponad26 = _uiState.value.ponad26
                ).obliczPodatek().toString()
            )
        }
    }

    fun validateForm(): Boolean {
        return uiState.value.imie != "" &&
                uiState.value.dochod != "" &&
                uiState.value.kwota != "" &&
                (uiState.value.dochod.replace(",", ".").toDoubleOrNull() ?: 0.0) > 0 &&
                (uiState.value.kwota.replace(",", ".").toDoubleOrNull() ?: 0.0) > 0 &&
                (uiState.value.dochod.replace(",", ".").toDoubleOrNull() ?: 0.0) > (uiState.value.kwota.replace(",", ".").toDoubleOrNull() ?: 0.0)
    }

    fun resetForm() {
        _uiState.value = PodatkingState(kwota = "")
    }
}