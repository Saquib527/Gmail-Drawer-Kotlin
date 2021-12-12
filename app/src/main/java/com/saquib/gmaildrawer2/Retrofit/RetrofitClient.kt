package com.saquib.gmaildrawer2.Retrofit

import retrofit2.Retrofit
import com.saquib.gmaildrawer2.Retrofit.RetrofitClient
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    //GetDataService dataService = RetrofitInstance.getRetrofit().create(GetDataService.class);
    var retrofit: Retrofit? = null
        get() {
            if (field == null) {
                field = Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(BASE_URL)
                        .build()
            }
            return field
        }
        private set
    private const val BASE_URL = "https://api.foursquare.com/v2/"
}