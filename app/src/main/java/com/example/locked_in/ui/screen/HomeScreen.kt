package com.example.locked_in.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.locked_in.R

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    // --- BALANCE CALÓRICO ---
    val objetivoTotal = 1700
    val caloriasIngeridas = 1200
    val caloriasQuemadas = 350

    // Cálculo de déficit: las calorías restantes dependen solo de lo ingerido frente al objetivo
    val caloriasRestantes = objetivoTotal - caloriasIngeridas

    // El progreso mide qué porcentaje de tu presupuesto diario has consumido
    val progresoCircular = caloriasIngeridas.toFloat() / objetivoTotal.toFloat()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // --- 1. ENCABEZADO ---
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Hola, Laura 👋",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "Lunes, 5 de Octubre",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }

        // --- 2. BALANCE DE HOY (Anillo Central) ---
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFAFAFA)),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "BALANCE DE HOY",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.size(170.dp)
                ) {
                    CircularProgressIndicator(
                        progress = { 1f },
                        modifier = Modifier.fillMaxSize(),
                        color = Color(0xFFE0E0E0),
                        strokeWidth = 12.dp
                    )
                    CircularProgressIndicator(
                        progress = { progresoCircular.coerceIn(0f, 1f) },
                        modifier = Modifier.fillMaxSize(),
                        color = Color(0xFF4CAF50),
                        strokeWidth = 12.dp,
                        strokeCap = StrokeCap.Round
                    )
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "$caloriasRestantes",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Text(
                            text = "kcal restantes",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Restaurant,
                            contentDescription = null,
                            tint = Color(0xFF4CAF50),
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.size(6.dp))
                        Column {
                            Text(text = "Ingeridas", fontSize = 11.sp, color = Color.Gray)
                            Text(
                                text = "$caloriasIngeridas kcal",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        }
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.LocalFireDepartment,
                            contentDescription = null,
                            tint = Color(0xFFFF9800),
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.size(6.dp))
                        Column {
                            Text(text = "Quemadas", fontSize = 11.sp, color = Color.Gray)
                            Text(
                                text = "$caloriasQuemadas kcal",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }

        // --- 3. REGISTRO RÁPIDO (4 comidas en 1ª fila, Ejercicio centrado en 2ª) ---
        Text(
            text = "REGISTRO RÁPIDO",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Fila 1: Las 4 comidas principales
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                BotonPildora(texto = "Desayuno", modifier = Modifier.weight(1f))
                BotonPildora(texto = "Almuerzo", modifier = Modifier.weight(1f))
                BotonPildora(texto = "Merienda", modifier = Modifier.weight(1f))
                BotonPildora(texto = "Cena", modifier = Modifier.weight(1f))
            }

            // Fila 2: Ejercicio centrado con el mismo tamaño relativo que 1 botón de arriba
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(0.25f)
                ) {
                    BotonPildora(
                        texto = "Ejercicio",
                        modifier = Modifier.fillMaxWidth(),
                        colorTexto = Color(0xFFFF9800),
                        colorFondo = Color(0xFFFFF3E0)
                    )
                }
            }
        }

        // --- 4. SUGERENCIA PARA LA CENA ---
        Text(
            text = "SUGERENCIA PARA LA CENA",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .background(Color.White, shape = RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_food_bank_24),
                        contentDescription = null,
                        tint = Color(0xFF4CAF50),
                        modifier = Modifier.size(36.dp)
                    )
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Bowl de Quinoa y Aguacate",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Baja en calorías • Proteica",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF2E7D32)
                    )
                }
            }
        }
    }
}

@Composable
fun BotonPildora(
    texto: String,
    modifier: Modifier = Modifier,
    colorTexto: Color = Color(0xFF4CAF50),
    colorFondo: Color = Color(0xFFE8F5E9)
) {
    Card(
        modifier = modifier,
        shape = CircleShape,
        colors = CardDefaults.cardColors(containerColor = colorFondo)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 2.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = colorTexto,
                modifier = Modifier.size(12.dp)
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                text = texto,
                fontSize = 9.5.sp,
                fontWeight = FontWeight.Bold,
                color = colorTexto
            )
        }
    }
}