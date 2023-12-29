package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.data.MainVM
import com.example.myapplication.encrypt.SecurityEncrypt

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("NewApi", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SaveData(viewModel: MainVM, navController: NavController, context: Context) {
    var website by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var expanded by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf("") }
    val listOfCategories = listOf("Application", "Cloud", "Payment", "Website")

    var iconClickable by remember { mutableStateOf(true) }
    //val c = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    //PasswordGeneratorScreen(c)

    Scaffold(
        topBar = {
            TopAppBar(title = {
                IconButton(onClick = {
                    navController.navigate("openList")
                }) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            })
        }
    ) {
        it.calculateTopPadding()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = website,
                onValueChange = { website = it },
                label = { Text("Site", color = MaterialTheme.colorScheme.primary) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = null,
                        tint = Color.Gray
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            Box {
                OutlinedTextField(
                    value = selectedCategory,
                    onValueChange = { selectedCategory = it },
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth(),
                    label = { Text("Category") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Build,
                            contentDescription = null,
                            tint = Color.Gray
                        )
                    },
                    trailingIcon = {
                        Icon(Icons.Default.KeyboardArrowDown, "contentDescription",
                            Modifier.clickable { expanded = !expanded })
                    }
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .defaultMinSize(50.dp, 50.dp)
                ) {
                    listOfCategories.forEach { category ->
                        DropdownMenuItem(
                            text = { Text(text = category) },
                            onClick = {
                                selectedCategory = category
                            })
                    }
                }
            }

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username", color = MaterialTheme.colorScheme.primary) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = Color.Gray
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password", color = MaterialTheme.colorScheme.primary) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                        tint = Color.Gray
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ExtendedFloatingActionButton(
                    icon = { Icon(Icons.Filled.Home, contentDescription = "Save") },
                    text = { Text("Skip") },
                    onClick = {
                        navController.navigate("openList")
                    }
                )

                if (iconClickable) {
                    ExtendedFloatingActionButton(
                        icon = { Icon(Icons.Filled.Add, contentDescription = "Save") },
                        text = { Text("Save") },
                        onClick = {
                            viewModel.insertData(
                                username,
                                SecurityEncrypt(context).putDataPass(password),
                                selectedCategory,
                                website,
                                1
                            )
                            iconClickable = false
                            navController.navigate("openList")
                        }
                    )
                }
            }
        }
    }
}