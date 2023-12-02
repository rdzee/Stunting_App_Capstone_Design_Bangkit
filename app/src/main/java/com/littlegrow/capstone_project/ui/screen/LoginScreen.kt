package com.littlegrow.capstone_project.ui.screen

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.littlegrow.capstone_project.R
import com.littlegrow.capstone_project.ui.theme.Capstone_ProjectTheme
import com.littlegrow.capstone_project.util.AuthResultContract

@Composable
fun LoginScreen(
    context: Context = LocalContext.current,
    navigateToHome: () -> Unit
) {
    val auth = Firebase.auth
    val signInRequestCode = 1

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
                        }
                }
            } catch (e: ApiException) {
                Log.w("LoginScreen", "Google Sign In Fail:", e)
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
        }

    LoginContent(
        onClick = {
            resultLauncher.launch(signInRequestCode)
        }
    )
}

@Composable
fun LoginContent(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .align(Alignment.Center)
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

@Composable
@Preview(
    showBackground = true
)
fun LoginScreenPreview() {
    Capstone_ProjectTheme {
        LoginContent(
            onClick = {}
        )
    }
}