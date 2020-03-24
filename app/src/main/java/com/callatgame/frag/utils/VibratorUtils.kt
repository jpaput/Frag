package com.callatgame.frag.utils

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import java.time.Duration

class VibratorUtils(val context: Context) {

    companion object{
        val SHORT_DURATION = 120L
    }

    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?

    fun vibrate(duration : Long){
        if (vibrator != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(
                    VibrationEffect.createOneShot(
                        duration,
                        VibrationEffect.DEFAULT_AMPLITUDE
                    )
                )
            } else {
                vibrator.vibrate(duration)
            }
        }
    }
}