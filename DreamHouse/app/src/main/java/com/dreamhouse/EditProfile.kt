package com.dreamhouse

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class EditProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
    }

    fun voltar(view: View){
        startActivity(Intent(baseContext, HomeActivity::class.java))
    }

    fun salvar(){

    }

    fun irCadastro(view: View) {
        startActivity(Intent(baseContext, RegisterLocation::class.java))
    }

    fun irAnuncios(view: View) {
        startActivity(Intent(baseContext, HomeActivity::class.java))
    }


}