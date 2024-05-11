package com.example.calculadoraimc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculadoraimc.ui.theme.CalculadoraIMCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraIMCTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalculatorContent(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CalculatorContent(modifier: Modifier = Modifier) {
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(modifier = modifier.padding(16.dp)) {
        TextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Digite o seu peso (kg): ") }
        )
        TextField(
            value = height,
            onValueChange = { height = it },
            label = { Text("Digite a sua altura (m): ") }
        )
        Button(
            onClick = {
                val weightValue = weight.toFloatOrNull()
                val heightValue = height.toFloatOrNull()
                if (weightValue != null && heightValue != null) {
                    val bmi = calculateIMC(weightValue, heightValue)
                    result = when {
                        bmi < 18.5 -> "Você está abaixo do peso."
                        bmi < 24.9 -> "Você está no peso normal."
                        bmi < 29.9 -> "Você está acima do peso."
                        bmi < 34.9 -> "Você está com obesidade Classe I."
                        else -> "Você está com obesidade Classe II."
                    }
                } else {
                    result = "Erro! Valor inválido."
                }
            }
        ) {
            Text("Calcular IMC")
        }
        Text(text = result)
    }
}

fun calculateIMC(weight: Float, height: Float): Float {
    return weight / (height * height)
}

@Preview(showBackground = true)
@Composable
fun CalculatorContentPreview() {
    CalculadoraIMCTheme {
        CalculatorContent()
    }
}
