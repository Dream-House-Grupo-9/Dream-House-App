package com.dreamhouse

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import com.dreamhouse.rest.Rest
import com.dreamhouse.services.LocacaoService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterLocation : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterLocationBinding
    private val request = Rest.getInstance().create(LocacaoService::class.java)

    private val listImage = arrayOf("", "", "", "")


//    private val uris = mutableListOf<Uri?>(
//        null,
//        null,
//        null,
//        null
//    )

//    private var uriSelected: Uri? = null
    private var stringListImage = ""

//    private var imageSelected: ImageView? = null
    private val imgurService = ImgurApiService()

//
//    private val actForResult = registerForActivityResult(
//        ActivityResultContracts.GetContent()
//    ) {
//        uriSelected = it
//        imageSelected?.setImageURI(uriSelected)
//        when (imageSelected?.id) {
//            R.id.image1 -> uris.add(0, uriSelected)
//            R.id.image2 -> uris.add(1, uriSelected)
//            R.id.image3 -> uris.add(2, uriSelected)
//            R.id.image4 -> uris.add(3, uriSelected)
//        }
//    }

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
                when (image.id) {
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

        request.createLocacao(locacao).enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                if (response.isSuccessful) {
                    Toast.makeText(baseContext, "Criou locacao", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(baseContext, CardLocacao::class.java))

                }
            }
            override fun onFailure(call: Call<Any>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

//    private fun buildImagens(): List<File> {
//        val files = mutableListOf<File>()
//        uris.forEach { uri ->
//            files.add(File(uri.toString()))
//        }
//        return files
//    }

    private fun buildLocacao(): Locacao {
        return Locacao(
            binding.EtTitulo.text.toString(),
            binding.EtTelefone.text.toString(),
            binding.ETCategoria.text.toString(),
            binding.ETBairro.text.toString(),
            binding.ETCidade.text.toString(),
            binding.ETLogradouro.text.toString(),
            binding.ETNumero.text.toString().toInt(),
            binding.EtDescricao.text.toString(),
            binding.ETValDiario.text.toString().toDouble(),
            stringListImage,
            ClientId(getSharedPreferences("USER", MODE_PRIVATE).getInt("id", 0))
        )
    }

    private fun postImageImgur(image: Bitmap, index: Int) {
        imgurService.uploadImageToImgur(image, index, this)
    }

    fun addImages(newImage: String, index: Int) {
        listImage[index] = newImage
    }

    fun voltar(view: View) {
        startActivity(Intent(baseContext, HomeActivity::class.java))
    }
}