package com.example.currencyapp.model

import com.google.gson.annotations.SerializedName


data class Query (

  @SerializedName("amount" ) var amount : Int?    = null,
  @SerializedName("from"   ) var from   : String? = null,
  @SerializedName("to"     ) var to     : String? = null

)