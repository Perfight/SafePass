package com.example.myapplication

import android.annotation.SuppressLint
import android.content.ClipboardManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.data.Dependencies
import com.example.myapplication.data.MainVM
import com.example.myapplication.encrypt.SecurityEncrypt

class MainActivity : ComponentActivity() {
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Dependencies.init(applicationContext)
        val viewModel = MainVM(Dependencies.managerRepository)
        var start = "main"
        val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager

        setContent {
            val navController = rememberNavController()
            if (SecurityEncrypt(this@MainActivity).containsKey("user_id")) {
                start = "home"
            }
            NavHost(
                navController = navController,
                startDestination = start
            ) {
                composable("main") {
                    Auth(viewModel, navController, this@MainActivity)
                }
                composable("registration") {
                    Register(viewModel, navController, this@MainActivity)
                }
                composable("otherUsers") {
                    UsersList(viewModel, navController, this@MainActivity)
                }
                composable("home") {
                    HomeScreen(viewModel, navController, this@MainActivity)
                }
                composable("addData") {
                    SaveData(viewModel, navController, this@MainActivity)
                }
                composable("openList") {
                    DataList(viewModel, navController, this@MainActivity)
                }
                composable("generate") {
                    CustomPassword(this@MainActivity, clipboard)
                }
                composable(
                    "dataOverview/{idAccount}",
                    arguments = listOf(navArgument("idAccount") { type = NavType.IntType })
                ) { backStackEntry ->
                    val argumentValue = backStackEntry.arguments?.getInt("idAccount") ?: 1
                    DataOverview(
                        viewModel,
                        navController,
                        this@MainActivity,
                        clipboard,
                        argumentValue
                    )
                }
            }
        }
    }
}