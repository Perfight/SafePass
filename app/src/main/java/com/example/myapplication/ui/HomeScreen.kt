package com.example.myapplication.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.MainActivity
import com.example.myapplication.data.Account
import com.example.myapplication.data.MainVM
import com.example.myapplication.data.User
import com.example.myapplication.encrypt.SecurityEncrypt

@Composable
fun HomeScreen(viewModel: MainVM, navController: NavController, context: MainActivity) {
    val userId = SecurityEncrypt(context).getData("user_id", 0)
    viewModel.getUserInfo(userId)
    var user by remember { mutableStateOf(User(0, "", "", "")) }
    viewModel.userData.observe(context) {
        user = it
    }
    viewModel.getData(userId)
    var all by remember { mutableStateOf(emptyList<Account>()) }
    viewModel.accounts.observe(context) { all = it }
    viewModel.getAccountCounter(userId)

    var divide by remember {
        mutableStateOf(emptyMap<String, Int>())
    }
    viewModel.accountCounterList.observe(context) {
        divide = it
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(listOf(Color(0xFFe8b7dd), Color(0xFFb7e8d0))),
                shape = RoundedCornerShape(10.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Hello, ${user.name}", fontSize = 28.sp, fontStyle = FontStyle.Italic)
        Spacer(Modifier.fillMaxHeight(0.1f))
        Card(
            modifier = Modifier.clickable {
                navController.navigate("openList")
            },
            border = BorderStroke(2.dp, Color.LightGray)
        ) {
            Row {
                Image(
                    imageVector = Icons.Default.List,
                    contentDescription = null,
                    modifier = Modifier.size(180.dp, 180.dp)
                )
                Column(
                    modifier = Modifier
                        .fillMaxHeight(0.2f)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "All")
                    Text(text = "Contains : " + all.size)
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Card(
                border = BorderStroke(2.dp, Color.LightGray)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight(0.1f)
                        .fillMaxWidth(0.25f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Application")
                    Text(text = "Contains: " + divide["Application"].toString())
                }
            }
            Card(
                border = BorderStroke(2.dp, Color.LightGray)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight(0.1f)
                        .fillMaxWidth(0.32f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Cloud")
                    Text(text = "Contains : " + divide["Cloud"].toString())
                }
            }
            Card(
                border = BorderStroke(2.dp, Color.LightGray)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight(0.1f)
                        .fillMaxWidth(0.45f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Payment")
                    Text(text = "Contains : " + divide["Payment"].toString())
                }
            }
            Card(
                border = BorderStroke(2.dp, Color.LightGray)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight(0.1f)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Website")
                    Text(text = "Contains : " + divide["Website"].toString())
                }
            }
        }


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.15f)
                .clickable {
                    navController.navigate("generate")
                }
                .background(
                    Brush.verticalGradient(listOf(Color(0xFFfae1f4), Color.White)),
                    RoundedCornerShape(10.dp)
                ),
            contentAlignment = Alignment.Center,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Outlined.Build, contentDescription = null)
                Text(text = "Generate new password")
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.15f)
                .clickable {
                    navController.navigate("otherUsers")
                }
                .background(
                    Brush.verticalGradient(listOf(Color(0xFFfae1f4), Color.White)),
                    RoundedCornerShape(10.dp)
                ),
            contentAlignment = Alignment.Center,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Outlined.AccountCircle, contentDescription = null)
                Text(text = "Another accounts")
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    }
}