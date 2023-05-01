package com.coderkube.composepractice.WebService

import com.coderkube.composepractice.Model.MovieDetailModel
import com.coderkube.composepractice.Model.MovieList
import com.coderkube.composepractice.WebService.ApiConstant.MOVIEDETAILS
import com.coderkube.composepractice.WebService.ApiConstant.MOVIELIST
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET(MOVIELIST)
    suspend fun MovieList(
        @Query("api_key") apiKey:String
    ): Response<MovieList>

    @GET(MOVIEDETAILS)
    suspend fun MovieDetails(
        @Path("movie_id") movie_id:String,
        @Query("api_key") apiKey:String
    ): Response<MovieDetailModel>


}