package com.example.finalproject.models.model_current

import androidx.room.ColumnInfo

data class Condition(
    @ColumnInfo(name = "code") val code: Int,
    @ColumnInfo(name = "icon") val icon: String,
    @ColumnInfo(name = "text") val text: String
)