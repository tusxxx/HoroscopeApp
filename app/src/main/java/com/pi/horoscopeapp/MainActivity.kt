package com.pi.horoscopeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pi.horoscopeapp.ui.theme.HoroscopeAppTheme

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            HoroscopeAppTheme {
                Scaffold(topBar = { ForecastToolbar() }) {
                    ForecastScreen()
                }
            }
        }
    }

    @Composable
    fun ForecastToolbar() {
        val currentSign = viewModel.currentSign.collectAsState()

        TopAppBar(
            title = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "HoroscopeForecast App")
                    Text(
                        text = currentSign.value,
                        modifier = Modifier.clickable { viewModel.changeSign() })
                }
            }
        )
    }

    @Composable
    private fun ForecastScreen() {
        val forecast = viewModel.forecast.collectAsState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .clip(MaterialTheme.shapes.medium)
                .background(Color.LightGray),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxWidth()
                    .animateContentSize()
                    .clip(MaterialTheme.shapes.medium)
                    .background(Color.Gray)
            ) {
                Text(text = forecast.value, style = TextStyle.Default.copy(fontSize = 18.sp))
            }
            Button(
                onClick = { viewModel.getForecast() }) {
                Text(text = "GET FORECAST", style = TextStyle(Color.White, fontSize = 24.sp))
            }
        }
    }
}
