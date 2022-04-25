package com.pi.horoscopeapp.data


import com.google.gson.annotations.SerializedName

data class HoroscopeNW(
    @SerializedName("date")
    val date: String,
    @SerializedName("horoscope")
    val horoscope: String,
    @SerializedName("sign")
    val sign: String
)