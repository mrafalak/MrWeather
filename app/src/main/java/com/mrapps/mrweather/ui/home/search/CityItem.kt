package com.mrapps.mrweather.ui.home.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.mrapps.mrweather.domain.model.AdministrativeArea
import com.mrapps.mrweather.domain.model.City
import com.mrapps.mrweather.domain.model.Country
import com.mrapps.mrweather.domain.model.Region
import com.mrapps.mrweather.ui.theme.MrWeatherTheme

@Composable
fun CityItem(
    city: City,
    onClick: (city: City) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = { onClick(city) },
        elevation = CardDefaults.cardElevation(4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
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
        }
    }
}

@PreviewLightDark
@Composable
fun CityItemPreview(modifier: Modifier = Modifier) {
    MrWeatherTheme {
        CityItem(city = cityPreview, onClick = {})
    }
}

val cityPreview = City(
    id = "Warszawa",
    localizedName = "Warszawa",
    englishName = "Warsaw",
    administrativeArea = AdministrativeArea(
        id = "1",
        localizedName = "Mazowieckie",
        englishName = "Masovia",
        localizedType = "Województwo",
        englishType = "Voivodeship",
        countryId = "PL"
    ),
    country = Country(
        id = "1", localizedName = "Polska", englishName = "Poland"
    ),
    region = Region(
        id = "1", localizedName = "Europa", englishName = "Europe"
    )
)