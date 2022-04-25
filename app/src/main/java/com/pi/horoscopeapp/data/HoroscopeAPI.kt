package com.pi.horoscopeapp.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HoroscopeAPI {
    @GET("{sign}")
    suspend fun getHoroscope(
        @Path("sign") sign: String
    ): HoroscopeNW
}