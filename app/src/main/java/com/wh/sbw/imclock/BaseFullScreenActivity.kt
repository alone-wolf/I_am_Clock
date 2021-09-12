package com.wh.sbw.imclock

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

open class BaseFullScreenActivity : ComponentActivity() {
    lateinit var clockViewModel: ClockViewModel
    private lateinit var timeTickBroadcastReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.attributes.apply {
            window.statusBarColor = android.graphics.Color.TRANSPARENT
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            }
        }

        clockViewModel = ViewModelProvider(this)[ClockViewModel::class.java]
        timeTickBroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(p0: Context?, p1: Intent?) {
                p1?.let {
                    when (it.action) {
                        Intent.ACTION_TIME_TICK -> clockViewModel.updateTime()
                    }
                }
            }
        }
        val intentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_TIME_TICK)
        }
        registerReceiver(timeTickBroadcastReceiver, intentFilter)

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            clockViewModel.isLandScape = true
        }
    }

    override fun onResume() {
        super.onResume()
        hideUI()
        clockViewModel.updateTime()
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(timeTickBroadcastReceiver)
        window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    //    private fun showUI() {
//        lifecycleScope.launch {
//            delay(300)
//            if (Build.VERSION.SDK_INT >= 30) {
//                window.decorView.windowInsetsController?.show(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
//            } else {
//                window.decorView.systemUiVisibility =
//                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
//                            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//            }
//            actionBar?.show()
//        }
//    }

    private fun hideUI() {
        lifecycleScope.launch {
            delay(300)
            actionBar?.hide()
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//            window.decorView.systemUiVisibility =
//                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
//                        View.SYSTEM_UI_FLAG_FULLSCREEN or
//                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        }
    }
}