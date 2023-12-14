package com.D121211037.filmapp.ui.screens.details

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.D121211037.filmapp.R
import com.D121211037.filmapp.models.Film
import com.D121211037.filmapp.ui.theme.FilmAppTheme

class DetailActivity : ComponentActivity() {

    private var selectedFilm: Film? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectedFilm = intent.getParcelableExtra("film")
        setContent {
            FilmAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DetailScreen()
                }
            }
        }
    }

    @Composable
    fun DetailScreen() {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Box (modifier = Modifier.fillMaxSize()){
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data("https://image.tmdb.org/t/p/original" + selectedFilm?.backdrop_path)
                        .crossfade(true)
                        .build(),
                    contentDescription = selectedFilm?.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(350.dp)
                        .offset(y = 180.dp, x = 16.dp)
                        .background(color = MaterialTheme.colorScheme.errorContainer),
                ){
                    Column (
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(16.dp)
                    ){
                        Text(
                            text = selectedFilm?.title.toString(),
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = stringResource(id = R.string.rating, selectedFilm?.vote_average.toString()),
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = stringResource(id = R.string.overvier),
                            style = MaterialTheme.typography.titleSmall
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = selectedFilm?.overview.toString(),
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Justify
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = stringResource(id = R.string.release_date),
                            style = MaterialTheme.typography.titleSmall,
                        )
                        Text(
                            text = selectedFilm?.release_date.toString(),
                            style = MaterialTheme.typography.bodyLarge
                            )
                    }
                }
            }
        }
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .verticalScroll(rememberScrollState())
//        ) {
//            Text(
//                text = selectedFilm?.title.toString(),
//                style = MaterialTheme.typography.titleMedium,
//                textAlign = TextAlign.Center,
//                modifier = Modifier
//                    .fillMaxWidth()
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            AsyncImage(
//                model = ImageRequest.Builder(context = LocalContext.current)
//                    .data("https://image.tmdb.org/t/p/original" + selectedFilm?.poster_path)
//                    .crossfade(true)
//                    .build(),
//                contentDescription = selectedFilm?.title,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(400.dp)
//                    .clip(MaterialTheme.shapes.small),
//                contentScale = ContentScale.Crop
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//            Text(
//                text = stringResource(id = R.string.rating, selectedFilm?.vote_average.toString()),
//                style = MaterialTheme.typography.titleSmall
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(
//                text = selectedFilm?.overview.toString(),
//                style = MaterialTheme.typography.bodyLarge,
//                textAlign = TextAlign.Justify
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(
//                text = stringResource(id = R.string.release_date, selectedFilm?.release_date.toString()),
//                style = MaterialTheme.typography.labelSmall,
//            )
//        }
    }
}