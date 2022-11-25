package com.dreamhouse

import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.dreamhouse.Rest.Rest
import com.dreamhouse.databinding.ActivityRegisterLocationBinding
import com.dreamhouse.models.ClientId
import com.dreamhouse.models.Locacao
import com.dreamhouse.models.idDetalhesAnuncio
import com.dreamhouse.services.LocacaoService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class RegisterLocation : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterLocationBinding
    private val request = Rest.getInstance().create(LocacaoService::class.java)

    private val uris = mutableListOf<Uri?>(
        null,
        null,
        null,
        null
    )

    private var uriSelected: Uri? = null

    private var imageSelected: ImageView? = null

    private val actForResult = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) {
        uriSelected = it
        imageSelected?.setImageURI(uriSelected)
        when (imageSelected?.id) {
            R.id.image1 -> uris.add(0, uriSelected)
            R.id.image2 -> uris.add(1, uriSelected)
            R.id.image3 -> uris.add(2, uriSelected)
            R.id.image4 -> uris.add(3, uriSelected)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.image1.setOnClickListener {
            actForResult.launch("image/*")
            imageSelected = binding.image1
        }
        binding.image2.setOnClickListener {
            actForResult.launch("image/*")
            imageSelected = binding.image2
        }
        binding.image3.setOnClickListener {
            actForResult.launch("image/*")
            imageSelected = binding.image3
        }
        binding.image4.setOnClickListener {
            actForResult.launch("image/*")
            imageSelected = binding.image4
        }
        binding.btnAvancar.setOnClickListener {
            createLocacao()
        }
    }

    private fun createLocacao() {
        val locacao = buildLocacao()
        val imagens = buildImagens()
        val listaBytes = mutableListOf<ByteArray>()
        imagens.forEach { file ->
            listaBytes.add(file.inputStream().readBytes())
        }
        request.createLocacao(locacao).enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                Toast.makeText(baseContext, "Criou locacao", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun buildImagens(): List<File> {
        val files = mutableListOf<File>()
        uris.forEach { uri ->
            files.add(File(uri.toString()))
        }
        return files
    }

    private fun buildLocacao(): Locacao {
        return Locacao(
            binding.EtTitulo.text.toString(),
            binding.ETInicioDisp.text.toString(),
            binding.ETFimDisp.text.toString(),
            binding.ETBairro.text.toString(),
            binding.ETCidade.text.toString(),
            binding.ETLogradouro.text.toString(),
            idDetalhesAnuncio(
                when (binding.radioGroupDiaria.checkedRadioButtonId) {
                    R.id.radio_diaria_s -> true
                    else -> false
                },
                when (binding.radioGroupSemanal.checkedRadioButtonId) {
                    R.id.radio_diaria_s -> true
                    else -> false
                },
                when (binding.radioGroupMensal.checkedRadioButtonId) {
                    R.id.radio_diaria_s -> true
                    else -> false
                },
                when (binding.radioGroupGaragem.checkedRadioButtonId) {
                    R.id.radio_diaria_s -> true
                    else -> false
                },
                when (binding.radioGroupMobilia.checkedRadioButtonId) {
                    R.id.radio_diaria_s -> true
                    else -> false
                },
            ),
            binding.EtDescricao.text.toString(),
            ClientId(getSharedPreferences("USER", MODE_PRIVATE).getInt("id", 0))
        )
    }
}