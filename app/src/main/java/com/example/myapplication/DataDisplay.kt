package com.example.myapplication

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.data.Account
import com.example.myapplication.data.MainVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataDisplay(viewModel: MainVM, context : MainActivity) {
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
        items(account){ data ->
            Card(modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 50.dp)
                .padding(3.dp),
                shape = RoundedCornerShape(10.dp),
                onClick = {
                    /*TODO*/
                }
            ) {
                Box(
                    contentAlignment = Alignment.BottomStart,
                    modifier = Modifier
                        .fillMaxWidth(),

                    ) {
                    Text(text = data.site)
                }
            }
        }
    }
}