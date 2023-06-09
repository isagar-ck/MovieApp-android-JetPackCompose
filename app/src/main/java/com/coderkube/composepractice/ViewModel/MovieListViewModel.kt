package com.coderkube.composepractice.ViewModel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.coderkube.composepractice.Model.MovieList
import com.coderkube.composepractice.R
import com.coderkube.composepractice.Repositories.MovieListRepository
import com.coderkube.composepractice.Utils.utils
import com.coderkube.composepractice.App
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

//TODO MovieListViewModel
class MovieListViewModel(application: Application) : AndroidViewModel(application) {

    val MovieListResponse = MutableLiveData<ArrayList<MovieList.Result>>()
    var MovieListResponsenonlive: List<MovieList.Result> by mutableStateOf(listOf())
    val errorMessage = MutableLiveData<String>()
    var movieModel: MovieList? = null

    /**
     * this function is use for Api calling and check response
     * */
    fun MovieListCall() {
        if (utils.isConnect(getApplication())) {

            CoroutineScope(Dispatchers.IO).launch {
                val response = MovieListRepository.MovieListSelect(utils.api_key)

                withContext(Dispatchers.Main) {


                    movieModel = response.body()

                    if (response.isSuccessful) {
                        when (response.code()) {
                            200 -> {
                                if (response.body() != null) {
                                    MovieListResponse.value = movieModel?.results
                                    MovieListResponsenonlive = movieModel?.results!!
                                    // Log.e("Apicheck", "MovieListCall: ${MovieListResponse.value}", )
                                }
                            }
                        }
                    } else
                        when (response.code()) {
                            401 -> {
                                try {
                                    val errorBody = response.errorBody()!!.string()
                                    val gson = Gson()

                                    movieModel = gson.fromJson(errorBody, MovieList::class.java)
                                    if (errorBody.contains("status_message")) {
                                        setError(movieModel!!.statusMessage)
                                    }
                                } catch (e: IOException) {
                                    e.printStackTrace()
                                }
                            }
                            500 -> setError(R.string.server_error)
                            else -> setError(R.string.something_went_wrong)
                        }
                }
            }
        }
    }

    /**
     * this function is use for check error
     * */
    private fun setError(text: Any) {
        if (text is Int)
            errorMessage.postValue(getApplication<App>().getString(text))
        else
            errorMessage.postValue(text.toString())
    }
}