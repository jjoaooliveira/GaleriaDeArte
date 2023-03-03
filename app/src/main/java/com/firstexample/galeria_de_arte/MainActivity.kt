package com.firstexample.galeria_de_arte

import android.app.Activity
import android.app.ActivityOptions
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.firstexample.galeria_de_arte.ui.theme.Galeria_de_ArteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Galeria_de_ArteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    GalleryScreen()
                }
            }
        }
    }
}

private fun switchBetweenImages(ActualImage: Int): Int {
     val imgSelect = when(ActualImage) {
         1 -> R.drawable.monstercat_blue
         2 -> R.drawable.monstercat_red
         3 -> R.drawable.monstercat_purple
         else -> R.drawable.monstercat_dual
    }
    return imgSelect
}
private fun switchBetweenImagesData(ActualImage: Int): Int {
    val imgSelectData = when(ActualImage) {
        1 -> R.string.monstercat_blue
        2 -> R.string.monstercat_red
        3 -> R.string.monstercat_purple
        else -> R.string.monstercat_dual
    }
    return imgSelectData
}

@Composable
fun ImageHandler(ActualImage: Int) {
    val imgPainter = switchBetweenImages(ActualImage)

    Surface(
        border = BorderStroke(4.dp, color = Color.DarkGray),
        elevation = 10.dp
    ) {
        Image(
            painter = painterResource(id = imgPainter),
            contentDescription = null,
            modifier = Modifier
                .padding(30.dp)
                .size(280.dp)

        )
    }
}

@Composable
fun ImageInfo(ActualImage: Int) {
    val picSubtitle = switchBetweenImagesData(ActualImage)

    val picSubtitleData = when(ActualImage) {
        1 -> R.string.monstercat_blue_data
        2 -> R.string.monstercat_red_data
        3 -> R.string.monstercat_purple_data
        else -> R.string.monstercat_dual_data
    }

    Surface(
        elevation = 10.dp,
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = stringResource(id = picSubtitle),
                fontSize = 27.sp,
                fontWeight = FontWeight.Light
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.monstercat),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(text = stringResource(id = picSubtitleData))
            }
        }
    }
}

@Composable
fun GalleryScreen() {
    var actualImage by remember { mutableStateOf(1) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        ImageHandler(actualImage)

        Spacer(modifier = Modifier.height(100.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ImageInfo(actualImage)
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        if(actualImage == 1) {
                            actualImage = 4
                        } else {
                            actualImage--
                        }
                    },
                    modifier = Modifier
                        .width(130.dp)
                ) {
                    Text("Previous")
                }

                Button(
                    onClick = {
                        if(actualImage < 4) {
                            actualImage++
                        } else {
                            actualImage = 1
                        }
                    },
                    modifier = Modifier
                        .width(130.dp)
                ) {
                    Text("Next")
                }
            }
        }
    }
}

@Preview(showBackground = false, showSystemUi = true)
@Composable
fun DefaultPreview() {
    Galeria_de_ArteTheme {
        GalleryScreen()
    }
}

