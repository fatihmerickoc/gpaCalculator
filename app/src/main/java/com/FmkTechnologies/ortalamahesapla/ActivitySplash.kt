package com.FmkTechnologies.ortalamahesapla

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash.*

class ActivitySplash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)




        var asagidangelenButon = AnimationUtils.loadAnimation(this,R.anim.asagidangelenbuton)
        btn_hesaplaOrtalamaanim.animation = asagidangelenButon

        var yukaridangelenBalon = AnimationUtils.loadAnimation(this,R.anim.yukaridangelenbalon)
        animresim.animation = yukaridangelenBalon


        var asagiyagidenButon = AnimationUtils.loadAnimation(this,R.anim.asagidangidenbuton)
        var yukariyayagidenBalon = AnimationUtils.loadAnimation(this,R.anim.yukaridangidenbalon)




        btn_hesaplaOrtalamaanim.setOnClickListener {

            btn_hesaplaOrtalamaanim.startAnimation(asagiyagidenButon)
            animresim.startAnimation(yukariyayagidenBalon)

    object : CountDownTimer(1000,1000){
    override fun onFinish() {
        var intent = Intent(applicationContext,MainActivity::class.java)
        startActivity(intent)    }

    override fun onTick(millisUntilFinished: Long) {
           }

    }.start()


        }





    }
}
