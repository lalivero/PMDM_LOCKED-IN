package com.example.locked_in.model

data class Comida(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val esDulce: Boolean,
    val etiquetasAlergenos: List<String> = emptyList(),
    val calorias: Int,
    val proteinas: Int
)