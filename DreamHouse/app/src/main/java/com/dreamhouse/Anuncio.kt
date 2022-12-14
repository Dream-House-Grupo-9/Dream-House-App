package com.dreamhouse

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.dreamhouse.models.LocacaoListCard
import com.dreamhouse.network.ImovelApiService

class Anuncio : AppCompatActivity() {
    private lateinit var imovel: LocacaoListCard
    private lateinit var imovelBairro: TextView
    private lateinit var imovelEndereco: TextView
    private lateinit var imovelPerfil: TextView
    private lateinit var imovelValor: TextView
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
        imovelBairro = findViewById(R.id.tv_bairro)
        imovelEndereco = findViewById(R.id.tv_andress)
        imovelValor = findViewById(R.id.tv_value)
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
        imovelValor.text = imovel.valDiario
//        imovelPerfil.text = imovel.
        imovelZap.text = imovel.telefoneLocatario
        imovelDescricao.text = imovel.descricao
    }


    fun voltar(view: View) {
        startActivity(Intent(baseContext, HomeActivity::class.java))
    }
}