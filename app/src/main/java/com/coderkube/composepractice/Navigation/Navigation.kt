package com.coderkube.composepractice.Navigation

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.coderkube.composepractice.Activities.ui.theme.DisplayMoviesDetails
import com.coderkube.composepractice.Model.MovieDetailModel
import com.coderkube.composepractice.Model.MovieList
import com.coderkube.composepractice.R
import com.coderkube.composepractice.Utils.Constants.ROUTE_DETAILSCREEN
import com.coderkube.composepractice.Utils.Constants.ROUTE_MAINSCREEN
import com.coderkube.composepractice.ViewModel.MovieDetailViewModel
import com.coderkube.composepractice.ViewModel.MovieListViewModel
import com.coderkube.composepractice.ui.MovieList

/**This  Function is used to navigate between our all composables **/
@Composable
fun Navigation(context: Context) {

    val movieViewModel: MovieListViewModel = viewModel()

    movieViewModel.MovieListCall()
    val movieDetailViewMOdel: MovieDetailViewModel = viewModel()

    var isDetailScreeen by remember {
        mutableStateOf(false)
    }
    val baseTitle = "Movies App"
    val (title, setTitle) = remember { mutableStateOf(baseTitle) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        TopAppBar(
            backgroundColor = Color.White, elevation = 8.dp, modifier = Modifier.fillMaxWidth()
        ) {
            Box(Modifier.height(102.dp)) {
                Row(
                    Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically
                ) {

                    ProvideTextStyle(value = MaterialTheme.typography.h6) {
                        CompositionLocalProvider(
                            LocalContentAlpha provides ContentAlpha.high,
                        ) {

                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Center,
                                maxLines = 1,
                                text = title
                            )
                        }
                    }
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 60.dp)
                .background(Color.White)
        ) {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = ROUTE_MAINSCREEN) {
                composable(Screen.MainScreen.route) {
                    isDetailScreeen = false
                    Log.e("TAG", "Conversation: check data 2 : ${movieViewModel.MovieListResponsenonlive} ")
                    MainScreen(
                        navController = navController,
                        movieViewModel.MovieListResponsenonlive,
                        setTitle
                    )
                }
                composable(
                    route = "detail_screen/{movieId}/{releaseDate}/{orignalTitle}",
                    arguments = listOf(navArgument("movieId") {
                        type = NavType.IntType
                    }, navArgument("releaseDate") {
                        type = NavType.StringType
                    }, navArgument("orignalTitle") {
                        type = NavType.StringType
                    })
                ) { backStackEntry ->

                    DetailScreen(
                        context,
                        backStackEntry.arguments?.getInt("movieId")!!,
                        backStackEntry.arguments?.getString("releaseDate")!!,
                        backStackEntry.arguments?.getString("orignalTitle")!!,
                        setTitle,
                        movieDetailViewMOdel
                    )
                }
            }
        }
    }


}

/**This  Function is used to display our main movies screen **/
@Composable
fun MainScreen(
    navController: NavController, movieList: List<MovieList.Result>, setTitle: (String) -> Unit
) {
    setTitle(stringResource(R.string.movies_demo))
    Log.e("TAG", "Conversation: check data 1 : ${movieList} ")
    MovieList(movieList, navController)
}

/**This  Function is used to display our  moviesDetails screen **/
@Composable
fun DetailScreen(
    context: Context,
    id: Int,
    releaseDate: String,
    orignalTitle: String,
    setTitle: (String) -> Unit,
    movieDetailViewModel: MovieDetailViewModel
) {
    val movieDetail: State<MovieDetailModel> = movieDetailViewModel.moviePro.observeAsState(MovieDetailModel())

    movieDetail.value.let {
        LaunchedEffect(id) {
            movieDetailViewModel.MovieID.value = id.toString()
            movieDetailViewModel.MovieDetailCall()
            setTitle(orignalTitle)
        }
        if (movieDetailViewModel.MovieDetailResponse.value != null) {
            var genresList: ArrayList<String> = ArrayList()
            var movieLanguageList: ArrayList<String> = ArrayList()
            var movieProductionList: ArrayList<String> = ArrayList()

            for (i in 0..movieDetailViewModel.MovieDetailResponse.value!!.genres.size - 1) {
                genresList.add(movieDetailViewModel.MovieDetailResponse.value!!.genres.get(i).name)
            }
            for (i in 0..movieDetailViewModel.MovieDetailResponse.value!!.spokenLanguages.size - 1) {
                movieLanguageList.add(movieDetailViewModel.MovieDetailResponse.value!!.spokenLanguages.get(i).name)
            }

            for (i in 0..movieDetailViewModel.MovieDetailResponse.value!!!!.productionCompanies.size - 1) {
                movieProductionList.add(movieDetailViewModel.MovieDetailResponse.value!!.productionCompanies.get(i).name)
            }

            movieDetailViewModel.movieGenres.value =
                genresList.toString().replace("[", "").replace("]", "")

            movieDetailViewModel.movieLanguages.value =
                movieLanguageList.toString().replace("[", "").replace("]", "")

            movieDetailViewModel.movieProduction.value =
                movieProductionList.toString().replace("[", "").replace("]", "")

            val rating = movieDetailViewModel.MovieDetailResponse.value!!.voteAverage
            val description = movieDetailViewModel.MovieDetailResponse.value!!.overview
            val Genres = movieDetailViewModel.movieGenres.value!!
            val Production = movieDetailViewModel.movieProduction.value!!
            val languages = movieDetailViewModel.movieLanguages.value!!

            DisplayMoviesDetails(
                id,
                orignalTitle,
                releaseDate,
                rating,
                Genres,
                languages,
                Production,
                description,
            )
        } else {
            // Toast.makeText(context, "Api Not Called yet", Toast.LENGTH_SHORT).show()
        }
    }


}

/**This  class is used for routing between composables **/
sealed class Screen(val route: String) {
    object MainScreen : Screen(ROUTE_MAINSCREEN)
    object DetailScreen : Screen(ROUTE_DETAILSCREEN)

}