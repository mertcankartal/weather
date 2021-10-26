package com.example.finalproject.models.response

import com.example.finalproject.models.model_search.WeatherSearchItem

data class SearchAutoCompleteResponse(
    val data:List<WeatherSearchItem>
)
