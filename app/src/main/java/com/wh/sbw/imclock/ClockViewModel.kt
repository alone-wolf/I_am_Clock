package com.wh.sbw.imclock

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.pm.ActivityInfo
import android.content.res.Resources
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontFamily
import androidx.lifecycle.AndroidViewModel
import com.wh.sbw.imclock.ui.theme.THEME_TYPE_COUNT
import com.wh.sbw.imclock.util.ensure00
import com.wh.sbw.imclock.util.fontList
import com.wh.sbw.imclock.util.hourTo24Format
import com.wh.sbw.imclock.util.num2week
import java.util.*

class ClockViewModel(application: Application) : AndroidViewModel(application) {
    private val sharedPreference = application.getSharedPreferences("config", MODE_PRIVATE)


    private var currentHour by mutableStateOf(0)
    var currentMin by mutableStateOf(0)
    var currentWeek by mutableStateOf(1)

    var isFullScreen by mutableStateOf(false)

    var selectedFont by mutableStateOf(0)

    var use24Format by mutableStateOf(true)

    var isLandScape by mutableStateOf(false)

    var isRotateLocked by mutableStateOf(false)

    var currentOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED

//    var isDarkTheme by mutableStateOf(false)
    var selectedTheme by mutableStateOf(0)

    init {
        selectedFont = sharedPreference.getInt("selectedFont", 0)
        use24Format = sharedPreference.getBoolean("use24Format",true)
        selectedTheme = sharedPreference.getInt("selectedTheme",0)
    }

    fun updateTime() {
        val c = Calendar.getInstance()
        currentHour = c.get(Calendar.HOUR_OF_DAY)
        currentMin = c.get(Calendar.MINUTE)
        currentWeek = num2week[c.get(Calendar.DAY_OF_WEEK)]!!
    }

    fun switchFont(){
        selectedFont=(selectedFont+1)% fontList.size
        sharedPreference.edit().putInt("selectedFont",selectedFont).apply()
    }

    fun switchUse24Format(){
        use24Format=!use24Format
        sharedPreference.edit().putBoolean("use24Format",use24Format).apply()
    }

    fun switchTheme(){
        selectedTheme = (selectedTheme+1)% THEME_TYPE_COUNT
        sharedPreference.edit().putInt("selectedTheme",selectedTheme).apply()
    }

    fun getFormattedHour(): String {
        return ensure00(hourTo24Format(currentHour, use24Format))
    }

    fun getWeekDate(r:Resources): String {
        return r.getString(currentWeek)
    }

    fun getFormattedMin(): String {
        return ensure00(currentMin)
    }

    fun getFontFamily(): FontFamily {
        return fontList[selectedFont]
    }
}