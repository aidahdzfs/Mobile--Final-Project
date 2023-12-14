package com.D121211037.filmapp.ui.screens.list

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.D121211037.filmapp.R
import com.D121211037.filmapp.models.Dates
import com.D121211037.filmapp.models.Film
import com.D121211037.filmapp.ui.screens.details.DetailActivity
import com.D121211037.filmapp.ui.theme.FilmAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FilmAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FilmApp()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun FilmApp(){
        Scaffold (
            modifier = Modifier.fillMaxSize(),
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.app_name),
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.background
                        )
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                    )
                )
            }
        ){
            Surface (
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ){
                val filmViewModel: FilmViewModel = viewModel(factory = FilmViewModel.Factory)
                ListScreen(
                    filmViewModel.filmUiState,
                    contentPadding = it
                )
            }
        }
    }

    @Composable
    private fun ListScreen(filmUiState: FilmUiState, modifier: Modifier = Modifier, contentPadding : PaddingValues = PaddingValues(0.dp)) {
        when (filmUiState) {
            is FilmUiState.Loading -> LoadingScreen()
            is FilmUiState.Error -> ErrorScreen()
            is FilmUiState.Success -> FilmList(filmUiState.films)
        }
    }
    @Composable
    fun LoadingScreen(modifier: Modifier = Modifier) {
        Image(
            painter = painterResource(R.drawable.loading),
            contentDescription = null,
            modifier = modifier
        )
    }
    @Composable
    fun ErrorScreen(modifier: Modifier = Modifier) {
        Text(
            text = stringResource(id = R.string.error),
            modifier = modifier.fillMaxSize()
        )
    }

    @Composable
    fun FilmList(films: List<Film>) {
        Column (modifier = Modifier){
            Spacer(modifier = Modifier.height(100.dp))
            Text(
                text = stringResource(id = R.string.now_playing),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyVerticalGrid(columns = GridCells.Fixed(2)){
                items(films) { film ->
                    FilmCard(film = film)
                }
            }
        }

    }

    @Composable
    fun FilmCard(film: Film) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .clickable {
                    val intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra("film", film)
                    startActivity(intent)
                },
            shape = MaterialTheme.shapes.small
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data("https://image.tmdb.org/t/p/original" + film.poster_path)
                        .crossfade(true)
                        .build(),
                    contentDescription = film.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.FillWidth,
                    error = painterResource(id = R.drawable.broken),
                    placeholder = painterResource(id = R.drawable.loading)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = film.title.toString(),
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = film.release_date.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                )

                // Other details like rating, genre, etc. can be added here
            }
        }
    }
}



