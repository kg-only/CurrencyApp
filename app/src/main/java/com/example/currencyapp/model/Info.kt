package com.example.currencyapp.model

import com.google.gson.annotations.SerializedName


data class Info (

  @SerializedName("rate"      ) var rate      : Double? = null,
  @SerializedName("timestamp" ) var timestamp : Int?    = null

)