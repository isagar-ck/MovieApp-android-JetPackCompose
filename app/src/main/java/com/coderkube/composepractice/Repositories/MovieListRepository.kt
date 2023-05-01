package com.coderkube.composepractice.Repositories

import com.coderkube.composepractice.WebService.ApiClient

object MovieListRepository {

    suspend fun MovieListSelect(apikey: String) = ApiClient.getClient().MovieList(
        apikey
    )
}
