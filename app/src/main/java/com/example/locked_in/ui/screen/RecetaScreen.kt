package com.example.locked_in.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.locked_in.ui.components.ComidaCard
import com.example.locked_in.viewModel.ComidaViewModel

@Composable
fun RecetasScreen(
    modifier: Modifier = Modifier,
    onRecetaClick: (Long) -> Unit,
    comidaViewModel: ComidaViewModel = viewModel()
) {
    // 1. Estados para el buscador y las pestañas
    var textoBusqueda by remember { mutableStateOf("") }
    var tabSeleccionada by remember { mutableStateOf(0) }
    val titulosTabs = listOf("Sugeridas", "Favoritas")

    // 2. Obtenemos las comidas desde ViewModel
    val todasLasComidas = comidaViewModel.listaComidas

    // Estado reactivo para almacenar los IDs de las recetas marcadas como favoritas
    val idsFavoritos = remember { mutableStateListOf<Int>() }

    // Sugeridas muestra todas, Favoritas muestra solo las marcadas con el corazón
    val listaActual = if (tabSeleccionada == 0) {
        todasLasComidas
    } else {
        todasLasComidas.filter { idsFavoritos.contains(it.id) }
    }

    // 3. Filtrado dinámico por el texto de la lupa
    val listaFiltrada = listaActual.filter { comida ->
        comida.nombre.contains(textoBusqueda, ignoreCase = true)
    }

    Column(modifier = modifier.fillMaxSize()) {
        // --- BARRA DE BÚSQUEDA CON LUPA ---
        OutlinedTextField(
            value = textoBusqueda,
            onValueChange = { textoBusqueda = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            placeholder = { Text(text = "Buscar receta...") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Buscar"
                )
            },
            singleLine = true,
            shape = RoundedCornerShape(12.dp)
        )

        // Selector de pestañas
        TabRow(selectedTabIndex = tabSeleccionada) {
            titulosTabs.forEachIndexed { index, titulo ->
                Tab(
                    selected = tabSeleccionada == index,
                    onClick = { tabSeleccionada = index },
                    text = { Text(text = titulo) }
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Grilla de 2 columnas simétrica
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(listaFiltrada) { comida ->
                val esFavorita = idsFavoritos.contains(comida.id)

                Box(modifier = Modifier.fillMaxWidth()) {
                    ComidaCard(
                        comida = comida,
                        modifier = Modifier.clickable {
                            // Convertimos el Int del ID a Long para cumplir con la ruta
                            onRecetaClick(comida.id.toLong())
                        }
                    )

                    IconButton(
                        onClick = {
                            if (esFavorita) {
                                idsFavoritos.remove(comida.id)
                            } else {
                                idsFavoritos.add(comida.id)
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(4.dp)
                    ) {
                        Icon(
                            imageVector = if (esFavorita) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Favorito",
                            tint = if (esFavorita) Color.Red else Color.Gray
                        )
                    }
                }
            }
        }
    }
}