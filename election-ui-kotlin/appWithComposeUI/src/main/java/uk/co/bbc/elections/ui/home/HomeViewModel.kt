package uk.co.bbc.elections.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import uk.co.bbc.elections.api.ResultsService
import uk.co.bbc.elections.data.ServiceContainer

class HomeViewModel(
    private val resultsService: ResultsService
) : ViewModel() {

    private val viewModelState = MutableStateFlow(HomeViewModelState(loading = true))


    val uiState = viewModelState.map { it.toUiState() }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    init {

        refresh()
    }



    fun refresh() = viewModelScope.launch {
        val candidates = async {
            resultsService.candidateResults()
        }.await()
        val latestResults = async { resultsService.latestResults() }.await()

        viewModelState.update { state ->
            state.copy(
                loading = false,
                results = latestResults.results,
                countingComplete = latestResults.isComplete,
                candidates = candidates
            )
        }
    }


    class Factory(private val serviceContainer: ServiceContainer) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(serviceContainer.electionResultsService) as T
        }
    }
}
