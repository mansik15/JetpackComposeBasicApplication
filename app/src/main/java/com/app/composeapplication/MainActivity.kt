package com.app.composeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.composeapplication.ui.theme.ComposeApplicationTheme
import com.app.composeapplication.ui.theme.state.CityDetails
import com.app.composeapplication.ui.theme.state.StateList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            // Entry point of the application
            ComposeApplicationTheme {
                EntryPoint()
            }
        }
    }
}

// Compose function to declare routes of application & start destination.
@Composable
fun EntryPoint() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "onBoarding") {
        composable(route = "onBoarding") {
            OnBoardingScreen(navController)
        }
        composable(route = "state") {
            StateList(navController)
        }
        composable(route = "detail") {
            CityDetails(navController)
        }
    }
}

// Compose function to add on boarding screen after splash.
@Composable
fun OnBoardingScreen(navController: NavController) {
    Surface(color = Color(169, 190, 215)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Welcome to the United Kingdom!",
                color = MaterialTheme.colors.primary,
                fontStyle = FontStyle.Italic
            )
            // Explore button click will navigate to state listing screen
            Button(
                modifier = Modifier
                    .padding(vertical = 24.dp),
                onClick = { navController.navigate("state") }
            ) {
                Text("Explore")
            }
        }
    }
}
