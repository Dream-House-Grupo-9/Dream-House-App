package com.dreamhouse.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dreamhouse.HomeActivity
import com.dreamhouse.RegisterLocation
import com.dreamhouse.databinding.ActivityMainBinding
import com.dreamhouse.databinding.LoginScreenBinding

class CardLocacao_fragment: Fragment() {

    private var CardBinding: ActivityMainBinding? = null
    private val binding get() = CardBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        CardBinding = ActivityMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun registrationLocation(view: View) {

        activity?.let {
        val screenLocation = (Intent(
            it,
            RegisterLocation::class.java
        ))
            startActivity(screenLocation)
        }
    }

    fun voltar(view: View) {
        activity?.let {
            startActivity(Intent(it, HomeActivity::class.java))
        }
    }
}