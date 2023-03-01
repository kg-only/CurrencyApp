package com.example.currencyapp.base

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.currencyapp.R

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    private lateinit var progressDialog: AlertDialog
    open fun setIsLoading(value: Boolean) {
        if (value) showLoading() else hideLoading()
    }

    open fun showLoading() {
        progressDialog.show()
    }

    open fun hideLoading() {
        progressDialog.dismiss()
    }

    lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        setupLoader()
    }

    abstract fun getViewBinding(): VB

    @SuppressLint("InflateParams")
    private fun setupLoader() {
        progressDialog = AlertDialog.Builder(this)
            .setView(LayoutInflater.from(this).inflate(R.layout.view_progress, null))
            .setCancelable(false)
            .create()
        progressDialog.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                progressDialog.dismiss()
                finish()
            }
            return@setOnKeyListener true
        }
        progressDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onDestroy() {
        progressDialog.dismiss()
        super.onDestroy()
    }
}