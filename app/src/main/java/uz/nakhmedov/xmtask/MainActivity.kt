package uz.nakhmedov.xmtask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.nakhmedov.xmtask.ui.initial.navigation.initialNavigationRoute
import uz.nakhmedov.xmtask.ui.initial.navigation.initialScreen
import uz.nakhmedov.xmtask.ui.initial.navigation.navigateToMain
import uz.nakhmedov.xmtask.ui.main.navigation.mainScreen
import uz.nakhmedov.xmtask.ui.theme.XMTaskTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            XMTaskTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = initialNavigationRoute
                    ) {
                        initialScreen(onGetStartClick = navController::navigateToMain)
                        mainScreen(onBackClick = { navController.popBackStack() })
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    XMTaskTheme {
        Greeting("Android")
    }
}