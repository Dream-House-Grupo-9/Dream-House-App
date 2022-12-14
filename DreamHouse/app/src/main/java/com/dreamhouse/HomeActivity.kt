package com.dreamhouse

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.dreamhouse.databinding.ActivityHomeBinding
import com.dreamhouse.network.ImovelApiService


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var recyclerView: RecyclerView
    private val imovelService = ImovelApiService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVariables()
        initUI()
    }

    private fun initVariables() {
        recyclerView = findViewById(R.id.recycler_view)
    }

    private fun initUI() {
        setDataToRecyclerView()
    }

    private fun setDataToRecyclerView() {
        imovelService.getCardImoveis(
            context = this,
            intent = Intent(this, Anuncio::class.java),
            recyclerView = recyclerView
        )
    }

//    fun visitar(view: View) {
//        startActivity(Intent(baseContext, Anuncio::class.java))
//    }

    fun irCadastro(view: View) {
        startActivity(Intent(baseContext, RegisterLocation::class.java))
    }

    fun logout(view: View) {

        startActivity(Intent(baseContext, LoginScreen::class.java))
    }

}