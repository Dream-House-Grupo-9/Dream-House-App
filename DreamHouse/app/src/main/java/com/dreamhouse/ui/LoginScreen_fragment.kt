package com.dreamhouse.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.dreamhouse.HomeActivity
import com.dreamhouse.LoginScreen
import com.dreamhouse.MainActivity
import com.dreamhouse.databinding.LoginScreenBinding
import com.dreamhouse.models.LoginResponse
import com.dreamhouse.models.UsuarioLogin
import com.dreamhouse.rest.Rest
import com.dreamhouse.services.UsuarioService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginScreen_fragment: Fragment() {

    private lateinit var etEmail: EditText
    private lateinit var etSenha: EditText
    private val retrofit = Rest.getInstance()
    private var LoginBinding: LoginScreenBinding? = null
    private val binding get() = LoginBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        LoginBinding = LoginScreenBinding.inflate(inflater, container, false)
        etEmail = binding.emailLoginET
        etSenha = binding.senhaLoginET

        binding.linkCadastro.setOnClickListener {
            redirecionarCadastro()
        }
        return binding.root
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
                        val editor = getActivity()?.getSharedPreferences(
                            "USER",
                            Context.MODE_PRIVATE
                        )?.edit()
                        editor?.putInt("id", response.body()?.idCliente!!)
                        editor?.apply()
                        activity?.let {
                            startActivity(Intent(it, LoginScreen::class.java))
                        }
                    } else {
                        activity?.let {
                            Toast.makeText(
                                it, "Email ou senha incorretos!", Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    activity?.let {
                        Toast.makeText(it, t.message, Toast.LENGTH_LONG).show()
                    }
                }

            }
        )
    }

    fun voltar(view: View) {
        activity?.let {
            startActivity(Intent(it, MainActivity::class.java))
        }
    }

    private fun redirecionarCadastro() {
        activity?.let {
            startActivity(Intent(it, MainActivity::class.java))
        }
    }



}