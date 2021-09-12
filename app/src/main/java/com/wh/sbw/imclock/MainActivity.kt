package com.wh.sbw.imclock

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.wh.sbw.imclock.ui.composable.ClockNumHour
import com.wh.sbw.imclock.ui.composable.ClockNumMin
import com.wh.sbw.imclock.ui.composable.ClockNumWeek
import com.wh.sbw.imclock.ui.composable.FunBtn
import com.wh.sbw.imclock.ui.theme.ImClockTheme

class MainActivity : BaseFullScreenActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ImClockTheme(theme = clockViewModel.selectedTheme) {
                Surface(
                    modifier = Modifier
                        .background(color = MaterialTheme.colors.background)
                        .clickable {
                            clockViewModel.isFullScreen = !clockViewModel.isFullScreen
                        }) {
//                    Image(bitmap = ImageBitmap.imageResource(id = R.drawable.xxx), contentDescription ="",modifier = Modifier.fillMaxSize() )
                    if (clockViewModel.isLandScape) {
                        ClockFaceLand(
                            hour = clockViewModel.getFormattedHour(),
                            min = clockViewModel.getFormattedMin(),
                            week = clockViewModel.getWeekDate(resources),
                            use24Format = clockViewModel.use24Format,
                            isFullScreen = clockViewModel.isFullScreen,
                            isRotateLocked = clockViewModel.isRotateLocked,
                            color = MaterialTheme.colors.primary,
                            fontFamily = clockViewModel.getFontFamily()
                        )
                    } else {
                        ClockFace(
                            hour = clockViewModel.getFormattedHour(),
                            min = clockViewModel.getFormattedMin(),
                            week = clockViewModel.getWeekDate(resources),
                            use24Format = clockViewModel.use24Format,
                            isFullScreen = clockViewModel.isFullScreen,
                            isRotateLocked = clockViewModel.isRotateLocked,
                            color = MaterialTheme.colors.primary,
                            fontFamily = clockViewModel.getFontFamily()
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun ClockFace(
        hour: String,
        min: String,
        week: String,
        use24Format: Boolean,
        isFullScreen: Boolean,
        isRotateLocked: Boolean,
        color: Color,
        fontFamily: FontFamily
        ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 15.dp),
            verticalArrangement = Arrangement.Center
        ) {
            ClockNumHour(text = hour, color = color, fontFamily = fontFamily)
            ClockNumWeek(text = week, color = color, fontFamily = fontFamily)
            ClockNumMin(text = min, color = color, fontFamily = fontFamily)
        }

        if (!isFullScreen) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, start = 15.dp, end = 15.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                FunBtnFONT(
                    color = color,
                    fontFamily = fontFamily
                )
                FunBtn24FORMAT(
                    color = color,
                    use24Format = use24Format,
                    fontFamily = fontFamily
                )
                FunBtnTHEME(
                    color = color,
                    fontFamily = fontFamily
                )
                FunBtnROTATE(
                    color = color,
                    isRotateLocked = isRotateLocked,
                    fontFamily = fontFamily
                )
            }
        }
    }

    @Composable
    fun ClockFaceLand(
        hour: String,
        min: String,
        week: String,
        use24Format: Boolean,
        isFullScreen: Boolean,
        isRotateLocked: Boolean,
        color: Color,
        fontFamily: FontFamily
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier.padding(start = 60.dp, top = 30.dp)) {
                ClockNumWeek(text = week, color = color, fontFamily = fontFamily)
            }
            Row(
                modifier = Modifier.padding(start = 45.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ClockNumHour(
                    text = hour,
                    color = color,
                    fontFamily = fontFamily
                )
                Spacer(modifier = Modifier.width(15.dp))
                ClockNumMin(text = min, color = color, fontFamily = fontFamily)
            }
        }
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            if (!isFullScreen) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 30.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    FunBtnFONT(
                        color = color,
                        fontFamily = fontFamily
                    )
                    FunBtn24FORMAT(
                        color = color,
                        use24Format = use24Format,
                        fontFamily = fontFamily
                    )
                    FunBtnTHEME(
                        color = color,
                        fontFamily = fontFamily
                    )
                    FunBtnROTATE(
                        color = color,
                        isRotateLocked = isRotateLocked,
                        fontFamily = fontFamily
                    )
                }
            }
        }
    }


    @Composable
    fun FunBtnFONT(color: Color, fontFamily: FontFamily) {
        FunBtn(
            text = "FONT",
            color = color,
            fontFamily = fontFamily,
            modifier = Modifier
                .clickable {
                    clockViewModel.switchFont()
                }
        )
    }

    @Composable
    fun FunBtn24FORMAT(color: Color, use24Format: Boolean, fontFamily: FontFamily) {
        FunBtn(
            text = if (use24Format) " 12[24]" else "[12]24 ",
            color = color,
            fontFamily = fontFamily,
            modifier = Modifier
                .clickable {
                    clockViewModel.switchUse24Format()
                }
        )
    }

    @Composable
    fun FunBtnTHEME(color: Color, fontFamily: FontFamily) {
        FunBtn(
            text = "THEME",
            color = color,
            fontFamily = fontFamily,
            modifier = Modifier
                .clickable {
                    clockViewModel.switchTheme()
                }
        )
    }

    @Composable
    fun FunBtnROTATE(color: Color, isRotateLocked: Boolean, fontFamily: FontFamily) {
//        val m = if (isRotateLocked) Modifier
//            .background(Color.Gray)
//            .clip(CircleShape) else Modifier
        FunBtn(
            text = if (isRotateLocked) "LOCKED" else "ROTATE",
            color = color,
            fontFamily = fontFamily,
            modifier = Modifier.clickable {
                clockViewModel.isRotateLocked = !clockViewModel.isRotateLocked
                requestedOrientation = if (clockViewModel.isRotateLocked) {
                    ActivityInfo.SCREEN_ORIENTATION_LOCKED
                } else {
                    ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                }
            }
        )
    }


//    @Preview(uiMode = UI_MODE_TYPE_NORMAL, showBackground = true)
//    @Composable
//    fun PreviewP() {
//        ImClockTheme {
//            Surface(color = MaterialTheme.colors.background, modifier = Modifier.clickable {
//                clockViewModel.isFullScreen = !clockViewModel.isFullScreen
//            }) {
//                ClockFace(
//                    hour = "15",
//                    min = "20",
//                    week = "星期三",
//                    use24Format = true,
//                    isFullScreen = true,
//                    isRotateLocked = true,
//                    color = MaterialTheme.colors.primary,
//                    fontFamily = FontFamily.Monospace
//                )
//            }
//        }
//    }
//
//    @Preview(uiMode = UI_MODE_TYPE_NORMAL, showBackground = true, widthDp = 864, heightDp = 432)
//    @Composable
//    fun PreviewL() {
//        ImClockTheme {
//            Surface(color = MaterialTheme.colors.background, modifier = Modifier.clickable {
//                clockViewModel.isFullScreen = !clockViewModel.isFullScreen
//            }) {
//                ClockFaceLand(
//                    hour = "15",
//                    min = "20",
//                    week = "星期三",
//                    use24Format = true,
//                    isFullScreen = true,
//                    isRotateLocked = false,
//                    color = MaterialTheme.colors.primary,
//                    fontFamily = FontFamily.Monospace
//                )
//            }
//        }
//    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        clockViewModel.currentOrientation = newConfig.orientation
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            clockViewModel.isLandScape = true
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT or Configuration.ORIENTATION_UNDEFINED) {
            clockViewModel.isLandScape = false
        }
    }
}

