package com.kadiroz.hazir.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.kadiroz.hazir.databinding.FragmentSepetimBinding
import com.kadiroz.hazir.ui.adapter.SepetAdapter

import com.kadiroz.hazir.ui.viewmodel.SepetimViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SepetimFragment : Fragment() {
    private lateinit var binding: FragmentSepetimBinding
    private lateinit var viewModel: SepetimViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSepetimBinding.inflate(inflater,container,false)

        viewModel.sepetList.observe(viewLifecycleOwner){
            val sepetAdapter = SepetAdapter(requireContext(),it,viewModel)
            binding.sepetimRv.adapter = sepetAdapter
            binding.sepetimRv.layoutManager = StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL)
            sepetAdapter.hesaplaToplamFiyat()
        }

        viewModel.toplamSepet.observe(viewLifecycleOwner){
            binding.sepetUrunlerTotalFiyat.text = "Toplam: ${it} ₺"
        }


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: SepetimViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.sepettekiUrunleriGetir("kadir_ozen")
    }


}