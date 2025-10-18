package eucesar.com.github.android_crypto_monitor_cp_oficial.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eucesar.com.github.android_crypto_monitor_cp_oficial.service.MercadoBitcoinServiceFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun CryptoMonitorScreen(modifier: Modifier = Modifier) {
    var lastValue by remember { mutableStateOf("R$ 0,00") }
    var lastDate by remember { mutableStateOf("--/--/---- --:--:--") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    
    val coroutineScope = rememberCoroutineScope()

    fun makeRestCall() {
        coroutineScope.launch {
            isLoading = true
            errorMessage = ""
            try {
                val service = MercadoBitcoinServiceFactory().create()
                val response = service.getTicker()

                if (response.isSuccessful) {
                    val tickerResponse = response.body()

                    val value = tickerResponse?.ticker?.last?.toDoubleOrNull()
                    if (value != null) {
                        val numberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
                        lastValue = numberFormat.format(value)
                    }

                    val date = tickerResponse?.ticker?.date?.let { Date(it * 1000L) }
                    val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
                    lastDate = date?.let { sdf.format(it) } ?: "--/--/---- --:--:--"

                } else {
                    val error = when (response.code()) {
                        400 -> "Bad Request"
                        401 -> "Unauthorized"
                        403 -> "Forbidden"
                        404 -> "Not Found"
                        else -> "Unknown error"
                    }
                    errorMessage = error
                }

            } catch (e: Exception) {
                errorMessage = "Falha na chamada: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF1976D2))
            .padding(32.dp)
    ) {
        Text(
            text = "CRYPTO MONITOR",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            Text(
                text = "Cotação Atual",
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = lastValue,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Última atualização:",
                fontSize = 16.sp,
                color = Color.White,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = lastDate,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Button(
                onClick = { makeRestCall() },
                enabled = !isLoading,
                colors = ButtonDefaults.buttonColors(Color.White),
                modifier = Modifier.size(width = 200.dp, height = 48.dp)
            ) {
                if (isLoading) {
                    Text(
                        text = "Carregando...",
                        fontSize = 16.sp,
                        color = Color.Blue
                    )
                } else {
                    Text(
                        text = "ATUALIZAR",
                        fontSize = 16.sp,
                        color = Color.Blue
                    )
                }
            }

            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    fontSize = 14.sp,
                    color = Color(0xFFFFCDD2),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }
}
