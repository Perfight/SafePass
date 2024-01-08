package com.example.myapplication

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.data.Account
import com.example.myapplication.data.MainVM
import com.example.myapplication.encrypt.SecurityEncrypt

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DataOverview(
    viewModel: MainVM,
    navController : NavController,
    context: MainActivity,
    clipboard: ClipboardManager,
    argumentValue: Int
) {
    viewModel.getAccountData(argumentValue)

    var account by remember {
        mutableStateOf(
            Account(
                "",
                "",
                "",
                "",
                1,
                0
            )
        )
    }

    var newPass by remember {
        mutableStateOf(
            "0"
        )
    }
    viewModel.accountInformation.observe(context) {
        account = it
        newPass = SecurityEncrypt(context).getDataPass(
            account.password,
            "0"
        )!!
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            //.padding(top = it.calculateTopPadding())
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = account.site,
            onValueChange = { account.site = it },
            readOnly = true,
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

        OutlinedTextField(
            value = account.category,
            onValueChange = { account.category = it },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Build,
                    contentDescription = null,
                    tint = Color.Gray
                )
            }
        )

        OutlinedTextField(
            value = account.username,
            onValueChange = { account.username = it },
            readOnly = true,
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
            value = newPass!!,
            onValueChange = {
                newPass = it
            },
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
                .padding(vertical = 8.dp),
            trailingIcon = {
                IconButton(
                    onClick = {
                        clipboard.setPrimaryClip(ClipData.newPlainText("password", newPass))
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


        ExtendedFloatingActionButton(
            icon = { Icon(Icons.Filled.Add, contentDescription = "Save") },
            text = { Text("Save") },
            onClick = {
                account.password = SecurityEncrypt(context).putDataPass(newPass!!)
                viewModel.updateAccount(account)
                navController.popBackStack()
                navController.navigate("openList")
            }
        )
    }
}