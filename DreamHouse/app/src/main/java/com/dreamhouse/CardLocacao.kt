package com.dreamhouse

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.dreamhouse.databinding.ActivityCardLocacaoBinding
import com.dreamhouse.models.LocacaoListCard
import com.dreamhouse.network.ImovelApiService
import com.dreamhouse.rest.Rest

class CardLocacao : AppCompatActivity() {

    private val retrofit = Rest.getInstance()
    private lateinit var binding: ActivityCardLocacaoBinding
    private lateinit var recyclerView: RecyclerView
    val listLocacao = mutableListOf<LocacaoListCard>()
    private lateinit var etCidade: TextView
    private lateinit var etBairro: TextView
    private val imovelService = ImovelApiService()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardLocacaoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVariables()
        initUI()
    }

    private fun initVariables() {
        recyclerView = findViewById(R.id.recyclerContainer)
        etCidade = findViewById(R.id.id_txt_cidade)
        etBairro = findViewById(R.id.id_txt_bairro)
    }

    private fun initUI() {
        setDataToRecyclerView()
    }

    private fun setDataToRecyclerView() {
        imovelService.getCardAnimaisAdotante(
            context = this,
            intent = Intent(this, Anuncio::class.java),
            recyclerView = recyclerView
        )
    }


    fun registrationLocation(view: View) {
        val screenLocation = Intent(
            this,
            RegisterLocation::class.java
        )
        startActivity(screenLocation)
    }

    fun voltar(view: View) {
        startActivity(Intent(baseContext, HomeActivity::class.java))
    }

}