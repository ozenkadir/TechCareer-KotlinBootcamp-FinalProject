package com.kadiroz.hazir.ui.fragment

import android.os.Bundle
import android.provider.SyncStateContract.Helpers
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import androidx.appcompat.widget.ThemedSpinnerAdapter.Helper
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.kadiroz.hazir.R
import com.kadiroz.hazir.data.entity.Urunler
import com.kadiroz.hazir.databinding.FragmentAnasayfaBinding
import com.kadiroz.hazir.ui.adapter.UrunlerAdapter
import com.kadiroz.hazir.ui.viewmodel.AnasayfaViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.http.Query

@AndroidEntryPoint
class AnasayfaFragment : Fragment() {
    private lateinit var binding: FragmentAnasayfaBinding
    private lateinit var viewModel: AnasayfaViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAnasayfaBinding.inflate(inflater, container, false)


        viewModel.urunlerListesi.observe(viewLifecycleOwner){
            val urunlerAdapter = UrunlerAdapter(requireContext(),it,viewModel)
            binding.urunRv.adapter = urunlerAdapter

            binding.urunRv.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)

        }


        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener{
            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.ara(newText)
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.ara(query)
                return true
            }
        })



        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:AnasayfaViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.urunleriYukle()
    }

}