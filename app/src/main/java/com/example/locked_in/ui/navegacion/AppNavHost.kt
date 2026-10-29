package com.example.locked_in.ui.navegacion

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.locked_in.ui.screen.DetalleScreen
import com.example.locked_in.ui.screen.DiarioScreen
import com.example.locked_in.ui.screen.EntrenoScreen
import com.example.locked_in.ui.screen.HomeScreen
import com.example.locked_in.ui.screen.PerfilScreen
import com.example.locked_in.ui.screen.RecetasScreen

object Rutas {
    const val HOME = "home"
    const val DIARIO = "diario"
    const val RECETAS = "recetas"
    const val ENTRENO = "entreno"
    const val PERFIL = "perfil"
    const val DETALLE = "detalle/{recetaId}"

    fun detalleConId(recetaId: Long) = "detalle/$recetaId"
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Rutas.HOME,
        modifier = modifier
    ) {
        // 1. Pantalla de Inicio / Home
        composable(Rutas.HOME) {
            HomeScreen()
        }

        // 2. Pantalla de Diario
        composable(Rutas.DIARIO) {
            DiarioScreen()
        }

        // 3. Pantalla de Recetas (con la lambda para navegar al detalle)
        composable(Rutas.RECETAS) {
            RecetasScreen(
                onRecetaClick = { recetaId ->
                    navController.navigate(Rutas.detalleConId(recetaId))
                }
            )
        }

        // 4. Pantalla de Entreno
        composable(Rutas.ENTRENO) {
            EntrenoScreen()
        }

        // 5. Pantalla de Perfil
        composable(Rutas.PERFIL) {
            PerfilScreen()
        }

        // 6. Pantalla de Detalle con argumento seguro tipado a Long
        composable(
            route = Rutas.DETALLE,
            arguments = listOf(navArgument("recetaId") { type = NavType.LongType })
        ) { backStackEntry ->
            val recetaId = backStackEntry.arguments?.getLong("recetaId") ?: 0L
            DetalleScreen(recetaId = recetaId)
        }
    }
}