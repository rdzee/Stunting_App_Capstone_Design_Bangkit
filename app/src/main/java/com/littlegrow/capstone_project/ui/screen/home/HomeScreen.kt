package com.littlegrow.capstone_project.ui.screen.home

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Button
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
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.littlegrow.capstone_project.R
import com.littlegrow.capstone_project.data.local.database.Information
import com.littlegrow.capstone_project.di.Injection
import com.littlegrow.capstone_project.model.FeatureData
import com.littlegrow.capstone_project.ui.components.row.FeatureRow
import com.littlegrow.capstone_project.ui.components.row.InformationRow
import com.littlegrow.capstone_project.ui.components.row.ProfileRow
import com.littlegrow.capstone_project.ui.components.shape.RectangleShape
import com.littlegrow.capstone_project.ui.screen.Result
import com.littlegrow.capstone_project.ui.screen.ViewModelFactory
import com.littlegrow.capstone_project.ui.theme.Capstone_ProjectTheme
import com.littlegrow.capstone_project.util.getGoogleSignInClient

@Composable
fun HomeScreen(
    navigateToDetail: () -> Unit,
    navigateToAdd: () -> Unit,
    navigateToLogin: () -> Unit,
    navigateToChooseProfile: (String) -> Unit,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepo(context))
    )
) {
    val auth = Firebase.auth
    var expanded by remember { mutableStateOf(false) }
    var listInformation: List<Information> = remember { mutableStateListOf() }

    val listFeature: List<FeatureData> = listOf(
        FeatureData(
            id = "F1",
            featurePicture =  R.drawable.home_icon_1,
            featureName = stringResource(id = R.string.feature_recommendation)
        ),
        FeatureData(
            id = "F2",
            featurePicture =  R.drawable.home_icon_2,
            featureName = "?"
        )
    )

    viewModel.informationList.collectAsState().value.let {result ->
        when(result) {
            is Result.Loading -> viewModel.getAllInfo()
            is Result.Success -> {
                listInformation = result.data
            }
            is Result.Error -> {
                Log.d("PopularMovies: ", result.error)
                Toast.makeText(context, "HealthInformation: ${ result.error }", Toast.LENGTH_SHORT).show()
            }
        }
    }

    HomeContent(
        image = auth.currentUser?.photoUrl,
        email = auth.currentUser?.email,
        listFeature = listFeature,
        listInformation = listInformation,
        expanded = expanded,
        setExpanded = { expanded = it },
        navigateToChooseProfile = navigateToChooseProfile,
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
    listFeature: List<FeatureData>,
    listInformation: List<Information>?,
    setExpanded: (Boolean) -> Unit,
    navigateToChooseProfile: (String) -> Unit,
    navigateToDetail: () -> Unit,
    navigateToAdd: () -> Unit,
    navigateToLogin: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(MaterialTheme.colorScheme.primary),
                title = {},
                actions = {
                    IconButton(
                        onClick = {
                            setExpanded(!expanded)
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.AccountCircle,
                            contentDescription = stringResource(id = R.string.profile),
                            tint = Color.White,
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
            val (boxRef, containerRes, buttonRef, featureRef, featureRowRef, informationRef, informationRowRef) = createRefs()
            Box(
                modifier = modifier
                    .constrainAs(boxRef) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                    }
            ) {
                RectangleShape(
                    shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp),
                    color = MaterialTheme.colorScheme.primary,
                    modifier = modifier
                        .align(Alignment.Center)
                        .height(130.dp)
                        .fillMaxWidth()
                )
            }
            ProfileRow(
                // TODO : Input Real Data Model
                profileList = listOf(
                    "1",
                    "2"
                ),
                navigateToDetail = navigateToDetail,
                navigateToAdd = navigateToAdd,
                modifier = modifier
                    .padding(top = 16.dp)
                    .constrainAs(containerRes) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        width = Dimension.fillToConstraints
                    }
            )

            Button(
                onClick = { navigateToAdd() },
                modifier = modifier
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                    .fillMaxWidth()
                    .constrainAs(buttonRef) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(containerRes.bottom)
                    }
            ) {
                Text(
                    text = stringResource(id = R.string.add_profile),
                    style = MaterialTheme.typography.titleMedium
                    )
            }

            Text(
                text = stringResource(id = R.string.features),
                fontSize =  20.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .constrainAs(featureRef) {
                        top.linkTo(buttonRef.bottom, 24.dp)
                        start.linkTo(parent.start, 16.dp)
                    }
            )

            FeatureRow(
                featureList = listFeature,
                navigateToChooseProfile = navigateToChooseProfile,
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
                fontSize =  20.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .constrainAs(informationRef) {
                        top.linkTo(featureRowRef.bottom, 32.dp)
                        start.linkTo(parent.start, 16.dp)
                    }
            )

            if (listInformation != null) {
                InformationRow(
                    articleList = listInformation,
                    modifier = Modifier
                        .constrainAs(informationRowRef) {
                            start.linkTo(parent.start)
                            top.linkTo(informationRef.bottom)
                        }
                )
            }
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
            listFeature = listOf(
                FeatureData(
                    id = "",
                    featurePicture =  R.drawable.home_icon_1,
                    featureName = stringResource(id = R.string.feature_recommendation)
                ),
                FeatureData(
                    id = "",
                    featurePicture =  R.drawable.home_icon_2,
                    featureName = stringResource(id = R.string.feature_budget)
                ),
            ),
            listInformation = listOf(
                Information(
                    id = 1,
                    articleImage = "",
                    articleTitle = "10 Cara Mengatasi Stunting pada Anak, Orang Tua Wajib Tahu!",
                    articleLink = "",
                    articleSource = "genbest"
                )
            ),
            navigateToDetail = {},
            navigateToAdd = {},
            navigateToLogin = {},
            navigateToChooseProfile = {},
            setExpanded = {},
            expanded = false,
        )
    }
}