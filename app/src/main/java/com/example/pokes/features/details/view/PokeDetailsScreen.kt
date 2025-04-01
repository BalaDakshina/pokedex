package com.example.pokes.features.details.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.lib_domain.model.PokeDetail
import com.example.lib_domain.model.Stat
import com.example.pokes.R
import com.example.pokes.composables.ErrorScreen
import com.example.pokes.composables.LoadingScreen
import com.example.pokes.features.details.viewModel.PokeDetailsUiState
import com.example.pokes.features.details.viewModel.PokeDetailsUiState.Success
import com.example.pokes.features.details.viewModel.PokeDetailsViewModel
import com.example.pokes.ui.PokesTheme

@Composable
fun PokeDetailsScreen(viewModel: PokeDetailsViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = Unit) { viewModel.getDetails() }
    PokeDetailContent(state = state)
}

@Composable
fun PokeDetailContent(state: PokeDetailsUiState) {
    when (state) {
        is Success -> PokeDetail(state.data)
        is PokeDetailsUiState.Error -> ErrorScreen()
        is PokeDetailsUiState.Loading -> LoadingScreen()
    }
}

@Composable
private fun PokeDetail(pokeDetail: PokeDetail) {
    var isImageLoading by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.poke_details),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp)
        )

        Box(
            modifier = Modifier
                .size(264.dp)
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp)
                .clip(CircleShape)
                .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = pokeDetail.imageUrl,
                    onState = { state ->
                        isImageLoading = state is AsyncImagePainter.State.Loading
                    }
                ),
                contentDescription = pokeDetail.name,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            )

            if (isImageLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        Text(
            text = pokeDetail.name,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = stringResource(R.string.physic),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp, horizontal = 8.dp)
        )
        {
            Text(
                text = stringResource(R.string.height),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .weight(1f)

            )
            Text(
                text = pokeDetail.height,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp, horizontal = 8.dp)
        ) {
            Text(
                text = stringResource(R.string.weight),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .weight(1f)
            )

            Text(
                text = pokeDetail.weight,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        Text(
            text = stringResource(R.string.stats),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn {
            items(pokeDetail.baseStats) { stat ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                ) {
                    Text(
                        text = stat.name,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = stat.amount,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}


@Composable
@Preview
fun PreviewPokeDetailsScreen(
    state: PokeDetailsUiState = Success(
        PokeDetail(
            name = "Poke 1",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/1.png",
            height = "11",
            weight = "76",
            baseStats = listOf(
                Stat(
                    name = "hp",
                    amount = "3"
                )
            )
        )
    )
) {
    PokesTheme {
        PokeDetailContent(state = state)
    }
}
