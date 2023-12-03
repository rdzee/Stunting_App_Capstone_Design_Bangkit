package com.littlegrow.capstone_project.ui.screen

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.net.toUri
import coil.compose.AsyncImage
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.littlegrow.capstone_project.R
import com.littlegrow.capstone_project.ui.components.row.FeatureRow
import com.littlegrow.capstone_project.ui.components.row.InformationRow
import com.littlegrow.capstone_project.ui.components.row.ProfileRow
import com.littlegrow.capstone_project.ui.theme.Capstone_ProjectTheme
import com.littlegrow.capstone_project.util.getGoogleSignInClient

@Composable
fun HomeScreen(
    navigateToDetail: () -> Unit,
    navigateToAdd: () -> Unit,
    navigateToLogin: () -> Unit,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
) {
    val auth = Firebase.auth
    var expanded by remember { mutableStateOf(false) }

    HomeContent(
        image = auth.currentUser?.photoUrl,
        email = auth.currentUser?.email,
        expanded = expanded,
        setExpanded = { expanded = !expanded },
        navigateToDetail = navigateToDetail,
        navigateToAdd = navigateToAdd,
        navigateToLogin = {
            auth.signOut()
            getGoogleSignInClient(context).signOut()
            navigateToLogin()
        },
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    image: Uri?,
    email: String?,
    expanded: Boolean,
    setExpanded: (Boolean) -> Unit,
    navigateToDetail: () -> Unit,
    navigateToAdd: () -> Unit,
    navigateToLogin: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                actions = {
                    IconButton(
                        onClick = {
                            setExpanded(expanded)
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.AccountCircle,
                            contentDescription = stringResource(id = R.string.profile),
                            modifier = Modifier
                                .size(32.dp)
                        )

                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = {
                                setExpanded(false)
                            }
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(bottom = 8.dp)
                            ) {
                                AsyncImage(
                                    model = image,
                                    contentDescription = stringResource(id = R.string.profile),
                                    modifier = Modifier
                                        .padding(start = 8.dp)
                                        .clip(CircleShape)
                                        .background(Color.LightGray)
                                        .size(32.dp)
                                )

                                if (email != null) {
                                    Text(
                                        text = email,
                                        modifier = Modifier
                                            .padding(horizontal = 8.dp)
                                    )
                                } else {
                                    Text(
                                        text = stringResource(id = R.string.email),
                                        modifier = Modifier
                                            .padding(horizontal = 8.dp)
                                    )
                                }
                            }

                            Divider()

                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = stringResource(id = R.string.logout),
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    )
                                },
                                onClick = navigateToLogin,
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                            )
                        }
                    }
                }
            )
        }
    ) {
        ConstraintLayout(
            modifier = modifier
                .padding(it)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            val (containerRes, featureRef, featureRowRef, informationRef, informationRowRef) = createRefs()
            ProfileRow(
                // TODO : Input Real Data Model
                profileList = listOf(
                    "1",
                    "2"
                ),
                navigateToDetail = navigateToDetail,
                navigateToAdd = navigateToAdd,
                modifier = Modifier.constrainAs(containerRes) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    width = Dimension.fillToConstraints
                }
            )

            Text(
                text = stringResource(id = R.string.features),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .constrainAs(featureRef) {
                        top.linkTo(containerRes.bottom, 24.dp)
                        start.linkTo(parent.start, 16.dp)
                    }
            )

            FeatureRow(
                featureList = listOf(
                    "Recommendation",
                    "?",
                    "?"
                ),
                modifier = Modifier
                    .constrainAs(featureRowRef) {
                        top.linkTo(featureRef.bottom, 16.dp)
                        start.linkTo(parent.start, 16.dp)
                        end.linkTo(parent.end, 16.dp)
                        width = Dimension.fillToConstraints
                    }
            )

            Text(
                text = stringResource(id = R.string.information),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .constrainAs(informationRef) {
                        top.linkTo(featureRowRef.bottom, 32.dp)
                        start.linkTo(parent.start, 16.dp)
                    }
            )

            InformationRow(
                articleList = listOf(
                    "Pencegahan Stunting Pada Anak",
                    "4 Cara Pencegahan Stunting"
                ),
                modifier = Modifier
                    .constrainAs(informationRowRef) {
                        start.linkTo(parent.start)
                        top.linkTo(informationRef.bottom)
                    }
            )
        }
    }
}

@Composable
@Preview(
    showBackground = true,
    device = Devices.DEFAULT,
    showSystemUi = true
)
fun HomeContentPreview() {
    Capstone_ProjectTheme {
        HomeContent(
            email = "",
            image = "".toUri(),
            navigateToDetail = {},
            navigateToAdd = {},
            navigateToLogin = {},
            setExpanded = {},
            expanded = false,
        )
    }
}