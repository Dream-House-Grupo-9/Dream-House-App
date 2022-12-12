package com.dreamhouse.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dreamhouse.R
import com.dreamhouse.models.LocacaoListCard
import com.dreamhouse.utils.pickFirstUrl

class ImovelGridAdapter(
    private val context: Context,
    private val imoveisList: List<LocacaoListCard>,
    private val onImovelClickListener: (LocacaoListCard) -> Unit
) : RecyclerView.Adapter<ImovelGridAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_res_card_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return imoveisList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imovel = imoveisList[position]
        holder.bind(context, imovel, onImovelClickListener)
    }

    class ViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            context: Context,
            imovel: LocacaoListCard,
            onImovelClickListener: (LocacaoListCard) -> Unit
        ) {
            Glide.with(itemView.context)
                .load(imovel.image?.pickFirstUrl())
                .into(itemView.findViewById(R.id.tv_imagem))

            itemView.findViewById<TextView>(R.id.id_txt_bairro).text = imovel.bairro

            itemView.findViewById<TextView>(R.id.id_txt_cidade).text = imovel.cidade


            itemView.setOnClickListener {
                onImovelClickListener(imovel)
            }

        }
    }

}