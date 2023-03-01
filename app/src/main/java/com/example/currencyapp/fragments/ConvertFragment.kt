package com.example.currencyapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.currencyapp.EventObserver
import com.example.currencyapp.R
import com.example.currencyapp.VM
import com.example.currencyapp.base.BaseFragment
import com.example.currencyapp.databinding.FragmentConvertBinding


class ConvertFragment : BaseFragment<FragmentConvertBinding>() {

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentConvertBinding.inflate(inflater, container, false)

    private val vm by activityViewModels<VM>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            val currentCurrency = requireArguments().getString("current")
            val usdKey = getString(R.string.usd)
            val eurKey = getString(R.string.eur)
            val kgsKey = getString(R.string.kgs)
            val kztKey = getString(R.string.kzt)
            binding.info.text = getString(R.string.current_currency, currentCurrency)
            binding.usdValue.text = requireArguments().getString(usdKey)
            binding.eurValue.text = requireArguments().getString(eurKey)
            binding.kgsValue.text = requireArguments().getString(kgsKey)
            binding.kztValue.text = requireArguments().getString(kztKey)
        }
        initAll()
        initObservers()
    }

    private fun initAll() = with(binding) {
        val currencies = arrayOf("USD", "EUR", "KGS", "KZT")
        val arrayAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, currencies)
        spinnerFrom.adapter = arrayAdapter
        spinnerTo.adapter = arrayAdapter
        spinnerFrom.setSelection(0)
        spinnerTo.setSelection(0)

        var currencyFrom = ""
        var currencyTo = ""

        spinnerFrom.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                currencyFrom = currencies[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        spinnerTo.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                currencyTo = currencies[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        convert.setOnClickListener {
            if (sumFrom.text.isBlank() && currencyFrom.isBlank() && currencyTo.isBlank())
                Toast.makeText(requireContext(), "Заполните поля", Toast.LENGTH_SHORT).show()
            else
                vm.convert(currencyTo, currencyFrom, sumFrom.text.toString())
        }
    }

    private fun initObservers() {
        vm.convertResponse.observe(viewLifecycleOwner, EventObserver {
            binding.sumTo.setText(it.result.toString())
        })

        vm.loadingState.observe(viewLifecycleOwner) { setIsLoading(it) }
    }
}