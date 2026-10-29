package com.example.locked_in.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EntrenoScreen(modifier: Modifier = Modifier) {
    val totalCaloriasQuemadas = 350

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
                text = "Entrenamiento",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "Actividad física y gasto calórico",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }

        // --- 2. TARJETA RESUMEN DE CALORÍAS QUEMADAS ---
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0)),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(Color.White, shape = RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.LocalFireDepartment,
                        contentDescription = null,
                        tint = Color(0xFFFF9800),
                        modifier = Modifier.size(36.dp)
                    )
                }
                Column {
                    Text(
                        text = "Total quemado hoy",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = "$totalCaloriasQuemadas kcal",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFE65100)
                    )
                }
            }
        }

        // --- 3. LISTA DE EJERCICIOS ---
        Text(
            text = "EJERCICIOS REGISTRADOS",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            EntrenoItemCard(
                nombre = "Entrenamiento de Fuerza",
                duracion = "45 min",
                calorias = "250 kcal"
            )
            EntrenoItemCard(
                nombre = "Caminata ligera",
                duracion = "30 min",
                calorias = "100 kcal"
            )
        }

        // --- 4. BOTÓN DE ACCIÓN ---
        Spacer(modifier = Modifier.height(4.dp))
        Button(
            onClick = { /* Acción para añadir nuevo ejercicio */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Registrar nuevo ejercicio",
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Composable
fun EntrenoItemCard(
    nombre: String,
    duracion: String,
    calorias: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFAFAFA)),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = nombre,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Duración: $duracion",
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }
            Text(
                text = calorias,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color(0xFFFF9800)
            )
        }
    }
}