package com.example.currencyapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.currencyapp.EventObserver
import com.example.currencyapp.R
import com.example.currencyapp.VM
import com.example.currencyapp.base.BaseFragment
import com.example.currencyapp.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSearchBinding.inflate(inflater, container, false)

    private val vm by viewModels<VM>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSearch.setOnClickListener { vm.fetchCurrency() }
        initObserver()
    }

    private fun initObserver() {
        vm.loadingState.observe(viewLifecycleOwner) { setIsLoading(it) }
        vm.currencyResponse.observe(viewLifecycleOwner, EventObserver {

            val usd = getString(R.string.usd)
            val eur = getString(R.string.eur)
            val kgs = getString(R.string.kgs)
            val kzt = getString(R.string.kzt)
            val bundle = Bundle().apply {
                putString("current", it.base)
                putString(usd, it.rates?.USD.toString())
                putString(eur, it.rates?.EUR.toString())
                putString(kgs, it.rates?.KGS.toString())
                putString(kzt, it.rates?.KZT.toString())
            }
            findNavController().navigate(R.id.action_searchFragment_to_convertFragment, bundle)
        })
    }
}