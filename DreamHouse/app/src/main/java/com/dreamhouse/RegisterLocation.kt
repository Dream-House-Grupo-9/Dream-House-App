package com.dreamhouse

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.dreamhouse.databinding.ActivityRegisterLocationBinding
import com.dreamhouse.models.ClientId
import com.dreamhouse.models.Locacao
import com.dreamhouse.models.idDetalhesAnuncio
import com.dreamhouse.rest.Rest
import com.dreamhouse.services.LocacaoService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class RegisterLocation : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterLocationBinding
    private val request = Rest.getInstance().create(LocacaoService::class.java)

    private val listImage = arrayOf("","","")


    private val uris = mutableListOf<Uri?>(
        null,
        null,
        null,
        null
    )

    private var uriSelected: Uri? = null
    private var stringListImage = ""

    private var imageSelected: ImageView? = null
    private val imgurService = ImgurApiService()


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
        setupListeners()
    }

    private fun setupImageUpload(image: ImageView) {
        val launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK
                && result.data != null
            ) {
                val photoUri = result.data!!.data
                image.setImageURI(photoUri)
                val imageStream = contentResolver.openInputStream(photoUri!!)
                val selectedImage = BitmapFactory.decodeStream(imageStream)
                when(image.id) {
                    binding.image1.id -> postImageImgur(selectedImage, 0)
                    binding.image2.id -> postImageImgur(selectedImage, 1)
                    binding.image3.id -> postImageImgur(selectedImage, 2)
                    binding.image4.id -> postImageImgur(selectedImage, 3)

                }
                imageStream?.close()
//                imageStream?.closeQuietly()
            }
        }

        image.setOnClickListener { view ->
            val intent =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            launcher.launch(intent)
        }
    }

    private fun setupListeners() {

//        binding.image1.setOnClickListener {
//            actForResult.launch("image/*")
//            imageSelected = binding.image1
//        }
//        binding.image2.setOnClickListener {
//            actForResult.launch("image/*")
//            imageSelected = binding.image2
//        }
//        binding.image3.setOnClickListener {
//            actForResult.launch("image/*")
//            imageSelected = binding.image3
//        }
//        binding.image4.setOnClickListener {
//            actForResult.launch("image/*")
//            imageSelected = binding.image4
//        }

        setupImageUpload(binding.image1)
        setupImageUpload(binding.image2)
        setupImageUpload(binding.image3)
        setupImageUpload(binding.image4)

        binding.btnAvancar.setOnClickListener {

            listImage.forEachIndexed { index, item ->
                if (item.isNotBlank()) {
                    if (index != listImage.size - 1) {
                        stringListImage += "$item,"
                    } else {
                        stringListImage += item
                    }
                }
            }

            createLocacao()
        }
    }

    private fun createLocacao() {
        val locacao = buildLocacao()
//        val imagens = buildImagens()
//        val listaBytes = mutableListOf<ByteArray>()
//        imagens.forEach { file ->
//            listaBytes.add(file.inputStream().readBytes())
//        }

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
            stringListImage,
            ClientId(getSharedPreferences("USER", MODE_PRIVATE).getInt("id", 0))
        )
    }

    private fun postImageImgur(image: Bitmap, index: Int){
        imgurService.uploadImageToImgur(image, index, this)
    }

    fun addImages(newImage: String, index: Int) {
        listImage[index] = newImage
    }

    fun voltar(view: View){
        startActivity(Intent(baseContext, HomeActivity::class.java))
    }
}