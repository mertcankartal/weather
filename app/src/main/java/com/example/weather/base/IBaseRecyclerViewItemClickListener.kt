package com.example.finalproject.base

import androidx.annotation.IdRes

interface IBaseRecyclerViewItemClickListener<T> {
    fun onClick(clickedObject: T, @IdRes id: Int = 0)
}