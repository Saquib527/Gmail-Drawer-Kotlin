package com.saquib.gmaildrawer2.Retrofit

import com.saquib.gmaildrawer2.Model.ResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {
    @GET("venues/search")
    fun getResponse(
            @Query("ll") latlng: String?,
            @Query("oauth_token") token: String?,
            @Query("v") version: String?
    ): Call<ResponseModel.Root?>?
}