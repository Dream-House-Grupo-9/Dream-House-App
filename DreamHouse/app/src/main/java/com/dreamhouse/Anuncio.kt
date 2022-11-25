package com.dreamhouse

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class Anuncio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anuncio)
    }

    fun voltar(view: View){
        startActivity(Intent(baseContext, HomeActivity::class.java))
    }
}