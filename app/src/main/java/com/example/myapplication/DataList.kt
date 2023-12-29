package com.example.myapplication

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.data.Account
import com.example.myapplication.data.MainVM
import com.example.myapplication.encrypt.SecurityEncrypt

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataList(viewModel: MainVM, navController: NavController, context: MainActivity) {
    viewModel.getData(SecurityEncrypt(context).getData("user_id", 1))
    var account by remember {
        mutableStateOf(
            listOf(
                Account(
                    "Loading...",
                    "Loading...",
                    "Loading...",
                    "Loading...",
                    1,
                    1
                )
            )
        )
    }
    viewModel.accounts.observe(context) {
        account = it
    }
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.background(Color.White), title = {
                    IconButton(onClick = {
                        navController.navigate("home")
                    }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            tint = Color.Black,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                content = { Icon(imageVector = Icons.Default.Add, contentDescription = "add") },
                onClick = { navController.navigate("addData") }
            )
            FabPosition.End
        }
    ) {
        LazyColumn(modifier = Modifier.padding(top = it.calculateTopPadding())){
            items(account) { data ->
                columnItem(data, viewModel, navController)
            }
        }
    }

}

@Composable
fun columnItem(data: Account, viewModel: MainVM, navController: NavController) {
    val brush = Brush.horizontalGradient(listOf(Color(0xFFe8b7dd), Color.White))
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(brush, RoundedCornerShape(20.dp))
            .clickable {
                val idAccount = data.id
                navController.navigate("dataOverview/$idAccount")
            }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(14.dp)
            ) {
                Text(text = data.site, fontSize = 18.sp)
                Text(text = data.username, fontSize = 12.sp)
            }
            IconButton(onClick = {
                viewModel.deleteData(data)
                navController.navigate("openList")
            }) {
                Icon(
                    modifier = Modifier.defaultMinSize(40.dp, 28.dp),
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = Color.Gray,
                )
            }
        }
    }
}