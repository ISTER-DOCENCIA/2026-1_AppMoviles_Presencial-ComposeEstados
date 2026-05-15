package com.example.appinteractiva

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appinteractiva.ui.theme.AppInteractivaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppInteractivaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    PantallaInicio()
                }
            }
        }
    }
}

@Composable
fun PantallaInicio() {
    var contador by remember { mutableStateOf(0) }
    var mensaje by remember { mutableStateOf("Presiona el boton!") }
    var nombreUsuario by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "App Interactiva",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        TextField(
            value = nombreUsuario,
            onValueChange = {
                textoNuevo -> nombreUsuario = textoNuevo
            },
            label = {Text("Ingresa tu nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = mensaje,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Clicks: $contador",
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Button(
                onClick = {
                    contador++

                    val nombre = nombreUsuario.ifEmpty { "Invitado" }

                    mensaje = when {
                        contador == 1 -> "¡Hola $nombre!"
                        contador < 5 -> "$nombre: $contador clicks"
                        contador < 10 -> "¡Sigue así, $nombre $contador"
                        else -> "$nombre eres increible"
                    }
                }

            ) {
                Text("Saludar")
            }

            //Boton reiniciar
            TextButton (
              onClick = {
                  contador = 0
                    mensaje = "Presiona el boton!"
                    nombreUsuario = ""
              }
            ){
                Text("Reiniciar")
            }

        }


    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppInteractivaTheme {
        PantallaInicio()
    }
}