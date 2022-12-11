package com.dreamhouse

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.dreamhouse.models.Locacao
import com.dreamhouse.models.LocacaoListCard
import com.dreamhouse.network.ImovelApiService

class Anuncio : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var imovel: LocacaoListCard
    private lateinit var imovelBairro: TextView
    private lateinit var imovelEndereco: TextView
    private lateinit var imovelCep: TextView
    private lateinit var imovelPerfil: TextView
    private lateinit var imovelZap: TextView
    private lateinit var imovelDescricao: TextView
    private val imovelService = ImovelApiService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anuncio)

        initVariables()
        initUI()
    }

    private fun initVariables() {
//        recyclerView = findViewById(R.id.recycler_view)
        imovelBairro = findViewById(R.id.tv_bairro)
        imovelEndereco = findViewById(R.id.tv_andress)
        imovelCep = findViewById(R.id.tv_cep)
        imovelPerfil = findViewById(R.id.tv_perfil)
        imovelZap = findViewById(R.id.tv_zap)
        imovelDescricao = findViewById(R.id.tv_description)

    }

    private fun initUI() {
        getImovelData()
    }

    private fun getImovelData() {
        imovelService.getDetalhesAnuncio(
            idImovel = imovel.id.toString(),
            context = this
        ) { imovel ->
            fillImovelInfo(imovel)
        }
    }

    private fun fillImovelInfo(imovel: LocacaoListCard) {
        imovelBairro.text = imovel.bairro
        imovelEndereco.text = imovel.logradouro
//        imovelCep.text = imovel.cep
//        imovelPerfil.text = imovel.
//        imovelZap.text = imovel.
        imovelDescricao.text = imovel.descricao
    }


    fun voltar(view: View) {
        startActivity(Intent(baseContext, HomeActivity::class.java))
    }
}