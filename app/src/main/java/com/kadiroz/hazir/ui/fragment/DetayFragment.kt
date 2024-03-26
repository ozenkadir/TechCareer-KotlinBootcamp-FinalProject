package com.kadiroz.hazir.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.kadiroz.hazir.databinding.FragmentDetayBinding
import com.kadiroz.hazir.ui.viewmodel.UrunDetayViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetayFragment : Fragment() {
    private lateinit var binding: FragmentDetayBinding
    private lateinit var viewModel: UrunDetayViewModel
    var adet = 1


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetayBinding.inflate(inflater, container, false)
        val bundle:DetayFragmentArgs by navArgs()
        val gelenUrun = bundle.urun
        binding


        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${gelenUrun.yemek_resim_adi}"
        Glide.with(this).load(url).override(450,450).into(binding.ivUrunResmi)

        binding.ivUrunAdi.text = gelenUrun.yemek_adi
        binding.ivUrunFiyat.text = "${gelenUrun.yemek_fiyat} ₺"
        val urunAdetText = binding.urunAdet.text.toString()
        val urunAdet = urunAdetText.toIntOrNull() ?: 0 // Eğer dönüşüm hatası olursa varsayılan değer olarak 0 kullanılır
        binding.ivTotalFiyat.text = "${gelenUrun.yemek_fiyat * urunAdet}"


        binding.ivFabArtis.setOnClickListener {
            fun btnArti() {
                adet++
                binding.urunAdet.text = adet.toString()
                val toplamFiyat = gelenUrun.yemek_fiyat * adet
                binding.ivTotalFiyat.text = "${toplamFiyat} ₺"
            }
            btnArti()
        }

        binding.ivFabAzaltis.setOnClickListener {
            fun btnEksi() {
                if (adet > 1) {
                    adet--
                    binding.urunAdet.text = adet.toString()
                    val toplamFiyat = gelenUrun.yemek_fiyat * adet
                    binding.ivTotalFiyat.text = "${toplamFiyat} ₺"
                }
            }
            btnEksi()
        }

        binding.buttonSepeteEkle.setOnClickListener {
            Log.d("DetayFragment", "Button clicked")

            val yemek_adi = binding.ivUrunAdi.text.toString()
            Log.d("DetayFragment", "Yemek adı: $yemek_adi")

            val yemek_resim_adi = gelenUrun.yemek_resim_adi
            Log.d("DetayFragment", "Yemek resim adı: $yemek_resim_adi")

            val yemek_fiyat = gelenUrun.yemek_fiyat
            Log.d("DetayFragment", "Yemek fiyat: $yemek_fiyat")


            val yemek_siparis_adet = binding.urunAdet.text.toString().toIntOrNull() ?: 0
            Log.d("DetayFragment", "Yemek sipariş adet: $yemek_siparis_adet")

            val kullanici_adi = "kadir_ozen"
            Log.d("DetayFragment", "Kullanıcı adı: $kullanici_adi")

            viewModel.urunEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: UrunDetayViewModel by viewModels()
        viewModel = tempViewModel
    }
}

