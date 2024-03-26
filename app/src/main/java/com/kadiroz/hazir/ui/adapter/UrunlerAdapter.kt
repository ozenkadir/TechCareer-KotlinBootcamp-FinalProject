package com.kadiroz.hazir.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.kadiroz.hazir.data.entity.Urunler
import com.kadiroz.hazir.databinding.CardTasarimBinding
import com.kadiroz.hazir.ui.fragment.AnasayfaFragmentDirections
import com.kadiroz.hazir.ui.viewmodel.AnasayfaViewModel

class UrunlerAdapter(var mContext: Context,
                     var urunlerListesi:List<Urunler>,
                     var viewModel: AnasayfaViewModel)
    : RecyclerView.Adapter<UrunlerAdapter.CardTasarimTutucu>() {
    inner class CardTasarimTutucu(var tasarim:CardTasarimBinding) : RecyclerView.ViewHolder(tasarim.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val binding = CardTasarimBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return CardTasarimTutucu(binding)
    }



    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {//0,1,2
        val urun = urunlerListesi.get(position)
        val t = holder.tasarim

        t.textViewUrunAdi.text = urun.yemek_adi
        t.textViewFiyat.text = "${urun.yemek_fiyat} ₺"



        t.CardViewUrun.setOnClickListener{
            val gecis = AnasayfaFragmentDirections.detayGecis(urun = urun)
            Navigation.findNavController(it).navigate(gecis)
        }

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${urun.yemek_resim_adi}"
        Glide.with(mContext).load(url).override(450,450).into(t.imageViewUrun)

    }
    override fun getItemCount(): Int {
        return urunlerListesi.size
    }


}