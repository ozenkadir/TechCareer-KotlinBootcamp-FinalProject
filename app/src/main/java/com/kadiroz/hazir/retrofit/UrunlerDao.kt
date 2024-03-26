package com.kadiroz.hazir.retrofit

import com.kadiroz.hazir.data.entity.CRUDCevap
import com.kadiroz.hazir.data.entity.SepetCevap
import com.kadiroz.hazir.data.entity.Urunler
import com.kadiroz.hazir.data.entity.UrunlerCevap
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UrunlerDao {

    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun urunleriYukle() : UrunlerCevap

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun urunEkle(@Field("yemek_adi") yemek_adi:String,
                 @Field("yemek_resim_adi") yemek_resim_adi:String,
                 @Field("yemek_fiyat") yemek_fiyat: Int,
                 @Field("yemek_siparis_adet") yemek_siparis_adet: Int,
                 @Field("kullanici_adi") kullanici_adi: String
    ) : CRUDCevap

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    suspend fun sepettekiUrunleriGetir(@Field("kullanici_adi") kullanici_adi: String) : SepetCevap


    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    suspend fun sepettenUrunSil(@Field("sepet_yemek_id") sepet_yemek_id:Int,
                                @Field("kullanici_adi") kullanici_adi:String) : CRUDCevap

}