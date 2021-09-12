package com.wh.sbw.imclock.ui.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable()
fun ClockNum(text: String, color: Color, fontFamily: FontFamily) {
    Text(
        text = text,
        color = color,
        fontWeight = FontWeight.Bold,
        fontSize = 130.sp,
        fontFamily = fontFamily
    )
}

@Composable
fun FunBtn(text: String, color: Color, fontFamily: FontFamily, modifier: Modifier) {
    Text(
        text = text,
        color = color,
        fontFamily = fontFamily,
        modifier = modifier.padding(all = 4.dp)
    )
}