package com.kadiroz.hazir.data.datasource

import android.util.Log
import com.kadiroz.hazir.data.entity.Sepet
import com.kadiroz.hazir.data.entity.Urunler
import com.kadiroz.hazir.retrofit.UrunlerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UrunlerDataSource(var udao:UrunlerDao) {
    suspend fun  urunleriYukle() : List<Urunler> =
        withContext(Dispatchers.IO){
            return@withContext udao.urunleriYukle().yemekler
        }

    suspend fun urunEkle(yemek_adi:String, yemek_resim_adi:String, yemek_fiyat:Int, yemek_siparis_adet:Int, kullanici_adi:String) =
        udao.urunEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)

    suspend fun sepettekiUrunleriGetir(kullanici_adi: String) : List<Sepet> =
        withContext(Dispatchers.IO){
            return@withContext udao.sepettekiUrunleriGetir(kullanici_adi).sepet_yemekler
        }

    suspend fun sepettenUrunSil(sepet_yemek_id:Int,kullanici_adi:String) = udao.sepettenUrunSil(sepet_yemek_id,kullanici_adi)

    suspend fun ara(aramaKelimesi: String): List<Urunler> = withContext(Dispatchers.IO) {
        val liste = udao.urunleriYukle().yemekler
        val filtrelenmisListe = liste.filter { urun -> urun.yemek_adi.contains(aramaKelimesi, ignoreCase = true) }
        return@withContext filtrelenmisListe
    }


}