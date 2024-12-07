package uk.co.bbc.elections.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Result(result: ResultUiState, backgroundColor: Color) = Row(
    modifier = Modifier.background(MaterialTheme.colors.surface)
) {
    Text(
        modifier = Modifier
            .weight(1f)
            .border(1.dp, Color.Black)
            .background(backgroundColor),
        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
        text = result.party,
        color = MaterialTheme.colors.onSurface
    )
    Text(
        modifier = Modifier
            .weight(1f)
            .border(1.dp, Color.Black)
            .background(backgroundColor),
        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
        text = result.candidateName,
        color = MaterialTheme.colors.onSurface
    )
    Text(
        modifier = Modifier
            .weight(1f)
            .border(1.dp, Color.Black)
            .background(backgroundColor),
        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
        text = result.votes,
        color = MaterialTheme.colors.onSurface
    )
}

//@Preview
//@Composable
//private fun ResultPreview() = Result(ResultUiState("Adder party", "1", "1056"))
