package com.coderkube.composepractice.Activities.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.coderkube.composepractice.R

/**This  file & Function  displays movies detail screen**/
@Composable
fun DisplayMoviesDetails(
    movieId: Int,
    movieName: String,
    releaseDate: String,
    rating: Double,
    Genres: String,
    languages: String,
    production_companies: String,
    description: String,
) {

    /**This composable is parent contianer for dispalying details screen**/
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
            .verticalScroll(rememberScrollState(), true, reverseScrolling = false)
    ) {
        /*This is Image composable to display main background image*/
        Image(
            painter = painterResource(id = R.drawable.androidicon),
            contentDescription = "Background Main",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(shape = CircleShape),
            contentScale = ContentScale.Crop
        )
        /**This composable is parent contianer for dispalying details components**/
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 230.dp)
        ) {
            MOvieDetails(
                rating, releaseDate, Genres, languages, production_companies, description
            )
        }
        /**This composable is parent contianer for dispalying movie image & movie Title**/

        Box(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .height(IntrinsicSize.Max)
                    .width(IntrinsicSize.Max)
                    .offset(x = 60.dp, y = 165.dp)

            ) {
                Row() {
                    /**This is Image composable for movie icon**/
                    Image(
                        painter = painterResource(id = R.drawable.movieicon),
                        contentDescription = "Image_background",
                        Modifier
                            .background(Color.White)
                            .height(120.dp)
                            .width(60.dp)
                            .shadow(8.dp)
                            .clip(shape = RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    /**This is Text composable for displaying movie Name text**/
                    Text(
                        text = movieName,
                        style = MaterialTheme.typography.h1,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(top = 90.dp)
                            .width(200.dp),
                        maxLines = 2

                    )
                }

            }
        }
    }
}


/**This Function is contains design for displaying moviedetail items in moviedetail screen**/

@Composable
fun MOvieDetails(
    rating: Double,
    releaseDate: String,
    Genres: String,
    languages: String,
    production_companies: String,
    description: String
) {
    /**This is container parent to align items vertically**/
    Column(
        Modifier
            .fillMaxSize()
            .padding(all = 8.dp)
            .background(Color.White)
            .padding(top = 50.dp)
    ) {

        /**This is container  to align items horizontally**/

        Row(modifier = Modifier.padding(top = 20.dp)) {

            /**This is spacer composable for applying space**/
            Spacer(modifier = Modifier.width(50.dp))

            /**This is Image composable for like icon**/
            Image(
                painter = painterResource(id = R.drawable.likeicon),
                contentDescription = "Like Icon",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))

            /**This is Text composable for displaying rating text**/
            Text(
                text = rating.toString(),
                style = MaterialTheme.typography.subtitle2,
                fontSize = 15.sp,
                color = Color.LightGray,
            )
            Spacer(modifier = Modifier.width(30.dp))

            /**This is Text composable for displaying releasedate text**/
            Text(
                text = releaseDate,
                style = MaterialTheme.typography.subtitle2,
                fontSize = 18.sp,
                color = Color.Gray,
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row() {
            Spacer(modifier = Modifier.width(10.dp))

            /**This is Text composable for displaying Genres text title**/
            Text(
                text = "Genres :",
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black,
            )
            Spacer(modifier = Modifier.width(5.dp))

            /**This is Text composable for displaying Genres text **/
            Text(
                text = Genres,
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black,
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row() {
            Spacer(modifier = Modifier.width(10.dp))
            /*This is Text composable for displaying Langauges text title*/
            Text(
                text = "Langauges :",
                style = MaterialTheme.typography.subtitle2,
                fontSize = 15.sp,
                color = Color.Gray,
            )
            Spacer(modifier = Modifier.width(5.dp))

            /**This is Text composable for displaying Langauges text **/
            Text(
                text = languages,
                style = MaterialTheme.typography.subtitle2,
                fontSize = 15.sp,
                color = Color.Gray,
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row() {
            Spacer(modifier = Modifier.width(10.dp))

            /**This is Text composable for displaying Production  Companies text title**/
            Text(
                text = "Production  Companies :",
                style = MaterialTheme.typography.subtitle2,
                fontSize = 15.sp,
                color = Color.Gray,
            )
            Spacer(modifier = Modifier.width(5.dp))

            /**This is Text composable for displaying Production  Companies text **/
            Text(
                text = production_companies,
                style = MaterialTheme.typography.subtitle2,
                fontSize = 15.sp,
                color = Color.Gray,
            )
        }
        Spacer(modifier = Modifier.height(40.dp))

        /**This is Text composable for displaying description text **/
        Text(
            text = description,
            style = MaterialTheme.typography.h6,
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier
                .padding(start = 13.dp)
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        )
    }

}

