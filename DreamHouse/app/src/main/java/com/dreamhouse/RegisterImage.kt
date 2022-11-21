package com.dreamhouse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dreamhouse.databinding.ActivityMainBinding

class RegisterImage : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}