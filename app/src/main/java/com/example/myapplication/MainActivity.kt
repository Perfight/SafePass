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
            if (SecurityEncrypt(this@MainActivity).containsKey("user_id")){
                start = "home"
            }
            NavHost(
                navController = navController,
                startDestination = start
            ) {
                composable("main") {
                    Auth(viewModel, navController, this@MainActivity)
                }
                composable("registration"){
                    Register(viewModel, navController, this@MainActivity)
                }
                composable("otherUsers"){
                    UsersList(viewModel, navController, this@MainActivity)
                }
                composable("home"){
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
                    DataOverview(viewModel, navController, this@MainActivity, clipboard, argumentValue)
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

/*
@Composable
fun PasswordGeneratorScreen(clipboard: ClipboardManager) {
    //var password by remember { mutableStateOf(generatePassword(12)) }
    var copiedToClipboard by remember { mutableStateOf(false) }

    Row(

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
                //password = generatePassword(12)
                copiedToClipboard = false
            },
            modifier = Modifier
                .size(48.dp)
                .padding(8.dp)
        ) {
            Icon(imageVector = Icons.Default.Refresh, contentDescription = null)
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

fun generatePassword(
    isWithLetters: Boolean,
    isWithUppercase: Boolean,
    isWithNumbers: Boolean,
    isWithSpecial: Boolean,
): String {
    val letters : String = "abcdefghijklmnopqrstuvwxyz"
    val uppercaseLetters : String = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    val numbers : String = "0123456789"
    val special : String = "@#=+!£$%&?-"

    val charSets = mutableListOf<String>()

    if (isWithLetters) { charSets.add(letters) }
    if (isWithUppercase) { charSets.add(uppercaseLetters) }
    if (isWithNumbers) { charSets.add(numbers) }
    if (isWithSpecial) { charSets.add(special) }

    if (charSets.size < 1) { return "" }

    var result = charSets.joinToString("")
    if (isWithLetters && isWithUppercase && isWithNumbers && isWithSpecial) {
        result += "${letters.random()}${uppercaseLetters.random()}${numbers.random()}${special.random()}"
    }

    result = result.toList().shuffled().joinToString("")

    return result.substring(0, 8.coerceAtMost(result.length))
}*/