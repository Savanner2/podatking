package com.example.podatking.data

data class PodatkingState(
    val imie: String = "",
    val dochod: String = "",
    val kwota: String = "30000",
    val czyKobieta: Boolean = false,
    val ponad26: Boolean = false,
    val podatek: String = ""
)