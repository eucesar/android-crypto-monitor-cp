package eucesar.com.github.android_crypto_monitor_cp_oficial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import eucesar.com.github.android_crypto_monitor_cp_oficial.screens.CryptoMonitorScreen
import eucesar.com.github.android_crypto_monitor_cp_oficial.ui.theme.AndroidcryptomonitorcpoficialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidcryptomonitorcpoficialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CryptoMonitorScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}