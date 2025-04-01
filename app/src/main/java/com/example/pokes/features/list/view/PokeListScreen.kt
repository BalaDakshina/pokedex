package com.example.pokes.features.list.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.lib_domain.model.Poke
import com.example.pokes.composables.ErrorScreen
import com.example.pokes.composables.LoadingScreen
import com.example.pokes.features.list.viewmodel.PokeListUiState
import com.example.pokes.features.list.viewmodel.PokeListUiState.Success
import com.example.pokes.features.list.viewmodel.PokeListViewModel
import com.example.pokes.navigation.Screens.PokeDetails
import com.example.pokes.ui.Dimensions
import com.example.pokes.ui.PokesTheme

@Composable
fun PokeListScreen(
    viewModel: PokeListViewModel = hiltViewModel(),
    navController: NavController
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) { viewModel.getPokesList() }
    PokeListContent(state = state, navController = navController)
}

@Composable
private fun PokeListContent(
    state: PokeListUiState,
    navController: NavController
) {
    when (state) {
        is PokeListUiState.Loading -> LoadingScreen()
        is Success -> PokeList(state.data, navController)
        is PokeListUiState.Error -> ErrorScreen()
    }
}

@Composable
private fun PokeList(
    pokes: List<Poke>,
    navController: NavController,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(Dimensions.mediumPadding)
    ) {
        items(pokes) {
            Row(
                modifier = Modifier
                    .padding(Dimensions.smallPadding)
                    .fillMaxSize()
                    .clickable {
                        navController.navigate(PokeDetails(it.name))
                    }) {
                Text(
                    text = it.name,
                    modifier = Modifier
                        .padding(start = Dimensions.smallPadding)
                        .weight(1f)
                )
            }
            HorizontalDivider()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPokesListScreen(
    state: Success = Success(
        listOf(
            Poke(name = "Poke 1"),
            Poke(name = "Poke 2")
        )
    )
) {
    PokesTheme {
        PokeListContent(
            state = state,
            navController = NavController(LocalContext.current)
        )
    }
}
