package com.example.lastprojectandroidprogram.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lastprojectandroidprogram.CallBackInterface.LoginCallback
import com.example.lastprojectandroidprogram.R
import com.example.lastprojectandroidprogram.Response.ResponseLogin
import com.example.lastprojectandroidprogram.Service.login
import com.example.lastprojectandroidprogram.components.HeaderText
import com.example.lastprojectandroidprogram.components.LoginTextField
import com.example.lastprojectandroidprogram.graphs.Graph
import com.example.lastprojectandroidprogram.ui.*
import com.example.lastprojectandroidprogram.ui.theme.AppTheme

val defaultPadding = 16.dp
val itemSpacing = 8.dp

@Composable
fun LoginScreen(onLoginClick: () -> Unit, onSignUpClick: () -> Unit) {
    val (userName, setUsername) = rememberSaveable {
        mutableStateOf("")
    }
    val (password, setPassword) = rememberSaveable {
        mutableStateOf("")
    }
    val (checked, onCheckedChange) = rememberSaveable {
        mutableStateOf(false)
    }
    val isFieldsEmpty = userName.isNotEmpty() && password.isNotEmpty()

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(defaultPadding)
            .background(MaterialTheme.colorScheme.surface)
            ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderText(
            text = "Login",
            modifier = Modifier
                .padding(vertical = defaultPadding)
                .align(alignment = Alignment.Start)
        )
        LoginTextField(
            value = userName,
            onValueChange = setUsername,
            labelText = "Username",
            leadingIcon = Icons.Default.Person,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(itemSpacing))
        LoginTextField(
            value = password,
            onValueChange = setPassword,
            labelText = "Password",
            leadingIcon = Icons.Default.Lock,
            modifier = Modifier.fillMaxWidth(),
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(Modifier.height(itemSpacing))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = checked, onCheckedChange = onCheckedChange)
                Text("Remember me")
            }
            TextButton(onClick = {}) {
                Text("Forgot Password?")
            }
        }
        Spacer(Modifier.height(itemSpacing))
        Button(
            onClick ={
                login(userName, password, object: LoginCallback{
                    override fun onResult(response: ResponseLogin?) {
                        if(response != null){
                            Log.d("Login", response.access_token)
                            Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                            //Graph.TOKEN_ACCESS = response.access_token
                            Log.d("Login", "access token ${Graph.TOKEN_ACCESS}")
                            onLoginClick()
                        }else{
                            Log.d("Login", "Failed")
                            Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show()

                        }
                    }

                })

            } ,
            modifier = Modifier.fillMaxWidth(),
            enabled = isFieldsEmpty,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.inversePrimary,
                contentColor = MaterialTheme.colorScheme.inverseOnSurface
            )
        ) {
            Text("Login")
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Don't have an Account?")
            Spacer(Modifier.height(itemSpacing))
            TextButton(onClick = onSignUpClick) {
                Text("Sign Up")
            }
        }


    }
}





@Preview(showSystemUi = true)
@Composable
fun PrevLoginScreen() {
    AppTheme {
        LoginScreen({}, {})
    }
}