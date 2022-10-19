package com.dreamhouse

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class TermosDeUso: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_termos_de_uso)
    }


    fun voltar(view: View){
        startActivity(Intent(baseContext, MainActivity::class.java))
    }
}