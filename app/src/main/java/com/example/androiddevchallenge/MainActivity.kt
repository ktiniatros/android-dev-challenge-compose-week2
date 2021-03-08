/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainViewModel(
    private val liveData: MutableLiveData<String> = MutableLiveData<String>(),
    private val timer: CountDownTimer = object : CountDownTimer(30000, 500) {
        override fun onTick(millisUntilFinished: Long) {
            val isHalfWay = millisUntilFinished.toInt() % 1000 < 600
            val nextValueInt = (millisUntilFinished / 1000).toInt()
            val nextValueString = if (nextValueInt < 1) {
                "DONE"
            } else {
                if (isHalfWay) {
                    ""
                } else {
                    "$nextValueInt"
                }
            }
            liveData.postValue(nextValueString)
        }

        override fun onFinish() {
        }
    }
) {

    val countDownData: LiveData<String> = liveData

    fun startCountDown() = timer.start()
}

class MainActivity : AppCompatActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = MainViewModel()

        setContent {
            MyTheme {
                viewModel.countDownData.observeAsState().value?.let {
                    MyApp(it)
                }
            }
        }

        viewModel.startCountDown()
    }
}

// Start building your app here!
@ExperimentalAnimationApi
@Composable
fun MyApp(timeLeft: String) {
    Surface(color = MaterialTheme.colors.background) {
        if (timeLeft.length > 3) {
            countDown(
                timerText = timeLeft,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            )
        } else {
            countDown(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                timerText = timeLeft,
                positioningX = (1..30).random() / 30f,
                positioningY = (1..30).random() / 30f,
                timerAlign = if ((0..1).random() < 1) {
                    TextAlign.Start
                } else {
                    TextAlign.End
                },
                textSize = (30..80).random().sp,
                color = when ((0..10).random()) {
                    1 -> Color.Magenta
                    2 -> Color.DarkGray
                    3 -> Color.Yellow
                    4 -> Color.Blue
                    5 -> Color.Green
//                    6 -> Color.LightGray
                    7 -> Color.Red
                    8 -> Color.Gray
                    9 -> Color.Cyan
                    else -> Color.Black
                }
            )
        }
    }
}

@ExperimentalAnimationApi
@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp(timeLeft = "30")
    }
}

@ExperimentalAnimationApi
@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp(timeLeft = "30")
    }
}
