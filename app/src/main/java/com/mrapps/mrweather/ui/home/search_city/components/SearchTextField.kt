package com.mrapps.mrweather.ui.home.search_city.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.mrapps.mrweather.R
import com.mrapps.mrweather.ui.theme.ThemeWithSurface
import com.mrapps.mrweather.ui.util.lettersWithSpacesRegex
import com.mrapps.mrweather.ui.util.preview.ThemePreview

@Composable
fun SearchTextField(
    query: String,
    onQueryChanged: (newQuery: String) -> Unit,
    onSearchClicked: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        modifier = modifier.fillMaxWidth(),
        enabled = enabled,
        value = query,
        onValueChange = { newQuery ->
            if (newQuery.length <= 100 && newQuery.matches(lettersWithSpacesRegex)) {
                onQueryChanged(newQuery)
            }
        },
        label = { Text(stringResource(R.string.search_city_label)) },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(
                    enabled = enabled,
                    onClick = { onQueryChanged("") }
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = stringResource(R.string.clear_text_ic),
                        tint = Color.Gray
                    )
                }
            } else {
                IconButton(
                    enabled = enabled,
                    onClick = onSearchClicked
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(R.string.search_ic),
                        tint = Color.Gray
                    )
                }
            }
        },
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search,
            keyboardType = KeyboardType.Text
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchClicked()
                keyboardController?.hide()
            }
        )
    )
}

@ThemePreview
@Composable
private fun SearchTextFieldPreview() {
    ThemeWithSurface {
        SearchTextField(
            "",
            onQueryChanged = {},
            onSearchClicked = {}
        )
    }
}

@ThemePreview
@Composable
private fun FilledSearchTextFieldPreview() {
    ThemeWithSurface {
        SearchTextField(
            "Warszawa",
            onQueryChanged = {},
            onSearchClicked = {}
        )
    }
}