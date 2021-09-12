package com.wh.sbw.imclock.util

import com.wh.sbw.imclock.R

val num2week = mapOf(
    Pair(1, R.string.week_day_Sun),
    Pair(2,R.string.week_day_Mon),
    Pair(3,R.string.week_day_Tues),
    Pair(4,R.string.week_day_Wed),
    Pair(5,R.string.week_day_Thu),
    Pair(6,R.string.week_day_Fri),
    Pair(7,R.string.week_day_Sat),
)

fun ensure00(n:Int): String {
    return if(n<10){
        "0$n"
    }else{
        n.toString()
    }
}

fun hourTo24Format(h:Int,use24:Boolean): Int {
    return if(use24) h else h%12
}

