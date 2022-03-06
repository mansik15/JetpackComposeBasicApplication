package com.app.composeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.composeapplication.ui.theme.ComposeApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeApplicationTheme {
                EntryPoint()
            }
        }
    }
}

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
            CityDetails()
        }
    }

    /*var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
    var shouldShowDetails by rememberSaveable { mutableStateOf(true) }

    if (shouldShowOnboarding) {
        OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
    } else {
        StateList()
    }*/

}

@Composable
private fun StateList(navController: NavController,names: List<String> = List(1000) { "$it" }) {
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

@Composable
private fun StateCard(name: String, navController: NavController) {
    val context = LocalContext.current
    val expanded = rememberSaveable { mutableStateOf(false) }

    val extraPadding = if (expanded.value) 48.dp else 0.dp

    Surface(
        color = Color.DarkGray,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding),
            ) {
                Text(
                    text = "State $name",
                    color = Color.White,
                    /*Modifier.clickable(
                        onClick = {
                            Toast.makeText(context, "OnClick", Toast.LENGTH_LONG).show()
                        }
                    )*/
                )
            }
            /*OutlinedButton(
                onClick = { expanded.value = !expanded.value }
            ) {
                Text(if (expanded.value) "Show less" else "Show more")
            }*/
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

@Composable
private fun CityDetails(){

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "State Details") }
            )
        },
        content = {
            Surface (modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)){
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text("City Name")
                    Text("State")
                    Text("Country")
                    Text("Pin code")
                }
            }
        }
    )

    /*Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text("City Name")
            Text("State")
            Text("Country")
            Text("Pin code")
        }
    }*/

}

@Composable
fun OnboardingScreen(navController: NavController) {

    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to the United Kingdom!", color = MaterialTheme.colors.primary, fontStyle = FontStyle.Italic)
            Button(
                modifier = Modifier
                    .padding(vertical = 24.dp),
                onClick = {navController.navigate("state")}
            ) {
                Text("Explore")
            }
        }
    }
}


@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    ComposeApplicationTheme {
//        OnboardingScreen(onContinueClicked = {})
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeApplicationTheme {
//        StateCard("Amsterdam")
    }
}