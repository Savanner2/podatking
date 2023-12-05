package com.example.podatking.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.podatking.data.PodatkingState
import java.text.NumberFormat

@Composable
fun ResultScreen(
    uiState: PodatkingState,
    onBackButtonClicked: () -> Unit,
    onResetButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .weight(1f)
        ) {
            Text(
                text = (if(uiState.czyKobieta) "Pani " else "Pan ") + uiState.imie,
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                modifier = modifier
                    .fillMaxWidth()
            )
            Text(
                text = "Podatek wynosi " + NumberFormat.getCurrencyInstance().format(uiState.podatek.toDoubleOrNull() ?: 0),
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = modifier
                    .fillMaxWidth()
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .weight(1f, false)
                .padding(16.dp)
        ) {
            Button(
                onClick = onResetButtonClicked,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Wróć i wyczyść")
            }
            OutlinedButton(
                onClick = onBackButtonClicked,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Wróć")
            }
        }

    }
}