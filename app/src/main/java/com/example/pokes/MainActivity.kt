package com.example.pokes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokes.features.details.view.PokeDetailsScreen
import com.example.pokes.features.list.view.PokeListScreen
import com.example.pokes.navigation.Screens.PokeDetails
import com.example.pokes.navigation.Screens.PokeList
import com.example.pokes.ui.PokesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PokesApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun PokesApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(modifier = modifier, navController = navController, startDestination = PokeList) {
        composable<PokeList> { PokeListScreen(navController = navController) }
        composable<PokeDetails> { PokeDetailsScreen() }
    }
}