package com.example.myapplication

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.AccountCircle
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
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.data.Account
import com.example.myapplication.data.MainVM
import com.example.myapplication.data.User

@Composable
fun HomeScreen(viewModel: MainVM, navController: NavController, context: MainActivity){
    viewModel.getUserInfo(1)
    var user by remember { mutableStateOf(User(0, "","","")) }
    viewModel.userData.observe(context){ user = it }
    viewModel.getData(1)
    var all by remember { mutableStateOf(listOf(Account("", "", "", "", 0, 0)))}
    viewModel.accounts.observe(context){all = it}

    var app by remember { mutableStateOf(listOf(Account("", "", "", "", 0, 0)))}
    app = Length(viewModel, 1, "Application", context)
    var cloud by remember { mutableStateOf(listOf(Account("", "", "", "", 0, 0)))}
    cloud = Length(viewModel, 1, "Cloud", context)
    var web by remember { mutableStateOf(listOf(Account("", "", "", "", 0, 0)))}
    web = Length(viewModel, 1, "Website", context)
    var pay by remember { mutableStateOf(listOf(Account("", "", "", "", 0, 0)))}
    pay = Length(viewModel, 1, "Payment", context)

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
        Text("Hello, " + user.name)
        Spacer(Modifier.fillMaxHeight(0.1f))
        LazyRow(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
        ) {
            item {
                Card(
                    modifier = Modifier.clickable {
                                                  navController.navigate("openList")
                    },
                    border = BorderStroke(2.dp, Color.LightGray)
                ) {
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            bitmap = ImageBitmap.imageResource(R.drawable.lock),
                            contentDescription = null,
                            modifier = Modifier.size(180.dp, 180.dp)
                        )
                        Text(text = "All")
                        Text(text = "Contains : " + all.size)
                    }
                }
            }
            item {
                Card(modifier = Modifier.clickable {
                    navController.navigate("openList")
                },
                    border = BorderStroke(2.dp, Color.LightGray)
                ) {
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            bitmap = ImageBitmap.imageResource(R.drawable.app),
                            contentDescription = "app",
                            modifier = Modifier.size(180.dp, 180.dp)
                        )
                        Text(text = "Application")
                        Text(text = "Contains : " + app.size)
                    }
                }
            }
            item {
                Card(modifier = Modifier.clickable {
                    navController.navigate("openList")
                },
                    border = BorderStroke(2.dp, Color.LightGray)
                ) {
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            bitmap = ImageBitmap.imageResource(R.drawable.cloud),
                            contentDescription = "cloud",
                            modifier = Modifier.size(180.dp, 180.dp)
                        )
                        Text(text = "Cloud")
                        Text(text = "Contains : " + cloud.size)
                    }
                }
            }
            item {
                Card(modifier = Modifier.clickable {
                    navController.navigate("openList")
                },
                    border = BorderStroke(2.dp, Color.LightGray)
                ) {
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            bitmap = ImageBitmap.imageResource(R.drawable.pay),
                            contentDescription = null,
                            modifier = Modifier.size(180.dp, 180.dp)
                        )
                        Text(text = "Payment")
                        Text(text = "Contains : " + pay.size)
                    }
                }
            }
            item {
                Card(modifier = Modifier.clickable {
                    navController.navigate("openList")
                },
                    border = BorderStroke(2.dp, Color.LightGray)
                ) {
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            bitmap = ImageBitmap.imageResource(R.drawable.web),
                            contentDescription = null,
                            modifier = Modifier.size(180.dp, 180.dp)
                        )
                        Text(text = "Website")
                        Text(text = "Contains : " + web.size)
                    }
                }
            }
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.15f)
            .clickable {
/*TODO*/
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

@Composable
fun Length(viewModel: MainVM, id: Int, category: String, context: MainActivity) : List<Account>{
    viewModel.divideByCategory(category, id)
    var list by remember { mutableStateOf(listOf(Account("", "", "", "", 0, 0)))}
    viewModel.accounts.observe(context){ list = it}
    return list
}