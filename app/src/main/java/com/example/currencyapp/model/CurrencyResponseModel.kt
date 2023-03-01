package com.example.currencyapp.model


data class CurrencyResponseModel(

    var base: String,
    var rates: Rates? = Rates()

)