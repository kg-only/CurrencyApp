package com.example.currencyapp

import android.os.Bundle
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.currencyapp.base.BaseActivity
import com.example.currencyapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    /*    override fun onSupportNavigateUp(): Boolean {
            return findNavController(R.id.fragment_container_view).navigateUp(appBarConfiguration)
        }*/
    override fun onBackPressed() {
        if (findNavController(this, R.id.fragment_container_view).popBackStack().not()) {
            //Last fragment: Do your operation here
            finish()
        }
    }
}