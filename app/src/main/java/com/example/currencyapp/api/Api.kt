package com.example.currencyapp.api

import com.example.currencyapp.model.ConvertResponseModel
import com.example.currencyapp.model.CurrencyResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("latest")
    suspend fun getCurrencies(): CurrencyResponseModel

    @GET("convert")
    suspend fun convertCurrency(
        @Query("to") to: String,
        @Query("from") from: String,
        @Query("amount") amount: String,
    ): ConvertResponseModel
}