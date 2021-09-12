package com.wh.sbw.imclock.util

import androidx.compose.ui.text.font.FontFamily

val fontMap = mapOf(
    Pair("Default", FontFamily.Default),
    Pair("Monospace", FontFamily.Monospace),
    Pair("Serif", FontFamily.Serif),
    Pair("SansSerif", FontFamily.SansSerif),
    Pair("Cursive", FontFamily.Cursive)
)


val fontList = listOf(
    FontFamily.Monospace,
    FontFamily.Serif,
    FontFamily.SansSerif,
    FontFamily.Cursive,
    FontFamily.Default
)

fun switchFont(name: Int): FontFamily {
    return when (name % 4) {
        0 -> FontFamily.Monospace
        1 -> FontFamily.Serif
        2 -> FontFamily.SansSerif
        3 -> FontFamily.Cursive
        else -> FontFamily.Default
    }
}