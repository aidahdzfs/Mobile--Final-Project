package com.D121211037.filmapp.ui.screens.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.D121211037.filmapp.FilmsApplication
import com.D121211037.filmapp.data.FilmRepository
import com.D121211037.filmapp.models.Film
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface FilmUiState {
    data class Success(val films: List<Film>) : FilmUiState
    object Error : FilmUiState
    object Loading : FilmUiState
}

class FilmViewModel(private val filmRepository: FilmRepository): ViewModel() {

    // initial state
    var filmUiState: FilmUiState by mutableStateOf(FilmUiState.Loading)
        private set

    fun getFilms() = viewModelScope.launch {
        filmUiState = FilmUiState.Loading
        try {
            val result = filmRepository.getFilms()
            filmUiState = FilmUiState.Success(result.results.orEmpty())
        } catch (e: IOException) {
            filmUiState = FilmUiState.Error
        }
    }

    init {
        getFilms()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FilmsApplication)
                val newsRepository = application.container.filmsRepository
                FilmViewModel(newsRepository)
            }
        }
    }
}