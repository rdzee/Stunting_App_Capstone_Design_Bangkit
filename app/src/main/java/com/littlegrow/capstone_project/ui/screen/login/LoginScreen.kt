package com.littlegrow.capstone_project.ui.screen.login

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.littlegrow.capstone_project.R
import com.littlegrow.capstone_project.ui.components.item.WelcomeItem
import com.littlegrow.capstone_project.ui.components.shape.RectangleShape
import com.littlegrow.capstone_project.ui.theme.Capstone_ProjectTheme
import com.littlegrow.capstone_project.util.AuthResultContract

@Composable
fun LoginScreen(
    context: Context = LocalContext.current,
    navigateToHome: () -> Unit
) {
    val auth = Firebase.auth
    val signInRequestCode = 1
    var loading by remember { mutableStateOf(false) }

    val resultLauncher =
        rememberLauncherForActivityResult(contract = AuthResultContract()) { task ->
            try {
                val account = task?.getResult(ApiException::class.java)
                if (account != null) {
                    val credential: AuthCredential = GoogleAuthProvider.getCredential(account.idToken, null)
                    auth.signInWithCredential(credential)
                        .addOnCompleteListener(context as Activity) { authResult ->
                            if (authResult.isSuccessful) {
                                navigateToHome()
                            } else {
                                Log.w("LoginScreen", authResult.exception)
                                Toast.makeText(context, context.getString(R.string.login_warning), Toast.LENGTH_SHORT).show()
                            }
                            loading = false
                        }
                }
            } catch (e: ApiException) {
                Log.w("LoginScreen", "Google Sign In Fail:", e)
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
        }

    LoginContent(
        loading = loading,
        onClick = {
            resultLauncher.launch(signInRequestCode)
            loading = true
        }
    )
}

@Composable
fun LoginContent(
    loading: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column {
            WelcomeItem()

            Box(modifier = modifier.padding(top = 20.dp)) {
                RectangleShape(
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                    color = MaterialTheme.colorScheme.surface,
                    modifier = modifier
                        .align(Alignment.Center)
                        .height(220.dp)
                        .fillMaxWidth()
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier.align(Alignment.Center)
                ) {
                    Text(
                        text = stringResource(id = R.string.please_login),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = modifier.padding(bottom = 16.dp))
                    Button(
                        onClick = onClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = MaterialTheme.colorScheme.surface)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.google_logo),
                            contentDescription = null
                        )
                        Text(
                            text = stringResource(id = R.string.login_google),
                            modifier = Modifier
                                .padding(start = 8.dp)
                        )
                    }
                }
            }
        }

        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }
}

@Composable
@Preview(
    showBackground = true
)
fun LoginScreenPreview() {
    Capstone_ProjectTheme {
        LoginContent(
            loading = true,
            onClick = {}
        )
    }
}