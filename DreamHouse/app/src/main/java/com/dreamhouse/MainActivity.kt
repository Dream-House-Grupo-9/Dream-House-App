package com.dreamhouse

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dreamhouse.Rest.Rest
import com.dreamhouse.databinding.ActivityMainBinding
import com.dreamhouse.models.Usuario
import com.dreamhouse.services.UsuarioService
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

//  private lateinit var etCPF: EditText
//  private lateinit var etTelefone: EditText
//  private lateinit var etCelular: EditText
    private lateinit var etNome: EditText
    private lateinit var etEmail: EditText
    private lateinit var etSenha: EditText
    private val retrofit = Rest.getInstance()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        etNome = findViewById(R.id.nomeCompletoET)
        etEmail = findViewById(R.id.emailCadastroET)
        etSenha = findViewById(R.id.senhaCadastroET)

        binding.linkLogin.setOnClickListener {
            redirecionarLogin()
        }

    }

    fun cadastrar(view: View) {
        val usuarioRequest = retrofit.create(UsuarioService::class.java)
        if (validarCampos()) {
            val novoUsuario = Usuario(
                idUsuario = null,
                nome = etNome.text.toString(),
                email = etEmail.text.toString(),
                senha = etSenha.text.toString()
            )
            usuarioRequest.cadastrar(novoUsuario).enqueue(
                object : retrofit2.Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        if (response.isSuccessful) {
                            Toast.makeText(
                                baseContext,
                                "Usuario cadastrado com sucesso!", Toast.LENGTH_LONG
                            ).show()
                            startActivity(Intent(baseContext, LoginScreen::class.java))
                        } else {
                            Toast.makeText(
                                baseContext,
                                "Algo deu errado, tente novamente mais tarde!", Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
                    }
                }
            )
        }
    }


    private fun validarCampos(): Boolean {
        if (etNome.text.isNullOrEmpty()) {
            etNome.error = "Preencha esse campo!"
            return false
        } else if (etSenha.text.isNullOrEmpty()) {
            etSenha.error = "Preencha esse campo!"
            return false
        } else if (etSenha.text.length < 6) {
            etSenha.error = "A senha precisa conter 6 digitos"
            return false
        } else if (etEmail.text.isNullOrEmpty()) {
            etEmail.error = "Preencha esse campo!"
            return false
        } else if (!etEmail.text.toString().contains("@")) {
            etEmail.error = "Campo precisa de '@'"
            return false
        }
        return true
    }

    private fun redirecionarLogin() {
        val telaLogin = Intent(this, LoginScreen::class.java)
        startActivity(telaLogin)
    }

}
