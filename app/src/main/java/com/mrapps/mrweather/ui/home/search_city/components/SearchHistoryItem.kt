package com.mrapps.mrweather.ui.home.search_city.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.mrapps.mrweather.domain.model.location.City
import com.mrapps.mrweather.ui.theme.ThemeWithSurface
import com.mrapps.mrweather.ui.util.preview.PreviewObjects
import com.mrapps.mrweather.ui.util.preview.ThemePreview

@Composable
fun SearchHistoryItem(
    city: City,
    onClick: (city: City) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick(city)
            }
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .padding(top = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                modifier = Modifier.size(30.dp),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = city.localizedName,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "${city.administrativeArea.localizedName}, ${city.country.localizedName}, ${city.region.localizedName}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        Spacer(
            modifier = Modifier
                .padding(top = 12.dp)
                .height(0.7.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f))
        )
    }
}

@ThemePreview
@Composable
private fun SearchHistoryItemPreview(modifier: Modifier = Modifier) {
    ThemeWithSurface {
        SearchHistoryItem(PreviewObjects.Cities.city, {})
    }
}