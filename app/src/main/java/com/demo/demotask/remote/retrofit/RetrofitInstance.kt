package com.demo.demotask.remote.retrofit

import com.demo.demotask.remote.services.MandiDataService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val baseUrl = "https://api.data.gov.in/resource/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build();
    }

    fun getMandiService(): MandiDataService {
        return retrofit.create(MandiDataService::class.java);
    }

}