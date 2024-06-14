package com.example.myapplication.ui

import android.widget.Toast
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.data.MainVM
import com.example.myapplication.data.User
import com.example.myapplication.encrypt.SecurityEncrypt
import java.math.BigInteger
import java.security.MessageDigest

@Composable
fun Auth(viewModel: MainVM, navController: NavController, context: MainActivity) {
    var username by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    var intendedUser: User
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(listOf(Color(0xFFe8b7dd), Color.White)),
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
                    if (username != "" || password != "") {
                        viewModel.getAuthInfo(username)
                        viewModel.currentUser.observe(context) {
                            intendedUser = it
                            if (hash(password) == intendedUser.password) {
                                SecurityEncrypt(context).putData("user_id", intendedUser.idUser)
                                navController.popBackStack()
                                navController.navigate("home")
                            } else {
                                Toast.makeText(
                                    context,
                                    "Check your data!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    } else {
                        Toast.makeText(
                            context,
                            "Check your data!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            )
            ExtendedFloatingActionButton(
                icon = { Icon(Icons.Filled.Person, contentDescription = "Register") },
                text = { Text("Register") },
                onClick = {
                    navController.popBackStack()
                    navController.navigate("registration")
                }
            )
        }
        Spacer(modifier = Modifier.fillMaxHeight(0.1f))
    }
}


fun hash(input: String): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
}