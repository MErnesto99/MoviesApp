package com.example.moviesapp.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import coil.transform.CircleCropTransformation
import com.example.moviesapp.model.Movie

//@Preview
@Composable
fun MovieRow(movie: Movie, onItemClick: (String) -> Unit = {}) {
    var expanded by remember { mutableStateOf(false) }


    Card(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            onItemClick(movie.id)
        }
        .padding(6.dp),
        shape = CircleShape.copy(all = CornerSize(13.dp)),
        elevation = 6.dp) {
        Row(modifier = Modifier.padding(5.dp), verticalAlignment = Alignment.CenterVertically) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
                elevation = 4.dp
            ) {

                val painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.images[0])
                        .size(Size.ORIGINAL)
                        .crossfade(enable = true)
                        .transformations(CircleCropTransformation())
                        .build()
                )

                Image(
                    modifier = Modifier,
                    painter = painter,
                    contentDescription = "movie poster",
                )
            }

            Column(modifier = Modifier.padding(4.dp)) {
                Text(
                    text = "${movie.title}",
                    style = MaterialTheme.typography.h6
                )
                Text(text = "Director: ${movie.director}",
                    style=MaterialTheme.typography.caption)
                Text(text = "Released: ${movie.year}",
                    style=MaterialTheme.typography.caption)

                AnimatedVisibility(visible = expanded) {
                    Column {
                        Text(buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.DarkGray,
                                fontSize = 13.sp)) {
                                append("Plot: ")
                            }
                            withStyle(style = SpanStyle(color = Color.DarkGray,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Light)) {
                                append(movie.plot)
                            }
                        }, modifier = Modifier.padding(6.dp))
                      
                        Divider(modifier = Modifier.padding(3.dp))
                        
                        Text(text = "Director: ${movie.director}", style=MaterialTheme.typography.caption)
                        Text(text = "Actors: ${movie.actors}", style=MaterialTheme.typography.caption)
                        Text(text = "Rating: ${movie.rating}", style=MaterialTheme.typography.caption)
                    }
                }

                Icon(
                    imageVector =
                    if (!expanded)
                        Icons.Filled.KeyboardArrowDown
                    else
                        Icons.Filled.KeyboardArrowUp,
                    contentDescription = "",
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            expanded = !expanded
                        },
                    tint = Color.DarkGray
                )
            }
        }

    }
}