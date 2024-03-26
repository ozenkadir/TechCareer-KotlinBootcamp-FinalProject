package com.kadiroz.hazir.retrofit

class ApiUtils {
    companion object {
        val BASE_URL = "http://kasimadalan.pe.hu/"

        fun getUrunlerDao() : UrunlerDao {
            return RetrofitClient.getClient(BASE_URL).create(UrunlerDao::class.java)
        }

    }
}