package com.example.myapplication.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExtendedFloatingActionButton
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.MainActivity
import com.example.myapplication.R

@Composable
fun CustomPassword(
    context: MainActivity,
    clipboard: ClipboardManager
) {
    var letters by remember { mutableStateOf(false) }
    var uppercase by remember { mutableStateOf(false) }
    var numbers by remember { mutableStateOf(false) }
    var special by remember { mutableStateOf(false) }

    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color(0xFFe8b7dd), Color(0xFFb7e8d0)))),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = { Text("Generated Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            readOnly = true,
            trailingIcon = {
                IconButton(
                    onClick = {
                        clipboard.setPrimaryClip(ClipData.newPlainText("password", password))
                        Toast.makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT)
                            .show()
                    },
                    modifier = Modifier
                        .size(48.dp)
                        .padding(8.dp)
                ) {
                    Icon(
                        bitmap = ImageBitmap.imageResource(R.drawable.copy),
                        contentDescription = null
                    )
                }
            }
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = letters,
                onCheckedChange = { letters = it },
                modifier = Modifier.padding(5.dp)
            )
            Text("with letters", fontSize = 22.sp)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = uppercase,
                onCheckedChange = { uppercase = it },
                modifier = Modifier.padding(5.dp)
            )
            Text("with uppercase", fontSize = 22.sp)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = numbers,
                onCheckedChange = { numbers = it },
                modifier = Modifier.padding(5.dp)
            )
            Text("with numbers", fontSize = 22.sp)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = special,
                onCheckedChange = { special = it },
                modifier = Modifier.padding(5.dp)
            )
            Text("with special", fontSize = 22.sp)
        }

        ExtendedFloatingActionButton(
            icon = { Icon(Icons.Outlined.Settings, contentDescription = "Regenerate") },
            text = { Text("Regenerate") },
            onClick = {
                password = generatePassword(
                    letters, uppercase, numbers, special
                )
            }
        )
    }
}


fun generatePassword(
    isWithLetters: Boolean,
    isWithUppercase: Boolean,
    isWithNumbers: Boolean,
    isWithSpecial: Boolean,
): String {
    val letters = "abcdefghijklmnopqrstuvwxyz"
    val uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    val numbers = "0123456789"
    val special = "@#=+!£$%&?-"
    val charSets = mutableListOf<String>()

    if (isWithLetters) {
        charSets.add(letters)
    }
    if (isWithUppercase) {
        charSets.add(uppercaseLetters)
    }
    if (isWithNumbers) {
        charSets.add(numbers)
    }
    if (isWithSpecial) {
        charSets.add(special)
    }
    if (charSets.size < 1) {
        return ""
    }

    var result = charSets.joinToString("")
    if (isWithLetters && isWithUppercase && isWithNumbers && isWithSpecial) {
        result += "${letters.random()}${uppercaseLetters.random()}${numbers.random()}${special.random()}"
    }

    result = result.toList().shuffled().joinToString("")

    return result.substring(0, 8.coerceAtMost(result.length))
}