package com.example.locked_in.ui.navegacion

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppTopBar(
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    TopAppBar(
        title = {
            Text(
                text = when {
                    currentRoute == Rutas.HOME -> "Locked-In"
                    currentRoute == Rutas.DIARIO -> "Mi Diario"
                    currentRoute == Rutas.RECETAS -> "Recetas"
                    currentRoute == Rutas.ENTRENO -> "Entrenamiento"
                    currentRoute == Rutas.PERFIL -> "Mi Perfil"
                    currentRoute?.startsWith("detalle") == true -> "Receta"
                    currentRoute == Rutas.COMIDA_CREAR -> "Crear Comida"
                    currentRoute?.startsWith("comida_editar") == true -> "Editar Comida"
                    else -> "Locked-In"
                },
                style = MaterialTheme.typography.headlineLarge
            )
        },
        navigationIcon = {
            // Si no estamos en el Home, mostramos el botón de volver atrás
            if (currentRoute != Rutas.HOME) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Volver"
                    )
                }
            }
        },

        actions = {
            // Si estamos en la pantalla de detalle, mostramos el lápiz arriba a la derecha
            if (currentRoute?.startsWith("detalle") == true) {
                val recetaId = navBackStackEntry?.arguments?.getLong("recetaId") ?: 0L
                IconButton(
                    onClick = {
                        navController.navigate(Rutas.comidaEditarConId(recetaId))
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Editar receta"
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )
}