package com.demo.demotask.remote.services

import com.demo.demotask.local.model.MandiDataResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MandiDataService {

    @GET("9ef84268-d588-465a-a308-a864a43d0070")
    suspend fun getMandiData(
        @Query("api-key") apiKey:String,
        @Query("format") format:String,
        @Query("offset") offset:Int,
        @Query("limit") limit:Int
    ): MandiDataResponse

}