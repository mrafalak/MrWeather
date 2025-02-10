MrWeather to aplikacja mobilna, która pozwala na szybkie sprawdzenie warunków pogodowych w wybranym miejscu.

Funkcjonalności
- Wyszukiwanie miast
- Podgląd aktualnej pogody
- Prognoza pogody na najbliższe dni

Konfiguracja
Aby aplikacja działała poprawnie, należy dodać plik local.properties w katalogu głównym projektu i umieścić w nim klucz API:

# Release
API_KEY="5eJLLEcii1n8r5stPdsasNAdCEqPt8zl"

# Test (w razie wyczerpania limitu zapytań)
#API_KEY="xhZy5HEFl6Mz3FrW3jkQ702F9sBcef0c"

Jeżeli aplikacja przestanie zwracać wyniki ze względu na ograniczenia zapytań do API, zaleca się skorzystanie z klucza testowego.
