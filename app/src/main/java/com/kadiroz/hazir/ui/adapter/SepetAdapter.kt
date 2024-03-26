package com.kadiroz.hazir.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.kadiroz.hazir.data.entity.Sepet
import com.kadiroz.hazir.databinding.BasketCardTasarimBinding
import com.kadiroz.hazir.ui.viewmodel.SepetimViewModel

class SepetAdapter(var mContext: Context,
                   var sepetList: List<Sepet>,
                   var viewModel: SepetimViewModel)
    : RecyclerView.Adapter<SepetAdapter.CardTasarimTutucu>() {
    inner class CardTasarimTutucu(var tasarim: BasketCardTasarimBinding) :
        RecyclerView.ViewHolder(tasarim.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val binding = BasketCardTasarimBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return CardTasarimTutucu(binding)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val sepet = sepetList.get(position)
        val t = holder.tasarim

        t.sepetUrunAdi.text = sepet.yemek_adi
        t.sepetUrunAdetFiyat.text = "${ sepet.yemek_fiyat} ₺"
        t.sepetUrunAdet.text = "${ sepet.yemek_siparis_adet} "
        t.sepetUrunToplamFiyat.text = "${sepet.yemek_fiyat * sepet.yemek_siparis_adet} ₺"

        t.buttonSepetUrunSil.setOnClickListener {
            Snackbar.make(it,"${sepet.yemek_adi} silinsin mi?",Snackbar.LENGTH_SHORT)
                .setAction("EVET"){
                    viewModel.sepettenUrunSil(sepet.sepet_yemek_id,sepet.kullanici_adi)
                }.show()
        }

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${sepet.yemek_resim_adi}"
        Glide.with(mContext).load(url).override(450,450).into(t.sepetImage)
    }

    override fun getItemCount(): Int {
        return sepetList.size
    }


    fun hesaplaToplamFiyat() {
        var toplam = 0
        for (sepet in sepetList) {
            toplam += sepet.yemek_fiyat * sepet.yemek_siparis_adet
        }
        viewModel.toplamSepet.value = toplam.toString()
    }




}
