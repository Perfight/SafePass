package com.example.myapplication

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.data.Dependencies
import com.example.myapplication.data.MainVM
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Dependencies.init(applicationContext)
        val viewModel = MainVM(Dependencies.managerRepository)
        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "main"
            ) {
                composable("main") {
                    HomeScreen(viewModel, navController, this@MainActivity)
                }
                composable("addData"){
                    SaveData(viewModel, navController, this@MainActivity)
                }
                composable("openList") {
                    DataList(viewModel, navController, this@MainActivity)
                }
                composable("dataOverview/{idAccount}",
                    arguments = listOf(navArgument("idAccount") { type = NavType.IntType })
                    ){backStackEntry ->
                    val argumentValue = backStackEntry.arguments?.getInt("idAccount") ?: 1
                    DataOverview(viewModel, navController, this@MainActivity, argumentValue)
                }
                /*
                * HomeScreen() -- if authorisized
                * SaveData() -- after home|overview
                * DataDisplay() -- after home -- list
                * DataOverview() -- last activity --
                * Auth() -- first activity|home
                * Register() -- auth -> register -> home
                * */
            }
        }
    }
}

@Composable
fun PasswordGeneratorScreen(clipboard: ClipboardManager) {
    var password by remember { mutableStateOf(generatePassword(12)) }
    var copiedToClipboard by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                copiedToClipboard = false
            },
            label = { Text("Generated Password") },
            modifier = Modifier
                .padding(8.dp),
            //visualTransformation = PasswordVisualTransformation(),
            readOnly = true
        )

        IconButton(
            onClick = {
                password = generatePassword(12)
                copiedToClipboard = false
            },
            modifier = Modifier
                .size(48.dp)
                .padding(8.dp)
        ) {
            Icon(imageVector = Icons.Default.Refresh, contentDescription = null)
        }
        IconButton(
            onClick = {
                clipboard.setPrimaryClip(ClipData.newPlainText("password", password))
                copiedToClipboard = true
            },
            modifier = Modifier
                .size(48.dp)
                .padding(8.dp)
        ) {
            Icon(imageVector = Icons.Default.Done, contentDescription = null)
        }

        if (copiedToClipboard) {
            Text(
                "Password copied to clipboard",
                color = Color.Green,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

fun generatePassword(length: Int): String {
    val charPool: List<Char> =
        ('a'..'z') + ('A'..'Z') + ('0'..'9') + listOf('!', '@', '#', '$', '%', '^', '&', '*')
    return (1..length)
        .map { Random.nextInt(0, charPool.size) }
        .map(charPool::get)
        .joinToString("")
}