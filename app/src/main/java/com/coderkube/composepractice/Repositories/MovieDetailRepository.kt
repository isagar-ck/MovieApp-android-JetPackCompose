package com.coderkube.composepractice.Repositories

import com.coderkube.composepractice.WebService.ApiClient
import com.coderkube.composepractice.requestModel.MovieDetailRequestModel

//TODO MovieDetailRepository
object MovieDetailRepository {

    suspend fun MovieDetails(movieDetailRequestModel: MovieDetailRequestModel, apikey: String) = ApiClient.getClient().MovieDetails(
        movieDetailRequestModel.MovieID!!,
        apikey
    )
}