package com.ashish.syfapplication.network


import com.ashish.syfapplication.model.ImageResponse
import com.ashish.syfapplication.model.constant.Constants
import retrofit2.Response
import retrofit2.http.*

interface CatService {

    @GET("/v1/images/search")
    suspend fun searchImages(
        @Query("limit") limit: Int = Constants.PAGE_SIZE,
        @Query("order") order: String = "random",
        @Query("breed_ids") breedId: String = "beng",
        @Query("api_key") key: String = Constants.API_KEY
    ): Response<List<ImageResponse>>


}