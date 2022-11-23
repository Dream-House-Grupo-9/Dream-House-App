package com.dreamhouse

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dreamhouse.Rest.Rest
import com.dreamhouse.databinding.LoginScreenBinding
import com.dreamhouse.models.LoginResponse
import com.dreamhouse.models.UsuarioLogin
import com.dreamhouse.services.UsuarioService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginScreen : AppCompatActivity() {
    private lateinit var etEmail: EditText
    private lateinit var etSenha: EditText
    private val retrofit = Rest.getInstance()
    private lateinit var binding: LoginScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LoginScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        etEmail = findViewById(R.id.emailLoginET)
        etSenha = findViewById(R.id.senhaLoginET)

        binding.linkCadastro.setOnClickListener {
            redirecionarCadastro()
        }

    }

    fun login(view: View) {
        val senha = etSenha.text.toString()
        val email = etEmail.text.toString()
        val body = UsuarioLogin(email, senha)
        val usuarioRequest = retrofit.create(UsuarioService::class.java)

        usuarioRequest.login(body).enqueue(
            object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful) {
                        val editor = getSharedPreferences(
                            "USER",
                            Context.MODE_PRIVATE
                        ).edit()
                        editor.putInt("id", response.body()?.idCliente!!)
                        editor.apply()
                        startActivity(Intent(baseContext, RegisterLocation::class.java))
                    } else {
                        Toast.makeText(
                            baseContext, "Email ou senha incorretos!", Toast.LENGTH_LONG
                        ).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
                }

            }
        )
    }

    fun voltar(view: View) {
        startActivity(Intent(baseContext, LoginScreen::class.java))
    }

    private fun redirecionarCadastro() {
        val telaCadastro = Intent(this, MainActivity::class.java)
        startActivity(telaCadastro)
    }

}