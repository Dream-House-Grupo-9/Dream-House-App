package com.dreamhouse

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dreamhouse.databinding.TelaInicialBinding

class TelaInicial: AppCompatActivity() {

    private lateinit var binding: TelaInicialBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_inicial)

    }


    private fun redirecionarLogin(view: View){
        startActivity(Intent(baseContext, LoginScreen::class.java))
    }

    private fun redirecionarCadastro() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}