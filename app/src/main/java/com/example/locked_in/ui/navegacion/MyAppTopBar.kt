package com.example.locked_in.ui.navegacion

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
                text = when (currentRoute) {
                    Rutas.HOME -> "Locked-In"
                    Rutas.DIARIO -> "Mi Diario"
                    Rutas.RECETAS -> "Recetas"
                    Rutas.ENTRENO -> "Entrenamiento"
                    Rutas.PERFIL -> "Mi Perfil"
                    Rutas.DETALLE -> "Detalle de Receta"
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
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )
}