package com.ashish.syfapplication.repository


import com.ashish.syfapplication.model.ImageResponse
import com.ashish.syfapplication.model.NetworkState
import com.ashish.syfapplication.network.CatService


class CatRepository constructor(private val service: CatService) {



    suspend fun getAllImages(): NetworkState<List<ImageResponse>> {
        val response = service.searchImages()
        return if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) {
                NetworkState.Success(responseBody)
            } else {
                NetworkState.Error(response)
            }
        } else {
            NetworkState.Error(response)
        }
    }
}