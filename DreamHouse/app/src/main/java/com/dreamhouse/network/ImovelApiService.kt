package com.dreamhouse.network

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.dreamhouse.adapters.ImovelGridAdapter
import com.dreamhouse.models.LocacaoListCard
import com.dreamhouse.rest.Rest
import com.dreamhouse.services.LocacaoService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImovelApiService {
    private val retrofit = Rest.getInstance()
    private val imovelApiConnection = retrofit.create(LocacaoService::class.java)

    fun getCardAnimaisAdotante(
        context: Context,
        intent: Intent,
        recyclerView: RecyclerView
    ) {

        val getAnuncios = imovelApiConnection.getLocacao()

        getAnuncios.enqueue(object : Callback<List<LocacaoListCard>> {
            override fun onResponse(
                call: Call<List<LocacaoListCard>>,
                response: Response<List<LocacaoListCard>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { listaImovel ->
                        recyclerView.adapter =
                            ImovelGridAdapter(context = context, listaImovel) { imovel ->
                                context.startActivity(intent)
                                (context as Activity).finish()
                            }
                    }
                }
            }

            override fun onFailure(call: Call<List<LocacaoListCard>>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}