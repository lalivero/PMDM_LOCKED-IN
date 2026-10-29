package com.example.locked_in.viewModel

import androidx.lifecycle.ViewModel
import com.example.locked_in.model.Comida

class ComidaViewModel : ViewModel() {

    val listaComidas: List<Comida> = listOf(
        Comida(
            id = 1,
            nombre = "Pechuga de pollo",
            descripcion = "Pechuga de pollo a la plancha",
            esDulce = false,
            calorias = 165,
            proteinas = 31
        ),
        Comida(
            id = 2,
            nombre = "Avena",
            descripcion = "Avena con leche",
            esDulce = true,
            calorias = 250,
            proteinas = 10
        ),
        Comida(
            id = 3,
            nombre = "Ensalada",
            descripcion = "Ensalada de verduras",
            esDulce = false,
            calorias = 180,
            proteinas = 5
        ),
        Comida(
            id = 4,
            nombre = "Yogur griego",
            descripcion = "Yogur griego natural",
            esDulce = true,
            calorias = 120,
            proteinas = 10
        ),
        Comida(
            id = 5,
            nombre = "Arroz con pollo",
            descripcion = "Arroz acompañado de pollo",
            esDulce = false,
            calorias = 450,
            proteinas = 28
        ),
        Comida(
            id = 6,
            nombre = "Tostada con aguacate",
            descripcion = "Pan integral con aguacate",
            esDulce = false,
            calorias = 220,
            proteinas = 6
        ),
        Comida(
            id = 7,
            nombre = "Pasta",
            descripcion = "Pasta con verduras",
            esDulce = false,
            calorias = 380,
            proteinas = 12
        ),
        Comida(
            id = 8,
            nombre = "Fruta con yogur",
            descripcion = "Fruta acompañada de yogur",
            esDulce = true,
            calorias = 190,
            proteinas = 8
        )
    )
}