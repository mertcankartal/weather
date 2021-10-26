package com.example.weather.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.weather.R
import com.example.weather.ui.home.HomeFragment
import kotlinx.android.synthetic.main.fragment_splash.*
import java.util.*

class SplashFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Timer().schedule(object : TimerTask(){
            override fun run() {
                val transaction = activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.splashContainer,HomeFragment())
                    ?.commit()
            }
        },3000)

    }
}