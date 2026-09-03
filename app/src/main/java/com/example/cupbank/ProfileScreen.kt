package com.example.cupbank
  
import android.widget.Toast  
import androidx.compose.foundation.background  
import androidx.compose.foundation.layout.Arrangement  
import androidx.compose.foundation.layout.Box  
import androidx.compose.foundation.layout.Column  
import androidx.compose.foundation.layout.Row  
import androidx.compose.foundation.layout.Spacer  
import androidx.compose.foundation.layout.fillMaxSize  
import androidx.compose.foundation.layout.fillMaxWidth  
import androidx.compose.foundation.layout.height  
import androidx.compose.foundation.layout.padding  
import androidx.compose.foundation.layout.size  
import androidx.compose.foundation.shape.RoundedCornerShape  
import androidx.compose.material3.Button  
import androidx.compose.material3.ButtonDefaults  
import androidx.compose.material3.Card  
import androidx.compose.material3.CardDefaults  
import androidx.compose.material3.Scaffold  
import androidx.compose.material3.Text  
import androidx.compose.runtime.Composable  
import androidx.compose.runtime.getValue  
import androidx.compose.runtime.mutableStateOf  
import androidx.compose.runtime.remember  
import androidx.compose.runtime.setValue  
import androidx.compose.ui.Alignment  
import androidx.compose.ui.Modifier  
import androidx.compose.ui.draw.clip  
import androidx.compose.ui.graphics.Color  
import androidx.compose.ui.platform.LocalContext  
import androidx.compose.ui.text.font.FontWeight  
import androidx.compose.ui.text.style.TextAlign  
import androidx.compose.ui.unit.dp  
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.weight


// Paleta de cores usada em toda a tela (tema dark do CupBank)

val CupBankBackground = Color(0xFF0B1020)  
val CupBankSurface = Color(0xFF151C2D)  
val CupBankPurple = Color(0xFF6D3BFF)  
val CupBankPurpleLight = Color(0xFF8B63FF)  
val CupBankTextPrimary = Color(0xFFFFFFFF)  
val CupBankTextSecondary = Color(0xFF9BA3B5)  
val CupBankField = Color(0xFF202A3E)


// ---------------------------------------------------------------------
//  ProfileScreen
// ---------------------------------------------------------------------
//  Tela principal de Perfil. Reune o cabecalho do usuario, o cartao
//  com os dados bancarios, dois botoes de menu e a barra de navegacao
//  inferior. Cada acao de menu exibe um Toast como feedback.
// ---------------------------------------------------------------------
@Composable  
fun ProfileScreen() {  
    val context = LocalContext.current  
  
    var contaVerificada by remember {  
        mutableStateOf(true)  
    }  
  
    Scaffold(  
        containerColor = CupBankBackground  
    ) { innerPadding ->  
        Box(  
            modifier = Modifier  
                .fillMaxSize()  
                .padding(innerPadding)  
                .padding(horizontal = 20.dp)  
        ) {  
            Column(  
                modifier = Modifier.fillMaxSize(),  
                horizontalAlignment = Alignment.CenterHorizontally  
            ) {  
                ProfileHeader(
                    nome = "Lucas Pereira",
                    email = "lucas.pereira@email.com",
                    contaVerificada = contaVerificada
                )

                BankInformationCard()
                
                Spacer(modifier = Modifier.height(24.dp))

                ProfileMenuButton(  
                    title = "Dados Pessoais",  
                    symbol = "ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¢ÃƒÆ’Ã‚Â¢ÃƒÂ¢Ã¢â€šÂ¬Ã…Â¾Ãƒâ€šÃ‚Â¢ÃƒÆ’Ã¢â‚¬Â¦Ãƒâ€šÃ‚Â¸",  
                    onClick = {  
                        Toast.makeText(  
                            context,  
                            "Dados pessoais selecionados.",  
                            Toast.LENGTH_SHORT  
                        ).show()  
                    }  
                )
                
                Spacer(modifier = Modifier.height(12.dp))

                ProfileMenuButton(  
                    title = "CartÃƒÆ’Ã†â€™Ãƒâ€ Ã¢â‚¬â„¢ÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Âµes",  
                    symbol = "ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¢ÃƒÆ’Ã‚Â¢ÃƒÂ¢Ã¢â‚¬Å¡Ã‚Â¬ÃƒÂ¢Ã¢â€šÂ¬Ã…â€œÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â£",  
                    onClick = {  
                    Toast.makeText(  
                        context,  
                        "CartÃƒÆ’Ã†â€™Ãƒâ€ Ã¢â‚¬â„¢ÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Âµes selecionados.",  
                        Toast.LENGTH_SHORT  
                        ).show()  
                    }  
                )

                Spacer(modifier = Modifier.weight(1f))

                ProfileBottomNavigation(  
                    onProfileClick = {  
                    Toast.makeText(  
                        context,  
                        "VocÃƒÆ’Ã†â€™Ãƒâ€ Ã¢â‚¬â„¢ÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Âª jÃƒÆ’Ã†â€™Ãƒâ€ Ã¢â‚¬â„¢ÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â¡ estÃƒÆ’Ã†â€™Ãƒâ€ Ã¢â‚¬â„¢ÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â¡ na tela de Perfil.",  
                        Toast.LENGTH_SHORT  
                        ).show()  
                    }  
                )

            }  
        }  
    }  
}


// ---------------------------------------------------------------------
//  ProfileHeader
// ---------------------------------------------------------------------
//  Avatar circular com a inicial do usuario, nome, e-mail e um selo
//  que indica se a conta foi verificada (controlado por contaVerificada).
// ---------------------------------------------------------------------
@Composable  
fun ProfileHeader(  
    nome: String,  
    email: String,  
    contaVerificada: Boolean  
) {  
    Spacer(modifier = Modifier.height(38.dp))  
  
    Box(  
        modifier = Modifier  
            .size(92.dp)  
            .clip(RoundedCornerShape(46.dp))  
            .background(CupBankPurple),  
        contentAlignment = Alignment.Center  
    ) {  
        Text(  
            text = "L",  
            color = CupBankTextPrimary,  
            fontSize = 36.sp,  
            fontWeight = FontWeight.Bold  
        )  
    }  
  
    Spacer(modifier = Modifier.height(14.dp))  
  
    Text(  
        text = nome,  
        color = CupBankTextPrimary,  
        fontSize = 20.sp,  
        fontWeight = FontWeight.Bold  
    )  
  
    Spacer(modifier = Modifier.height(5.dp))  
  
    Text(  
        text = email,  
        color = CupBankTextSecondary,  
        fontSize = 13.sp  
    )  
  
    Spacer(modifier = Modifier.height(13.dp))  
  
    Row(  
        modifier = Modifier  
            .clip(RoundedCornerShape(20.dp))  
            .background(Color(0xFF123A2B))  
            .padding(horizontal = 12.dp, vertical = 7.dp),  
        verticalAlignment = Alignment.CenterVertically,  
        horizontalArrangement = Arrangement.Center  
    ) {  
        Text(  
            text = if (contaVerificada) "ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¢ÃƒÆ’Ã¢â‚¬Â¦ÃƒÂ¢Ã¢â€šÂ¬Ã…â€œÃƒÆ’Ã‚Â¢ÃƒÂ¢Ã¢â‚¬Å¡Ã‚Â¬Ãƒâ€¦Ã¢â‚¬Å“" else "!",  
            color = Color(0xFF32D583),  
            fontSize = 13.sp,  
            fontWeight = FontWeight.Bold  
        )  
  
        Text(  
            text = if (contaVerificada) {  
                "  Conta verificada"  
            } else {  
                "  Conta nÃƒÆ’Ã†â€™Ãƒâ€ Ã¢â‚¬â„¢ÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â£o verificada"  
            },  
            color = Color(0xFF32D583),  
            fontSize = 12.sp,  
            fontWeight = FontWeight.Medium  
        )  
    }  
}


// ---------------------------------------------------------------------
//  BankInformationCard
// ---------------------------------------------------------------------
//  Card que exibe, lado a lado, os tres dados bancarios do usuario:
//  Agencia, Conta e Tipo de conta.
// ---------------------------------------------------------------------
@Composable  
fun BankInformationCard() {  
    Card(  
        modifier = Modifier  
            .fillMaxWidth()  
            .padding(top = 28.dp),  
        shape = RoundedCornerShape(16.dp),  
        colors = CardDefaults.cardColors(  
            containerColor = CupBankSurface  
        )  
    ) {  
        Row(  
            modifier = Modifier  
                .fillMaxWidth()  
                .padding(vertical = 17.dp, horizontal = 12.dp),  
            horizontalArrangement = Arrangement.SpaceEvenly,  
            verticalAlignment = Alignment.CenterVertically  
        ) {  
            BankInfoItem(  
                title = "AgÃƒÆ’Ã†â€™Ãƒâ€ Ã¢â‚¬â„¢ÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Âªncia",  
                value = "0001"  
            )  
  
            BankInfoItem(  
                title = "Conta",  
                value = "58.234-7"  
            )  
  
            BankInfoItem(  
                title = "Tipo",  
                value = "Corrente"  
            )  
        }  
    }  
}


// Item individual do BankInformationCard: um titulo pequeno acima
// e o valor correspondente em destaque abaixo.
@Composable  
fun BankInfoItem(  
    title: String,  
    value: String  
) {  
    Column(  
        horizontalAlignment = Alignment.CenterHorizontally  
    ) {  
        Text(  
            text = title,  
            color = CupBankTextSecondary,  
            fontSize = 10.sp  
        )  
  
        Spacer(modifier = Modifier.height(4.dp))  
  
        Text(  
            text = value,  
            color = CupBankTextPrimary,  
            fontSize = 13.sp,  
            fontWeight = FontWeight.Bold  
        )  
    }  
}


// ---------------------------------------------------------------------
//  ProfileMenuButton
// ---------------------------------------------------------------------
//  Botao generico de opcao do menu (ex.: "Dados Pessoais", "Cartoes").
//  Renderiza: icone a esquerda, titulo no centro e chevron a direita.
// ---------------------------------------------------------------------
@Composable  
fun ProfileMenuButton(  
    title: String,  
    symbol: String,  
    onClick: () -> Unit  
) {  
    Button(  
        onClick = onClick,  
        modifier = Modifier  
            .fillMaxWidth()  
            .height(56.dp),  
        shape = RoundedCornerShape(14.dp),  
        colors = ButtonDefaults.buttonColors(  
            containerColor = CupBankSurface,  
            contentColor = CupBankTextPrimary  
        )  
    ) {  
        Row(  
            modifier = Modifier.fillMaxWidth(),  
            verticalAlignment = Alignment.CenterVertically  
        ) {  
            Text(  
                text = symbol,  
                color = CupBankPurpleLight,  
                fontSize = 18.sp  
            )  
  
            Text(  
                text = title,  
                modifier = Modifier  
                    .weight(1f)  
                    .padding(start = 12.dp),  
                color = CupBankTextPrimary,  
                fontSize = 14.sp,  
                textAlign = TextAlign.Start  
            )  
  
            Text(  
                text = "ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¢ÃƒÆ’Ã‚Â¢ÃƒÂ¢Ã¢â€šÂ¬Ã…Â¡Ãƒâ€šÃ‚Â¬ÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Âº",  
                color = CupBankTextSecondary,  
                fontSize = 24.sp  
            )  
        }  
    }  
}


// ---------------------------------------------------------------------
//  ProfileBottomNavigation
// ---------------------------------------------------------------------
//  Barra inferior com 4 itens: Inicio, Extrato, botao central "+" e
//  Perfil. Apenas o botao de Perfil tem onClick tratado; os demais
//  ainda sao placeholders.
// ---------------------------------------------------------------------
@Composable  
fun ProfileBottomNavigation(  
    onProfileClick: () -> Unit  
) {  
    Row(  
        modifier = Modifier  
            .fillMaxWidth()  
            .padding(top = 28.dp, bottom = 16.dp),  
        horizontalArrangement = Arrangement.SpaceAround,  
        verticalAlignment = Alignment.CenterVertically  
    ) {  
        NavigationItem(  
            symbol = "ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¢ÃƒÆ’Ã¢â‚¬Â¦ÃƒÂ¢Ã¢â€šÂ¬Ã¢â€žÂ¢ÃƒÆ’Ã‚Â¢ÃƒÂ¢Ã¢â‚¬Å¡Ã‚Â¬Ãƒâ€¦Ã‚Â¡",  
            label = "InÃƒÆ’Ã†â€™Ãƒâ€ Ã¢â‚¬â„¢ÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â­cio",  
            selected = false,  
            onClick = { }  
        )  
  
        NavigationItem(  
            symbol = "ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¢ÃƒÆ’Ã‚Â¢ÃƒÂ¢Ã¢â‚¬Å¡Ã‚Â¬ÃƒÂ¢Ã¢â€šÂ¬Ã…â€œÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â¤",  
            label = "Extrato",  
            selected = false,  
            onClick = { }  
        )  
  
        NavigationItem(  
            symbol = "+",  
            label = "",  
            selected = true,  
            onClick = { }  
        )  
  
        NavigationItem(  
            symbol = "ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¢ÃƒÆ’Ã‚Â¢ÃƒÂ¢Ã¢â€šÂ¬Ã…Â¾Ãƒâ€šÃ‚Â¢ÃƒÆ’Ã‚Â¢ÃƒÂ¢Ã¢â€šÂ¬Ã…Â¾Ãƒâ€šÃ‚Â¢",  
            label = "Perfil",  
            selected = true,  
            onClick = onProfileClick  
        )  
    }  
}


// Item generico da barra de navegacao inferior. Quando nao tem label,
// funciona como botao quadrado de acao (ex.: o "+" central) e recebe
// o destaque da cor roxa quando selecionado.
@Composable
fun NavigationItem(
    symbol: String,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.size(
            width = if (label.isEmpty()) 48.dp else 58.dp,
            height = 48.dp
        ),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp),
        shape = RoundedCornerShape(18.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected && label.isEmpty()) {
                CupBankPurple
            } else {
                Color.Transparent
            },
            contentColor = if (selected) {
                CupBankPurpleLight
            } else {
                CupBankTextSecondary
            }
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = symbol,
                fontSize = if (label.isEmpty()) 24.sp else 18.sp
            )

            if (label.isNotEmpty()) {
                Text(
                    text = label,
                    fontSize = 9.sp
                )
            }
        }
    }
}
