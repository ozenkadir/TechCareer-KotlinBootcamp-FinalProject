package com.kadiroz.hazir.data.repo

import androidx.lifecycle.MutableLiveData
import com.kadiroz.hazir.data.datasource.UrunlerDataSource
import com.kadiroz.hazir.data.entity.Sepet
import com.kadiroz.hazir.data.entity.Urunler
import com.kadiroz.hazir.retrofit.UrunlerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UrunlerRepository(var uds: UrunlerDataSource) {


    suspend fun urunleriYukle() : List<Urunler> = uds.urunleriYukle()

    suspend fun urunEkle(yemek_adi:String, yemek_resim_adi:String, yemek_fiyat:Int, yemek_siparis_adet:Int, kullanici_adi:String) = uds.urunEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)

    suspend fun sepettekiUrunleriGetir(kullanici_adi: String) : List<Sepet> = uds.sepettekiUrunleriGetir(kullanici_adi)

    suspend fun sepettenUrunSil(sepet_yemek_id:Int,kullanici_adi:String) = uds.sepettenUrunSil(sepet_yemek_id,kullanici_adi)

    suspend fun ara(aramaKelimesi:String) : List<Urunler> = uds.ara(aramaKelimesi)



}