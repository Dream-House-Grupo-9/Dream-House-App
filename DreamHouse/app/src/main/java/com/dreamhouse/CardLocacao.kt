package com.dreamhouse

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dreamhouse.databinding.ActivityMainBinding

class CardLocacao : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun registrationLocation(view: View) {
        val screenLocation = Intent(
            this,
            RegisterLocation::class.java
        )
        startActivity(screenLocation)
    }

    fun voltar(view: View){
        startActivity(Intent(baseContext, HomeActivity::class.java))
    }
}