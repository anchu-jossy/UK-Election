package uk.co.bbc.elections.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.w3c.dom.Text
import uk.co.bbc.elections.R

@Composable
fun Home(viewModel: HomeViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    Home(uiState) { viewModel.refresh() }
}

@Composable
fun Home(uiState: HomeUiState, refresh: () -> Unit) {
    val snackbarHostState = SnackbarHostState()

    val scope = rememberCoroutineScope()
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
            if (!uiState.isCountingComplete) {
                FloatingActionButton(onClick = { if (!uiState.loading) refresh() }) {
                    Icon(
                        imageVector = Icons.Filled.Refresh,
                        contentDescription = stringResource(id = R.string.refresh)
                    )
                }
            } else {

                scope.launch {
                    snackbarHostState.showSnackbar("Hey Counting is complete")
                }
            }
        }


    ) { innerPadding ->
        LazyColumn(
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            item { ResultHeader() }
            val maximum = uiState.results.maxByOrNull { it.votes.toInt() }

            items(uiState.results) {
                Result(it, backgroundColor = if (it == maximum) Color.Yellow else Color.Transparent)
            }


        }
    }

}

@Preview
@Composable
private fun HomePreview() = Home(
    HomeUiState(
        results = listOf(
            ResultUiState("Adder party", "1", "1056","1"),
            ResultUiState("b", "2", "100","1")
        ),
        loading = false, false
    )
) {}
