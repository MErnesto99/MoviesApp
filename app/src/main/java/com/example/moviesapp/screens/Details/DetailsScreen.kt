package com.example.moviesapp.screens.Details

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.moviesapp.Utils.AppBar1
import com.example.moviesapp.model.Movie
import com.example.moviesapp.navigation.MovieScreens
import com.example.moviesapp.widgets.MovieRow


//@Preview
@Composable
fun DetailsScreen(navController: NavController, movieData: String?,mList:List<Movie>) {
    Scaffold(topBar = {
        AppBar1(title = "Details Screen", Icons.Default.ArrowBack) {
            navController.popBackStack()
        }
    }) {

        MainContent(navController = navController, movieData,mList)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainContent(navController: NavController, movieId: String?,mList:List<Movie>) {
    val newMovieList = mList.filter { movie ->
        movie.id == movieId
    } //This will return a movie when the condition matches.
    Surface(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            //  .first metgod gets the
            MovieRow(newMovieList.first())
            Spacer(modifier = Modifier.height(8.dp))
            Divider()
            Text(text = "Movie Images")
            Row(modifier = Modifier.fillMaxSize()) {

                val pagerState= rememberPagerState()

                Box(modifier = Modifier.fillMaxSize()) {
                    HorizontalPager(
                        pageCount = newMovieList.first().images.size,
                        state = pagerState,
                        key = {
                            newMovieList.first().images[it]
                        }
                    ) {index->

                        AsyncImage(
                            model = newMovieList.first().images[index],
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                                .clickable {
                                    navController.navigate(MovieScreens.ImageViewerScreen.name+"/$movieId")
                                })
                        
                    }
                }
                
//                LazyRow(modifier = Modifier.fillMaxSize()) {
//                    ///// LazyRow Is a list to show in rows
//                    items(newMovieList.first().images) { image ->
//                        Box(
//                            modifier = Modifier
//                                .padding(12.dp),
//                        ) {
//
//                            val painter = rememberAsyncImagePainter(
//                                model = ImageRequest.Builder(LocalContext.current)
//                                    .data(image)
//                                    .size(Size.ORIGINAL)
//                                    .crossfade(enable = true)
//                                    .build()
//                            )
//
//                            Image(
//                                modifier = Modifier,
//                                painter = painter,
//                                contentScale = ContentScale.None,
//                                contentDescription = "movie poster",
//                            )
//                        }
//                    }
//                }
            }

        }
    }

}

