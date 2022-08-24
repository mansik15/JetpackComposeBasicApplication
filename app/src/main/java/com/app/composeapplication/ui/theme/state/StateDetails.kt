package com.app.composeapplication.ui.theme.state

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

// Compose function to display state details screen
@Composable
fun CityDetails(navController: NavController) {
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