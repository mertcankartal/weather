package com.example.weather.ui.home

import com.example.finalproject.models.response.SearchAutoCompleteResponse


class AutoSearchStateModel(private val response : SearchAutoCompleteResponse) {

    //list of searched location
    fun getAllResults():List<String>{
        val cityList = mutableListOf<String>()
        response.data.forEach {
            cityList.add(it.name)
        }
        return cityList
    }

}