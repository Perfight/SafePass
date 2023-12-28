package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Auth() {
    var username by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.horizontalGradient(listOf(Color(0xFFb7e8d0), Color.White)),
                shape = RoundedCornerShape(10.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Image(
            bitmap = ImageBitmap.imageResource(R.drawable.lock),
            contentDescription = null
        )
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username", color = Color.Gray) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = Color.Gray
                )
            },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
        )


        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password", color = Color.Gray) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    tint = Color.Gray
                )
            },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            ExtendedFloatingActionButton(
                icon = { Icon(Icons.Filled.Check, contentDescription = "Log in") },
                text = { Text("Log in") },
                onClick = {
                    /*TODO*/
                }
            )
            ExtendedFloatingActionButton(
                icon = { Icon(Icons.Filled.Person, contentDescription = "Register") },
                text = { Text("Register") },
                onClick = {
                    /*TODO*/
                }
            )
        }
        Spacer(modifier = Modifier.fillMaxHeight(0.1f))
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewAuth() {
    Auth()
}