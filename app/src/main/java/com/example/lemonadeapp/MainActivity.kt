package com.example.lemonadeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonadeapp.ui.theme.LemonadeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeAppTheme {
                // A surface container using the 'background' color from the theme
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonadeApp() {

    var currentPage by remember { mutableStateOf(1)}
    var squeezeCount by remember { mutableStateOf(0)}

    when (currentPage) {
        1 -> {LemonTextAndImage(
            textResource = R.string.tree,
            imageResource = R.drawable.lemon_tree,
            contentDescriptionResource = R.string.content_description1,
            onImageClick = {
                currentPage = 2
                squeezeCount = (2..4).random()
            }
        )}

        2 -> {LemonTextAndImage(
            textResource = R.string.lemon,
            imageResource = R.drawable.lemon_squeeze,
            contentDescriptionResource = R.string.content_description2,
            onImageClick = {
                squeezeCount--

                if (squeezeCount == 0) {
                    currentPage = 3
                }
            }
        )}

        3 -> {LemonTextAndImage(
            textResource = R.string.drink_lemonade,
            imageResource = R.drawable.lemon_drink,
            contentDescriptionResource = R.string.content_description3,
            onImageClick = {
                currentPage = 4
            }
        )}

        4 -> {LemonTextAndImage(
            textResource = R.string.empty_glass,
            imageResource = R.drawable.lemon_restart,
            contentDescriptionResource = R.string.content_description4,
            onImageClick = {
                currentPage = 1
            }
        )}
    }
}

@Composable
fun LemonTextAndImage(
    textResource: Int,
    imageResource: Int,
    contentDescriptionResource: Int,
    onImageClick: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()) {
        
        Text(
            text = stringResource(textResource),
            fontSize = 18.sp)
        
        Spacer(
            modifier = Modifier.height(16.dp))
        
        Image(
            painter = painterResource(imageResource),
            contentDescription = stringResource(contentDescriptionResource),
            modifier = Modifier
                .wrapContentSize()
                .clickable(onClick = onImageClick)
                .border(BorderStroke(2.dp, Color(105, 205, 216)))
                .padding(16.dp))
    }
    
    
    
    
}

@Preview(
    name = "Lemonade Application",
    showBackground = true,
    showSystemUi = true)

@Composable
fun DefaultPreview() {
    LemonadeAppTheme {
        LemonadeApp()
    }
}