package com.example.cupbank

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TelaTransferenciaPix()

        }
    }
}

val corFundo = Color(0xFF0E0E1A)
val corCampo = Color(0xFF1C1C2E)
val corRoxo = Color(0xFF6C4CE0)
val corTextoCinza = Color(0xFF9A9AAE)

@Composable
fun TelaTransferenciaPix() {

    var abaSelecionada by remember { mutableStateOf("Chave PIX") }
    var chavePix by remember { mutableStateOf("") }
    var valor by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(corFundo)
            .padding(20.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)
        ) {
            Text(
                text = "<",
                color = Color.White,
                fontSize = 22.sp
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Transferência PIX",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = { abaSelecionada = "Chave PIX" },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (abaSelecionada == "Chave PIX") corRoxo else corCampo
                ),
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Text("Chave PIX", color = Color.White)
            }
            Button(
                onClick = { abaSelecionada = "Contato" },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (abaSelecionada == "Contato") corRoxo else corCampo
                )
            ) {
                Text("Contato", color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "CHAVE PIX", color = corTextoCinza, fontSize = 12.sp)
        Spacer(modifier = Modifier.height(6.dp))
        TextField(
            value = chavePix,
            onValueChange = { chavePix = it },
            placeholder = { Text("CPF, e-mail, telefone ou chave aleatória", color = corTextoCinza) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = corCampo,
                unfocusedContainerColor = corCampo,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "VALOR", color = corTextoCinza, fontSize = 12.sp)
        Spacer(modifier = Modifier.height(6.dp))
        TextField(
            value = valor,
            onValueChange = { valor = it },
            placeholder = { Text("R$ 0,00", color = corTextoCinza) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = corCampo,
                unfocusedContainerColor = corCampo,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "DESCRIÇÃO (OPCIONAL)", color = corTextoCinza, fontSize = 12.sp)
        Spacer(modifier = Modifier.height(6.dp))
        TextField(
            value = descricao,
            onValueChange = { descricao = it },
            placeholder = { Text("Ex: aluguel, jantar...", color = corTextoCinza) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = corCampo,
                unfocusedContainerColor = corCampo,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                println("Chave: $chavePix | Valor: $valor | Descrição: $descricao")
            },
            colors = ButtonDefaults.buttonColors(containerColor = corRoxo),
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(text = "Continuar", color = Color.White, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text("Início", color = corTextoCinza, fontSize = 12.sp)
            Text("Extrato", color = corTextoCinza, fontSize = 12.sp)
            Text("+", color = Color.White, fontSize = 18.sp)
            Text("Perfil", color = corTextoCinza, fontSize = 12.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTelaTransferenciaPix() {
    TelaTransferenciaPix()
}