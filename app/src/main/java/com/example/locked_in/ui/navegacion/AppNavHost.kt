package com.example.locked_in.ui.navegacion

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.locked_in.ui.screen.ComidaFormScreen
import com.example.locked_in.ui.screen.DetalleScreen
import com.example.locked_in.ui.screen.DiarioScreen
import com.example.locked_in.ui.screen.EntrenoScreen
import com.example.locked_in.ui.screen.HomeScreen
import com.example.locked_in.ui.screen.LoginFormScreen
import com.example.locked_in.ui.screen.PerfilScreen
import com.example.locked_in.ui.screen.RecetasScreen
import com.example.locked_in.viewModel.ComidaViewModel

object Rutas {
    const val HOME = "home"
    const val DIARIO = "diario"
    const val RECETAS = "recetas"
    const val ENTRENO = "entreno"
    const val PERFIL = "perfil"
    const val DETALLE = "detalle/{recetaId}"
    const val COMIDA_CREAR = "comida_crear"
    const val COMIDA_EDITAR = "comida_editar/{comidaId}"
    const val LOGIN = "login"
    fun detalleConId(recetaId: Long) = "detalle/$recetaId"
    fun comidaEditarConId(comidaId: Long) = "comida_editar/$comidaId"
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier
) {
    // 1. Instanciamos el ViewModel compartido
    val comidaViewModel: ComidaViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Rutas.LOGIN,
        modifier = modifier
    ) {
        // 1. Pantalla del formulario de login
        composable(Rutas.LOGIN) {
            LoginFormScreen(
                onLoginSuccess = {
                    navController.navigate(Rutas.HOME) {
                        popUpTo(Rutas.LOGIN) { inclusive = true }
                    }
                },

            )
        }
        // 2. Pantalla de Inicio / Home
        composable(Rutas.HOME) {
            HomeScreen()
        }

        // 3. Pantalla de Diario
        composable(Rutas.DIARIO) {
            DiarioScreen()
        }

        // 4. Pantalla de Recetas
        composable(Rutas.RECETAS) {
            RecetasScreen(
                comidaViewModel = comidaViewModel,
                onRecetaClick = { recetaId ->
                    navController.navigate(Rutas.detalleConId(recetaId))
                },
                onAddComidaClick = {
                    navController.navigate(Rutas.COMIDA_CREAR)
                }
            )
        }

        // 5. Pantalla de Entreno
        composable(Rutas.ENTRENO) {
            EntrenoScreen()
        }

        // 6. Pantalla de Perfil
        composable(Rutas.PERFIL) {
            PerfilScreen()
        }

        // 7. Pantalla de Detalle
        composable(
            route = Rutas.DETALLE,
            arguments = listOf(navArgument("recetaId") { type = NavType.LongType })
        ) { backStackEntry ->
            val recetaId = backStackEntry.arguments?.getLong("recetaId") ?: 0L
            DetalleScreen(
                recetaId = recetaId,
                comidaViewModel = comidaViewModel
            )
        }

        // 8. Ruta para CREAR una comida nueva (usa el ViewModel compartido)
        composable(Rutas.COMIDA_CREAR) {
            ComidaFormScreen(
                comidaInicial = null,
                onSaveClick = { comidaFinal ->
                    comidaViewModel.agregarComida(comidaFinal)
                    navController.popBackStack()
                },
                onCancelClick = {
                    navController.popBackStack()
                }
            )
        }

        // 9. Ruta para EDITAR una comida existente (usa el ViewModel compartido)
        composable(
            route = Rutas.COMIDA_EDITAR,
            arguments = listOf(navArgument("comidaId") { type = NavType.LongType })
        ) { backStackEntry ->
            val comidaId = backStackEntry.arguments?.getLong("comidaId") ?: 0L
            val comidaAEditar = comidaViewModel.listaComidas.find { it.id.toLong() == comidaId }

            ComidaFormScreen(
                comidaInicial = comidaAEditar,
                onSaveClick = { comidaActualizada ->
                    comidaViewModel.actualizarComida(comidaActualizada)
                    navController.popBackStack()
                },
                onCancelClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}