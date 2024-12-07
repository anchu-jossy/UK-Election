package uk.co.bbc.elections.ui.home

import uk.co.bbc.elections.api.Candidate
import uk.co.bbc.elections.api.Result

data class HomeViewModelState(
    val results: List<Result> = emptyList(),
    val countingComplete: Boolean = false,
    val loading: Boolean = false,
    val candidates: List<Candidate> = emptyList()
) {
    fun toUiState(): HomeUiState {


        return HomeUiState(

            results = results.map { result ->
                ResultUiState(
                    result.party,
                    result.candidateId.toString(),
                    result.votes.toString(),
                    candidateName = candidates.associateBy { it.id }.get(result.candidateId)?.name
                        ?: ""
                )
            },
            loading = loading, isCountingComplete = countingComplete
        )
    }


}
