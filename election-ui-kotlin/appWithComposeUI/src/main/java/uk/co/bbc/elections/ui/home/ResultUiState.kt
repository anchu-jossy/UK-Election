package uk.co.bbc.elections.ui.home

import uk.co.bbc.elections.api.Candidate

data class ResultUiState(
    val party: String,
    val id: String,
    val votes: String,
    val candidateName:String

)
