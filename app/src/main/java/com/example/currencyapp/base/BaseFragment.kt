package com.example.currencyapp.base

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.currencyapp.R

abstract class BaseFragment<Binding : ViewBinding> : Fragment() {

    private lateinit var progressDialog: AlertDialog
    lateinit var binding: Binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLoader()
    }

    private fun setupLoader() {
        progressDialog = AlertDialog.Builder(requireContext())
            .setView(LayoutInflater.from(requireContext()).inflate(R.layout.view_progress, null))
            .setCancelable(false)
            .create()

        progressDialog.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                progressDialog.dismiss()
            }
            return@setOnKeyListener true
        }
    }

    open fun setIsLoading(value: Boolean) {
        if (value) showLoading() else hideLoading()
    }

    open fun showLoading() {
        when (requireActivity()) {
            is BaseActivity<*> -> (requireActivity() as BaseActivity<*>).showLoading()
            else -> progressDialog.show()
        }
    }

    open fun hideLoading() {
        (requireActivity() as? BaseActivity<*>)?.hideLoading()
        progressDialog.dismiss()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getViewBinding(inflater, container)
        return binding.root
    }

    abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): Binding
}