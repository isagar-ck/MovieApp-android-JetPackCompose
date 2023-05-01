package com.coderkube.composepractice.Activities


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.coderkube.composepractice.Activities.ui.theme.DisplayMoviesDetails
import com.coderkube.composepractice.Navigation.Navigation
import com.coderkube.composepractice.ui.theme.ComposePracticeTheme

class MoviesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**This  Function is used to display all the screen and design content **/
        setContent {
            ComposePracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background

                ) {
                    Column {
                        /**This  Function is used to navigate between our all composables **/
                        Navigation(applicationContext)
                    }
                }
            }
        }
    }


}

@Preview(name = "Light Mode")
@Composable
fun DefaultPreview() {
    ComposePracticeTheme {
        Column {
//            Navigation(LocalContext.current)
//            movieId: Int,
//            movieName: String,
//            releaseDate: String,
//            rating: Double,
//            Genres: String,
//            languages: String,
//            production_companies: String,
//            description: String,
            DisplayMoviesDetails(
                movieId = 1,
                movieName = "ABC",
                releaseDate = "22-09-2022",
                rating = 4.5,
                Genres = "ab ,cd",
                languages = "hindi, gujarati",
                production_companies = "abcd",
                description = "dshjkfgjkashfajksldfgadsa"
            )

        }

    }
}