package uk.co.bbc.elections.ui.home

import uk.co.bbc.elections.api.Candidate

data class HomeUiState(
    val results: List<ResultUiState>,
    val loading: Boolean,val isCountingComplete:Boolean
)
