package com.kadiroz.hazir.ui.viewmodel

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.load.engine.Resource
import com.kadiroz.hazir.data.entity.Sepet
import com.kadiroz.hazir.data.entity.SepetCevap
import com.kadiroz.hazir.data.repo.UrunlerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SepetimViewModel  @Inject constructor(var urepo:UrunlerRepository) : ViewModel() {
    var sepetList = MutableLiveData<List<Sepet>>()
    var toplamSepet = MutableLiveData("0")


    fun sepettekiUrunleriGetir(kullanici_adi:String){
        CoroutineScope(Dispatchers.Main).launch {
            try{
                sepetList.value = urepo.sepettekiUrunleriGetir(kullanici_adi)
            }catch (e:Exception){}
        }
    }

    fun sepettenUrunSil(sepet_yemek_id: Int, kullanici_adi: String) {
        CoroutineScope(Dispatchers.Main).launch {
            urepo.sepettenUrunSil(sepet_yemek_id, kullanici_adi)
            sepetList.value?.let { currentList ->
                val updatedList = currentList.toMutableList()
                updatedList.removeAll { it.sepet_yemek_id == sepet_yemek_id }
                sepetList.value = updatedList
                if (updatedList.isNullOrEmpty()) {
                    sepetList.value = emptyList()
                }
            }
        }
    }

}