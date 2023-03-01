package com.example.currencyapp

import com.example.currencyapp.api.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Repo @Inject constructor(private val api: Api) {

    suspend fun fetchCurrency() = withContext(Dispatchers.IO) {
        api.getCurrencies()
    }

    suspend fun fetchCurrency(to: String, from: String, amount: String) =
        withContext(Dispatchers.IO) {
            api.convertCurrency(to, from, amount)
        }
}