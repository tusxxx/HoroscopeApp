package com.pi.horoscopeapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pi.horoscopeapp.data.HoroscopeAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel() {
    private val _forecast = MutableStateFlow("")
    val forecast: StateFlow<String> get() = _forecast.asStateFlow()

    private val signList = listOf(
        "aquarius",
        "pisces",
        "aries",
        "taurus",
        "gemini",
        "cancer",
        "leo",
        "virgo",
        "libra",
        "scorpio",
        "sagittarius",
        "capricorn"
    )
    private val _currentSign = MutableStateFlow(signList.first())
    val currentSign: StateFlow<String> = _currentSign.asStateFlow()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://ohmanda.com/api/horoscope/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(HoroscopeAPI::class.java)

    fun getForecast() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = retrofit.getHoroscope(_currentSign.value)
                _forecast.emit(result.horoscope)
            } catch (exception: Exception) {
                Log.e("ERROR", exception.toString())
            }
        }
    }

    fun changeSign() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _currentSign.emit(signList[signList.indexOf(_currentSign.value)+1])
            } catch (exception: Exception) {
                _currentSign.emit(signList.first())
            }
        }
    }
}

