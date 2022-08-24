package com.app.composeapplication.ui.theme.state

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

// Compose function to show state list.
@Composable
fun StateList(navController: NavController, names: List<String> = List(1000) { "$it" }) {
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