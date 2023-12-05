package com.example.podatking.data

import android.util.Log

class KalkulatorPodatku(
    private val dochod: Double,
    private val kwotaWolna: Double,
    private val ponad26: Boolean
) {

    fun obliczPodatek(): Double {
        val kwota1prog: Double = if(dochod <= 120_000.0) dochod else 120_000.0
        val kwota2prog: Double = if(dochod <= 120_000.0) 0.0 else dochod - 120_000.0

        if(ponad26) {
            var podatek: Double = 0.0

            podatek += pierwszyProg(kwota1prog - kwotaWolna)
            if(dochod <= 120_000.0) {
                return podatek
            } else {
                podatek += drugiProg(kwota2prog)
                return podatek
            }
        } else {
            return drugiProg(kwota2prog)
        }
    }

    fun pierwszyProg(kwota: Double): Double {
        return kwota * 0.12
    }

    fun drugiProg(kwota: Double): Double {
        return kwota * 0.32
    }
}