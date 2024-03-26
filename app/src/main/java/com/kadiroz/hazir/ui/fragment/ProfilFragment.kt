package com.kadiroz.hazir.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.kadiroz.hazir.databinding.FragmentProfilBinding
import com.kadiroz.hazir.ui.viewmodel.ProfilViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfilFragment : Fragment() {
    private  lateinit var binding: FragmentProfilBinding
    private  lateinit var viewModel: ProfilViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfilBinding.inflate(inflater,container,false)










        return binding.root
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: ProfilViewModel by viewModels()
        viewModel = tempViewModel
    }


}