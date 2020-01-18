package com.example.zaitoon

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_final__shopping.*

class Address_display : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address_display)


        //                                              Overlay

        val overlay = findViewById<View>(R.id.lastscreen)
        overlay.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_FULLSCREEN)





//                                            Animation Image 18



        val poa14= AnimationUtils.loadAnimation(this , R.anim.popout_animation)
        val imagefile14 = findViewById<ImageView>(R.id.imageView17)
        imagefile14.startAnimation(poa14)




//                                            Animation Image 17

        val poa15= AnimationUtils.loadAnimation(this , R.anim.popout_animation)
        val imagefile15 = findViewById<ImageView>(R.id.imageView18)
        imagefile15.startAnimation(poa15)







//                                          Animation


        val taft = AnimationUtils.loadAnimation(this , R.anim.transition_animation_from_top)
        val textheader = findViewById<TextView>(R.id.textView3)
        textheader.startAnimation(taft)

//                                          Animation


        val taft1 = AnimationUtils.loadAnimation(this , R.anim.transition_animation_from_top)
        val textheader1 = findViewById<TextView>(R.id.textView4)
        textheader1.startAnimation(taft1)
    }



}
