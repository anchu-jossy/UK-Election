package uk.co.bbc.elections.api

class ResultsServiceImpl(
    private val resultsRepository: ResultsRepository
) : ResultsService {
    override suspend fun latestResults(): Results {
        return resultsRepository.latestResults()
    }

    override suspend fun candidateResults(): List<Candidate> {
        return resultsRepository.allCandidates()
    }


}