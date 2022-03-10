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
            CityDetails(navController)
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
private fun CityDetails(navController: NavController){

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
//            Surface (modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)){
            Card(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp),
                shape = RoundedCornerShape(10.dp),
                backgroundColor = Color.DarkGray,
            ){
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Text("City", color = Color.White)
                Text("State", color = Color.White,)
                Text("Country", color = Color.White,)
                Text("Pin code", color = Color.White,)
            }
        }
//            }
        }
    )

}

@Composable
fun OnboardingScreen(navController: NavController) {

    Surface(color = Color(169, 190, 215)) {
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