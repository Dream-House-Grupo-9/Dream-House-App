package com.dreamhouse

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class LocationDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_detail)
    }

    fun voltar(view: View){
        startActivity(Intent(baseContext, RegisterLocation::class.java))
    }
}