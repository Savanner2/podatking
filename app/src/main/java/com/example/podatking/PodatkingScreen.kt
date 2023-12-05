package com.example.podatking

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.podatking.ui.theme.ui.PodatkingViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.podatking.ui.theme.ui.MainScreen
import com.example.podatking.ui.ResultScreen

enum class PodatkingScreen(val title: String) {
    Start("Kalkulator podatku"),
    Result("Wynik")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PodatkingApp(
    viewModel: PodatkingViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = PodatkingScreen.valueOf(
        backStackEntry?.destination?.route ?: PodatkingScreen.Start.name
    )
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            PodatkingAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = PodatkingScreen.Start.name,
            modifier = Modifier
                .padding(innerPadding)
        ) {
            composable(route = PodatkingScreen.Start.name) {
                MainScreen(
                    onImieChange = { viewModel.setName(it) },
                    imie = uiState.imie,
                    onKobietaChange = { viewModel.setCzyKobieta() },
                    czyKobieta = uiState.czyKobieta,
                    dochod = uiState.dochod,
                    onDochodChange = { viewModel.setDochod(it) },
                    kwota = uiState.kwota,
                    onKwotaChange = { viewModel.setKwota(it) },
                    ponad26 = uiState.ponad26,
                    onWiekChange = { viewModel.setWiek(it) },
                    onButtonClick = {
                        if(viewModel.validateForm()) {
                            viewModel.calculatePodatek()
                            navController.navigate(PodatkingScreen.Result.name)
                        } else {
                            Toast.makeText(context, "Nieprawidłowe dane!", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier.padding(8.dp)
                )
            }
            composable(route = PodatkingScreen.Result.name) {
                ResultScreen(
                    uiState = uiState,
                    onBackButtonClicked = { navController.navigateUp() },
                    onResetButtonClicked = {
                        viewModel.resetForm()
                        navController.popBackStack(PodatkingScreen.Start.name, inclusive = false)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PodatkingAppBar(
    currentScreen: PodatkingScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(currentScreen.title) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Wróć"
                    )
                }
            }
        }
    )
}