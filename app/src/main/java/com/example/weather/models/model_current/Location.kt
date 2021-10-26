package com.example.finalproject.models.model_current

import androidx.room.ColumnInfo

data class Location(
    @ColumnInfo(name="name") val name: String,
    @ColumnInfo(name="region") val region: String,
)