package com.example.flashcardsapp.login

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flashcardsapp.login.sharedPreferencesManager.SharedPreferencesManager

@SuppressLint("MutableCollectionMutableState")
@Composable
fun MainScreen(sharedPreferencesManager: SharedPreferencesManager) {
    val username = sharedPreferencesManager.getUsername() ?: "Usuário Desconhecido"
    var materia by remember { mutableStateOf("") }
    var listaMaterias = remember {
        mutableStateListOf<String>()
    }

    LaunchedEffect(Unit) {
        val savedMaterias = sharedPreferencesManager.getMaterias()
        if (savedMaterias != null) {
            listaMaterias.addAll(savedMaterias)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Bem-vindo, $username!", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = materia,
            onValueChange = { materia = it },
            label = { Text("Nome da Matéria") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            if (materia.isNotEmpty()) {
                listaMaterias.add(materia)
                materia = ""
                sharedPreferencesManager.saveMaterias(listaMaterias.toSet())
            }
        }) {
            Text("Adicionar Matéria")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Suas Matérias:", fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))
        listaMaterias.forEach {
            Text(it, fontSize = 16.sp)
        }
    }
}