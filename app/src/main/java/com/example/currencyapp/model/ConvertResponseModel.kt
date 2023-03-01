package com.example.currencyapp.model

import com.google.gson.annotations.SerializedName


data class ConvertResponseModel (

  @SerializedName("date"       ) var date       : String?  = null,
  @SerializedName("historical" ) var historical : String?  = null,
  @SerializedName("info"       ) var info       : Info?    = Info(),
  @SerializedName("query"      ) var query      : Query?   = Query(),
  @SerializedName("result"     ) var result     : Double?  = null,
  @SerializedName("success"    ) var success    : Boolean? = null

)