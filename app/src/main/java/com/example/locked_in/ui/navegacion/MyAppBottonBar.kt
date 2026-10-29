package com.example.locked_in.ui.navegacion

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun MyAppBottomBar(
    navController: NavHostController
) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        // 1. Home / Inicio
        NavigationBarItem(
            selected = currentRoute == Rutas.HOME,
            onClick = { navController.navigate(Rutas.HOME) },
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Inicio") }
        )

        // 2. Diario
        NavigationBarItem(
            selected = currentRoute == Rutas.DIARIO,
            onClick = { navController.navigate(Rutas.DIARIO) },
            icon = { Icon(Icons.Default.DateRange, contentDescription = "Diario") },
            label = { Text("Diario") }
        )

        // 3. Recetas
        NavigationBarItem(
            selected = currentRoute == Rutas.RECETAS,
            onClick = { navController.navigate(Rutas.RECETAS) },
            icon = { Icon(Icons.Default.Restaurant, contentDescription = "Recetas") },
            label = { Text("Recetas") }
        )

        // 4. Entreno
        NavigationBarItem(
            selected = currentRoute == Rutas.ENTRENO,
            onClick = { navController.navigate(Rutas.ENTRENO) },
            icon = { Icon(Icons.Default.FitnessCenter, contentDescription = "Entreno") },
            label = { Text("Entreno") }
        )

        // 5. Perfil
        NavigationBarItem(
            selected = currentRoute == Rutas.PERFIL,
            onClick = { navController.navigate(Rutas.PERFIL) },
            icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") },
            label = { Text("Perfil") }
        )
    }
}