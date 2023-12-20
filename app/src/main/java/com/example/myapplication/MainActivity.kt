package com.example.myapplication

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.data.Dependencies
import com.example.myapplication.data.MainVM
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Dependencies.init(applicationContext)
        val viewModel = MainVM(Dependencies.managerRepository)
        setContent {
            var categories by remember {
                mutableStateOf(emptyList<String>())
            }
            viewModel.category.observe(this) {
                categories = it
            }
            viewModel.getCategories()


            DataDisplay(viewModel, this)
            //SaveData(viewModel, this)
            /*
            NavHost(
                navController = navController,
                startDestination = "main"
            ) {
                composable("main") {

                }
                composable("open") {

                }
            }

            val c = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            PasswordGeneratorScreen(c)
            MyUI(categories)
        */
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyUI(category: List<String>) {
    var expanded by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf(" ") }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(), // menuAnchor modifier must be passed to the text field for correctness.
            readOnly = true,
            value = selectedCategory,
            onValueChange = {},
            label = { Text("Categories") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) }
        )

        // menu
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
        ) {
            // menu items
            category.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption) },
                    onClick = {
                        selectedCategory = selectionOption
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
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