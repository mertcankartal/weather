package com.example.weather.util

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

fun View.visible(){
    this.visibility = View.VISIBLE
}

fun View.gone(){
    this.visibility = View.GONE
}

fun Fragment.toast(messageToShow: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(requireContext(), messageToShow, duration).show()
}