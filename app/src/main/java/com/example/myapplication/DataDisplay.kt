package com.example.myapplication

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.data.Account
import com.example.myapplication.data.MainVM

@Composable
fun DataDisplay(viewModel: MainVM, navController: NavController, context: MainActivity) {
    viewModel.getData(1)
    var account by remember {
        mutableStateOf(
            listOf(
                Account(
                    "Loading...",
                    "Loading...",
                    1,
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
    LazyColumn {
        items(account) { data ->
            columnItem(data, viewModel)
        }
    }
}


@Composable
fun columnItem(data : Account, viewModel: MainVM) {
    val brush = Brush.horizontalGradient(listOf(Color(0xffd6eaf8), Color(0xffd4efdf)))
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(brush, RoundedCornerShape(20.dp))
    ) {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Column(
                modifier = Modifier
                    .padding(15.dp)
            ) {
                Text(text = data.site, fontSize = 18.sp)
                Text(text = data.username, fontSize = 12.sp)
            }
            IconButton(onClick = {
                viewModel.deleteData(data)

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