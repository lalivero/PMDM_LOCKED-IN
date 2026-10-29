package com.example.locked_in.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DiarioScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // --- ENCABEZADO ---
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Diario de Comidas",
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

        // --- SECCIÓN DE COMIDAS ---
        Text(
            text = "REGISTRO DIARIO",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ComidaDiarioItem(
                titulo = "Desayuno",
                descripcion = "Tostada de aguacate y huevo",
                calorias = 320
            )
            ComidaDiarioItem(
                titulo = "Almuerzo",
                descripcion = "Pechuga de pollo con arroz",
                calorias = 450
            )
            ComidaDiarioItem(
                titulo = "Merienda",
                descripcion = "Yogur griego con frutos rojos",
                calorias = 180
            )
            ComidaDiarioItem(
                titulo = "Cena",
                descripcion = "Pendiente de registrar",
                calorias = 0,
                esPendiente = true
            )
        }
    }
}

@Composable
fun ComidaDiarioItem(
    titulo: String,
    descripcion: String,
    calorias: Int,
    esPendiente: Boolean = false
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (esPendiente) Color(0xFFFAFAFA) else Color(0xFFE8F5E9)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = titulo,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = descripcion,
                    fontSize = 13.sp,
                    color = if (esPendiente) Color.Gray else Color(0xFF2E7D32)
                )
            }

            if (!esPendiente) {
                Text(
                    text = "$calorias kcal",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color(0xFF2E7D32)
                )
            } else {
                IconButton(onClick = { /* Acción para registrar */ }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Añadir",
                        tint = Color(0xFF4CAF50)
                    )
                }
            }
        }
    }
}