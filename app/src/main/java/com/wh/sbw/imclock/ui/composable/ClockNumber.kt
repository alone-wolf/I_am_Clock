package com.wh.sbw.imclock.ui.composable

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp

@Composable
fun ClockNumHour(text: String, color: Color, fontFamily: FontFamily) {
    ClockNum(text = text, color = color, fontFamily = fontFamily)
}

@Composable
fun ClockNumMin(text: String, color: Color, fontFamily: FontFamily) {
    ClockNum(text = text, color = color, fontFamily = fontFamily)
}

@Composable
fun ClockNumWeek(text: String, color: Color, fontFamily: FontFamily) {
    Text(
        text = text,
        color = color,
        fontSize = 30.sp,
        fontFamily = fontFamily
    )
}