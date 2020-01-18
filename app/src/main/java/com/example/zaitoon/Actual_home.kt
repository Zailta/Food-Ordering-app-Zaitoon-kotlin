package com.example.zaitoon

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_actual_home.*

class Actual_home : AppCompatActivity() {

    private var backPressedTime: Long = 0
    private var backtoast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actual_home)


        //                                              Overlay

        val overlay = findViewById<View>(R.id.Actual_home)
        overlay.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_FULLSCREEN)

        //                                              Animation


        val hometext = AnimationUtils.loadAnimation(this , R.anim.transition_animation_from_top)
        val textheaderhome = findViewById<TextView>(R.id.categorytext)
        textheaderhome.startAnimation(hometext)


        //1                                            Animation

        val poa= AnimationUtils.loadAnimation(this , R.anim.popout_animation)
        val imagefile = findViewById<ImageView>(R.id.imageView7) as ImageView
        imagefile.startAnimation(poa)


        // 2                                           Animation

        val poa5= AnimationUtils.loadAnimation(this , R.anim.popout_animation)
        val imagefile5 = findViewById<ImageView>(R.id.imageView5)
        imagefile5.startAnimation(poa5)



        // 3                                           Animation

        val poa8= AnimationUtils.loadAnimation(this , R.anim.popout_animation)
        val imagefile8 = findViewById<ImageView>(R.id.imageView8)
        imagefile8.startAnimation(poa8)



        // 4                                          Animation

        val poa9= AnimationUtils.loadAnimation(this , R.anim.popout_animation)
        val imagefile9 = findViewById<ImageView>(R.id.imageView9)
        imagefile9.startAnimation(poa9)



        // 5                                           Animation

        val poa10= AnimationUtils.loadAnimation(this , R.anim.popout_animation)
        val imagefile10 = findViewById<ImageView>(R.id.imageView10)
        imagefile10.startAnimation(poa10)



        //  6                                          Animation

        val poa11= AnimationUtils.loadAnimation(this , R.anim.popout_animation)
        val imagefile11 = findViewById<ImageView>(R.id.imageView11)
        imagefile11.startAnimation(poa11)



        // 7                                           Animation


        val poa13 = AnimationUtils.loadAnimation(this, R.anim.popout_animation)
        val imagefile13 = findViewById<ImageView>(R.id.imageView13)
        imagefile13.startAnimation(poa13)






        //                                       foodURL from the server Code


        var foodUpURL = "http://192.168.43.24/zaitoon/fetch_food.php"
        var foodlist = ArrayList<String>()

        var requestQ = Volley.newRequestQueue(this@Actual_home)
        var jsonAR = JsonArrayRequest(Request.Method.GET, foodUpURL, null, Response.Listener {

            response ->
            for (jsonObject in 0.until(response.length())) {

                foodlist.add(response.getJSONObject(jsonObject).getString("category"))
            }

            var foodListAdapter = ArrayAdapter(this@Actual_home, R.layout.design_actual_home, foodlist)
            listview.adapter = foodListAdapter


        }, Response.ErrorListener { error ->

            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setTitle("Message")
            dialogBuilder.setMessage(error.message)
            dialogBuilder.create().show()
        })

        requestQ.add(jsonAR)


        //Reffering to our list view
        listview.setOnItemClickListener { _, _, position, _ ->

            val tappedFood = foodlist.get(position)
            val intent = Intent(this@Actual_home, Getfood::class.java)
            intent.putExtra("CATEGORY", tappedFood)
            startActivity(intent)
        }


    }

    override fun onBackPressed() {
        if( backPressedTime+2000 > System.currentTimeMillis()){
            backtoast?.cancel()
            super.onBackPressed()
            var a =  Intent(Intent.ACTION_MAIN)
            a.addCategory(Intent.CATEGORY_HOME)
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(a)
            return
        } else{
            backtoast = Toast.makeText(getBaseContext() , "Press again to exit" , Toast.LENGTH_SHORT)
            backtoast?.show()
        }
        backPressedTime = System.currentTimeMillis()
    }

}
