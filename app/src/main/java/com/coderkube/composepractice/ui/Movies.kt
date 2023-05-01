package com.coderkube.composepractice.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.coderkube.composepractice.Model.MovieList
import com.coderkube.composepractice.R

/**This  file & Function  displays movies screen**/


@Composable
fun MovieList(movieList: List<MovieList.Result>, navController: NavController) {


    /**This  Composable creates a lists  of multiple movieitems**/
    LazyColumn {
        items(movieList) { movie ->
            ShowMovies(movie, navController)
        }
    }
}


/**This  Function displays movies screen and it is container for all movies composables**/
@Composable
fun ShowMovies(movies: MovieList.Result, navController: NavController) {

    /**This  composable is used to create a card container for items**/
    Card(
        shape = RectangleShape,
        elevation = 6.dp,
        backgroundColor = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(bottom = 5.dp)
            .clickable {
                navController.navigate("detail_screen/${movies.id}/${movies.releaseDate}/${movies.originalTitle}")
            }
    )
    {
        /**This composable is parent contianer for dispalying  movies**/
        Box(modifier = Modifier.padding(all = 8.dp))
        {
            /**This is container parent to align items vertically**/
            Column() {
                /**This is container  to align items horizontally**/
                Row() {
                    Spacer(modifier = Modifier.height(10.dp))

                    /**This is Image composable for movie image**/
                    Image(
                        painter = painterResource(id = R.drawable.androidicon),
                        contentDescription = "Movie Image",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(  RoundedCornerShape(4.dp))
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column() {

                        /**This is Text composable for displaying movies title text**/
                        Text(
                            text = movies.originalTitle,
                            style = MaterialTheme.typography.h2,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(5.dp)
                        )
                        /**This is Text composable for displaying releasedate text**/
                        Text(
                            text = movies.releaseDate,
                            style = MaterialTheme.typography.subtitle1,
                            fontSize = 18.sp,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(5.dp)
                        )
                        Row() {
                            /**This is Image composable for like icon**/
                            Image(
                                painter = painterResource(id = R.drawable.likeicon),
                                contentDescription = "Like Icon",
                                modifier = Modifier
                                    .size(24.dp)
                            )
                            /**This is Text composable for displaying rating text**/
                            Text(
                                text = movies.voteAverage.toString(),
                                style = MaterialTheme.typography.subtitle2,
                                fontSize = 15.sp,
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(5.dp)
                            )
                        }

                    }
                }
                Spacer(modifier = Modifier.height(10.dp))

                /**This is Text composable for displaying description text **/
                Text(
                    text = movies.overview,
                    style = MaterialTheme.typography.h6,
                    fontSize = 12.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth()
                )
            }


        }

    }
}