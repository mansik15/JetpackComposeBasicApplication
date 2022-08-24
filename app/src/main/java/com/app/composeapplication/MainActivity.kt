package com.app.composeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.composeapplication.ui.theme.ComposeApplicationTheme

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
    NavHost(navController, startDestination = "onboarding") {
        composable(route = "onboarding") {
            OnboardingScreen(navController)
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
fun OnboardingScreen(navController: NavController) {

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

// Compose function to show state list.
@Composable
private fun StateList(navController: NavController, names: List<String> = List(1000) { "$it" }) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "State List") }
            )
        },
        content = {
            LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
                items(items = names) { name ->
                    StateCard(name = name, navController = navController)
                }
            }
        }
    )
}

// Compose function to show a row in state list.
@Composable
private fun StateCard(name: String, navController: NavController) {

    Surface(
        color = Color.DarkGray,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Rounded.Person,
                "contentDescription",
                tint = Color.White
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(5.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "State $name",
                    color = Color.White,
                )
            }
            IconButton(modifier = Modifier.then(Modifier.size(24.dp)),
                onClick = {
                    navController.navigate("detail")
                }) {
                Icon(
                    Icons.Filled.ArrowForward,
                    "contentDescription",
                    tint = Color.White
                )
            }
        }
    }
}

// Compose function to display state details screen
@Composable
private fun CityDetails(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "State Details") },
                navigationIcon = {
                    // navigation icon is use
                    // for drawer icon.
                    IconButton(onClick = { navController.popBackStack() }) {
                        // below line is use to
                        // specify navigation icon.
                        Icon(
                            Icons.Filled.ArrowBack,
                            "contentDescription",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        content = {
            Card(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp),
                shape = RoundedCornerShape(10.dp),
                backgroundColor = Color.DarkGray,
            ) {
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text("City Name", color = Color.White)
                    Text("State Name", color = Color.White)
                    Text("Country Name", color = Color.White)
                    Text("Pin code", color = Color.White)
                }
            }
        }
    )

}