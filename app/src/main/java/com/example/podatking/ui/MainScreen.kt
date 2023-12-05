package com.example.podatking.ui.theme.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class WiekOption(val desc: String) {
    Do26("do 26 lat"),
    Ponad26("ponad 26 lat")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onImieChange: (String) -> Unit,
    imie: String,
    onKobietaChange: (Boolean) -> Unit,
    czyKobieta: Boolean,
    dochod: String,
    onDochodChange: (String) -> Unit,
    kwota: String,
    onKwotaChange: (String) -> Unit,
    ponad26: Boolean,
    onWiekChange: (String) -> Unit,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val radioOptions = enumValues<WiekOption>().map { it.desc }.toList()

    var selectedValue by rememberSaveable { mutableStateOf(
        if(ponad26) WiekOption.Ponad26.desc else WiekOption.Do26.desc
    ) }

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    keyboardOptions = KeyboardOptions.Default.copy(
                        capitalization = KeyboardCapitalization.Words,
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Text,
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Person,
                            contentDescription = null
                        )
                    },
                    value = imie,
                    onValueChange = onImieChange,
                    label = {
                        Text(
                            text = "Imię"
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Checkbox(
                        checked = czyKobieta,
                        onCheckedChange = onKobietaChange,
                    )
                    Text(
                        text = "Kobieta"
                    )
                }
            }
            OutlinedTextField(
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Next,
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.AccountBalanceWallet,
                        contentDescription = null
                    )
                },
                value = dochod,
                onValueChange = onDochodChange,
                label = {
                    Text(
                        text = "Dochód"
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(0.7f)
            )
            OutlinedTextField(
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Done,
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.AttachMoney,
                        contentDescription = null
                    )
                },
                value = kwota,
                onValueChange = onKwotaChange,
                label = {
                    Text(
                        text = "Kwota wolna"
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(0.7f)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                radioOptions.forEach { option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .selectable(
                                selected = selectedValue == option,
                                onClick = {
                                    selectedValue = option
                                    onWiekChange(option)
                                }
                            )
                    ) {
                        RadioButton(
                            selected = selectedValue == option,
                            onClick = {
                                selectedValue = option
                                onWiekChange(option)
                            }
                        )
                        Text(option)
                    }
                }
            }
        }
        Button(
            onClick = onButtonClick,
            modifier = modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Oblicz podatek"
            )
        }
    }
}