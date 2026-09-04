package com.example.teste2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.teste2.ui.theme.Teste2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Teste2Theme {
                BankAppScreen()
                TelaTransferenciaPix()
                ProfileScreen()
            }
        }
    }
}

private val BackgroundColor = Color(0xFF0D0E17)
private val SurfaceDark = Color(0xFF1B1C29)
private val TextGray = Color(0xFF8A8A9A)
private val AccentPurple = Color(0xFF7B5CFA)
private val AccentGreen = Color(0xFF3DDC84)
private val AccentOrange = Color(0xFFFFB020)

@Composable
fun BankAppScreen() {
    val context = LocalContext.current

    var saldo by remember { mutableDoubleStateOf(3284.50) }
    var valorPixTexto by remember { mutableStateOf("") }
    var ultimaTransacao by remember { mutableStateOf("Maria Souza" to 250.0) }

    Scaffold(
        containerColor = BackgroundColor
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = "Olá,", color = TextGray, fontSize = 16.sp)
                    Text(text = "Lucas", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                }
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(AccentPurple),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "L", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = AccentPurple)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(text = "Saldo Disponível", color = Color.White.copy(alpha = 0.85f), fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "R$ %.2f".format(saldo).replace(".", ","),
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(text = "Conta corrente • BancoPix", color = Color.White.copy(alpha = 0.8f), fontSize = 13.sp)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(text = "AÇÕES RÁPIDAS", color = TextGray, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                QuickAction(label = "PIX", corIcone = AccentOrange)
                QuickAction(label = "Transferir", corIcone = Color.White)
                QuickAction(label = "Pagar", corIcone = Color.White)
                QuickAction(label = "Receber", corIcone = Color.White)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = SurfaceDark
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(text = "Simular recebimento de PIX", color = Color.White, fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = valorPixTexto,
                        onValueChange = { novoTexto -> valorPixTexto = novoTexto },
                        label = { Text("Valor (R$)") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedBorderColor = AccentPurple,
                            unfocusedBorderColor = TextGray
                        )
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = {
                            val valor = valorPixTexto.replace(",", ".").toDoubleOrNull()
                            if (valor != null && valor > 0) {
                                saldo += valor
                                ultimaTransacao = "Maria Souza" to valor
                                valorPixTexto = ""
                                Toast.makeText(context, "PIX de R$ $valor recebido!", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context, "Digite um valor válido", Toast.LENGTH_SHORT).show()
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = AccentPurple)
                    ) {
                        Text(text = "Receber PIX", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "EXTRATO RECENTE", color = TextGray, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                Text(text = "Ver tudo", color = AccentPurple, fontSize = 13.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceDark)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(AccentGreen.copy(alpha = 0.25f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "↓", color = AccentGreen, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "PIX Recebido", color = Color.White, fontWeight = FontWeight.Medium)
                        Text(text = "De: ${ultimaTransacao.first}", color = TextGray, fontSize = 12.sp)
                    }
                    Text(
                        text = "+R$ %.2f".format(ultimaTransacao.second).replace(".", ","),
                        color = AccentGreen,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                color = SurfaceDark
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    NavItem(label = "Início", selected = true)
                    NavItem(label = "Extrato", selected = false)

                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .clip(CircleShape)
                            .background(AccentPurple),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "+", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    }

                    NavItem(label = "Perfil", selected = false)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
private fun QuickAction(label: String, corIcone: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(SurfaceDark),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(corIcone)
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(text = label, color = TextGray, fontSize = 12.sp)
    }
}

@Composable
private fun NavItem(label: String, selected: Boolean) {
    val cor = if (selected) AccentPurple else TextGray
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .clip(CircleShape)
                .background(cor)
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(text = label, color = cor, fontSize = 11.sp)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0D0E17)
@Composable
private fun BankAppScreenPreview() {
    BankAppScreen()
}


@Composable
fun TelaTransferenciaPix() {
    var abaSelecionada by remember { mutableStateOf("Chave PIX") }
    var chavePix by remember { mutableStateOf("") }
    var valor by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)
        ) {
            Text(text = "<", color = Color.White, fontSize = 22.sp)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "Transferência PIX", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = { abaSelecionada = "Chave PIX" },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (abaSelecionada == "Chave PIX") AccentPurple else SurfaceDark
                ),
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Text("Chave PIX", color = Color.White)
            }
            Button(
                onClick = { abaSelecionada = "Contato" },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (abaSelecionada == "Contato") AccentPurple else SurfaceDark
                )
            ) {
                Text("Contato", color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "CHAVE PIX", color = TextGray, fontSize = 12.sp)
        Spacer(modifier = Modifier.height(6.dp))
        TextField(
            value = chavePix,
            onValueChange = { chavePix = it },
            placeholder = { Text("CPF, e-mail, telefone ou chave aleatória", color = TextGray) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = SurfaceDark,
                unfocusedContainerColor = SurfaceDark,
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

        Text(text = "VALOR", color = TextGray, fontSize = 12.sp)
        Spacer(modifier = Modifier.height(6.dp))
        TextField(
            value = valor,
            onValueChange = { valor = it },
            placeholder = { Text("R$ 0,00", color = TextGray) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = SurfaceDark,
                unfocusedContainerColor = SurfaceDark,
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

        Text(text = "DESCRIÇÃO (OPCIONAL)", color = TextGray, fontSize = 12.sp)
        Spacer(modifier = Modifier.height(6.dp))
        TextField(
            value = descricao,
            onValueChange = { descricao = it },
            placeholder = { Text("Ex: aluguel, jantar...", color = TextGray) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = SurfaceDark,
                unfocusedContainerColor = SurfaceDark,
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
            onClick = {},
            colors = ButtonDefaults.buttonColors(containerColor = AccentPurple),
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
            Text("Início", color = TextGray, fontSize = 12.sp)
            Text("Extrato", color = TextGray, fontSize = 12.sp)
            Text("+", color = Color.White, fontSize = 18.sp)
            Text("Perfil", color = TextGray, fontSize = 12.sp)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0D0E17)
@Composable
private fun TelaTransferenciaPixPreview() {
    TelaTransferenciaPix()
}


@Composable
fun ProfileScreen() {
    Scaffold(
        containerColor = BackgroundColor
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(84.dp)
                        .clip(CircleShape)
                        .background(AccentPurple),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "L", color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.Bold)
                }
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .offset(x = (-2).dp, y = (-2).dp)
                        .size(26.dp)
                        .clip(CircleShape)
                        .background(AccentGreen),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "✓", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Lucas Pereira",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "lucas.pereira@email.com",
                color = TextGray,
                fontSize = 14.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(AccentGreen.copy(alpha = 0.15f))
                        .padding(horizontal = 14.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Conta verificada", color = AccentGreen, fontSize = 13.sp, fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = "✓", color = AccentGreen, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(SurfaceDark)
                    .padding(vertical = 16.dp)
            ) {
                InfoColumn(label = "Agência", value = "0001", modifier = Modifier.weight(1f))
                InfoColumn(label = "Conta", value = "58.234-7", modifier = Modifier.weight(1f))
                InfoColumn(label = "Tipo", value = "Corrente", modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(20.dp))

            MenuItem(label = "Dados Pessoais")

            Spacer(modifier = Modifier.height(12.dp))

            MenuItem(label = "Cartões")

            Spacer(modifier = Modifier.weight(1f))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                color = SurfaceDark
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    NavItem(label = "Início", selected = false)
                    NavItem(label = "Extrato", selected = false)

                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .clip(CircleShape)
                            .background(AccentPurple),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "+", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    }

                    NavItem(label = "Perfil", selected = true)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
private fun InfoColumn(label: String, value: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = label, color = TextGray, fontSize = 12.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = value, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun MenuItem(label: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(SurfaceDark)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(AccentPurple)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = label, color = Color.White, fontSize = 15.sp)
        }
        Text(text = ">", color = TextGray, fontSize = 16.sp)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0D0E17)
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen()
}