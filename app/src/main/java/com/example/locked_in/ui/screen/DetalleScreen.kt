package com.example.locked_in.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.locked_in.R
import com.example.locked_in.viewModel.ComidaViewModel

@Composable
fun DetalleScreen(
    recetaId: Long,
    modifier: Modifier = Modifier,
    comidaViewModel: ComidaViewModel = viewModel()
) {
    // Buscamos la comida correspondiente al ID recibido por la ruta
    val comida = comidaViewModel.listaComidas.find { it.id.toLong() == recetaId }

    if (comida == null) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Receta no encontrada")
        }
    } else {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally // Centra la imagen verticalmente en la pantalla
        ) {
            // --- CAJA DE IMAGEN VERTICAL Y MÁS GRANDE ---
            Box(
                modifier = Modifier
                    .fillMaxWidth() // Ocupa todo el ancho de la pantalla
                    .height(320.dp)  // Alto mayor para que sea formato vertical (retrato)
                    .background(Color(0xFFEFEFEF), shape = RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_food_bank_24),
                    contentDescription = comida.nombre,
                    tint = Color.Gray,
                    modifier = Modifier.size(140.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // --- CONTENIDO DE TEXTO (Alineado a la izquierda para mantener orden) ---
            Column(modifier = Modifier.fillMaxWidth()) {
                // Título de la receta
                Text(
                    text = comida.nombre,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Fila de datos destacados (Calorías, Proteínas y Tipo)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "🔥 ${comida.calorias} kcal",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.DarkGray
                    )
                    Text(
                        text = "💪 ${comida.proteinas}g de proteína",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.DarkGray
                    )
                    Text(
                        text = if (comida.esDulce) "🍰 Dulce" else "🥗 Salado",
                        fontSize = 15.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Descripción detallada
                Text(
                    text = "Descripción",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = comida.descripcion,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.DarkGray,
                    lineHeight = 22.sp
                )

                // Alérgenos (si existen)
                if (comida.etiquetasAlergenos.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Alérgenos: ${comida.etiquetasAlergenos.joinToString(", ")}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}