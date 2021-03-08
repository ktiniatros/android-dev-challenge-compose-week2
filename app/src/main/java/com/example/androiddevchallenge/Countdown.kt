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

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@ExperimentalAnimationApi
@Composable
fun countDown(
    timerText: String,
    positioningX: Float = 0.0001f,
    positioningY: Float = 0.0001f,
    timerAlign: TextAlign = TextAlign.Justify,
    color: Color = Color.Black,
    textSize: TextUnit = 100.sp,
    modifier: Modifier
) = Column(modifier = modifier) {

    val colorAnim: Color by animateColorAsState(targetValue = color)

    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(positioningY)
    )

    Row(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {

        Spacer(modifier = Modifier.fillMaxHeight().fillMaxWidth(positioningX))

        AnimatedVisibility(
            visible = timerText.isNotEmpty(),
            modifier = Modifier
                .wrapContentSize()
        ) {
            Text(
                text = timerText,
                color = colorAnim,
                textAlign = timerAlign,
                fontSize = textSize,
            )
        }
    }
} /*ConstraintLayout(modifier = modifier.background(Color.Red)) {

    val colorAnim: Color by animateColorAsState(targetValue = color)

    val countDownText = createRef()

    AnimatedVisibility(
        visible = timerText.isNotEmpty(),
        modifier = Modifier.constrainAs(countDownText) {
            height = androidx.constraintlayout.compose.Dimension.percent(positioningY)
            width = androidx.constraintlayout.compose.Dimension.percent(positioningX)
            centerTo(parent)
        }
    ) {
        Text(
            text = timerText,
            color = colorAnim,
            textAlign = timerAlign
        )
    }


}*/
