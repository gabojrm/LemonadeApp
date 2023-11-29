package com.example.lemonadeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonadeapp.ui.theme.LemonadeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeAppTheme {
                LemonadeApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MadeALemonade(
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                modifier = modifier,
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors
                    (
                    containerColor = Color(0xFFF9E44C),
                    titleContentColor = Color.Black,
                ),
                title = {
                    Text(
                        fontWeight = FontWeight.Bold,
                        modifier = modifier,
                        textAlign = TextAlign.Center,
                        text = stringResource(id = R.string.app_name)
                    )
                })
        }
    ) { innerPadding ->
        BodyLemonade(modifier, innerPadding)
    }
}

@Composable
fun BodyLemonade(modifier: Modifier, innerPadding: PaddingValues) {
    var result by remember { mutableStateOf(1) }

    val imgButton = when (result) {
        1 -> R.drawable.lemon_tree
        in 2..4 -> R.drawable.lemon_squeeze
        5 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val textStep = when (result) {
        1 -> R.string.tap_lemon
        in 2..4 -> R.string.tap_squeeze
        5 -> R.string.tap_drink
        else -> R.string.tap_empty
    }

    Column(
        modifier = modifier
            .padding(innerPadding)
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            modifier = modifier,
            shape = RoundedCornerShape(10),
            colors = ButtonDefaults.buttonColors(Color(0xFFC3ECD2)),
            onClick = {
                if (result < 2) result++
                else if (result in 2..4) result = (2..5).random()
                else if (result < 6) result++
                else result = 1
            }
        ) {
            Image(
                modifier = modifier.padding(25.dp),
                painter = painterResource(id = imgButton),
                contentDescription = "$result"
            )
        }
        Spacer(modifier = modifier.height(16.dp))
        Text(
            fontSize = 18.sp,
            modifier = modifier.padding(16.dp),
            text = stringResource(id = textStep)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    MadeALemonade()
}
