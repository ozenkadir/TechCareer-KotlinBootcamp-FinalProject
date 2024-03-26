package com.kadiroz.hazir.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kadiroz.hazir.data.entity.Urunler
import com.kadiroz.hazir.data.entity.UrunlerCevap
import com.kadiroz.hazir.data.repo.UrunlerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnasayfaViewModel @Inject constructor(var urepo:UrunlerRepository)  : ViewModel() {
    var urunlerListesi = MutableLiveData<List<Urunler>>()
    private val _aramaSonuclari = MutableLiveData<List<Urunler>>()
    val aramaSonuclari: LiveData<List<Urunler>> = _aramaSonuclari

    init {
        urunleriYukle()
    }

    fun urunleriYukle(){
        CoroutineScope(Dispatchers.Main).launch {
            urunlerListesi.value = urepo.urunleriYukle()
        }
    }

    fun ara(aramaKelimesi: String) {
       CoroutineScope(Dispatchers.Main).launch {
            val sonuclar = urepo.ara(aramaKelimesi)
            _aramaSonuclari.value = sonuclar
           urunlerListesi.value = sonuclar // urunlerListesi'ni de güncelle
       }
    }







}