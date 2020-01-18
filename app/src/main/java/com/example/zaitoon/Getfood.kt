package com.example.zaitoon

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_getfood.*

class Getfood : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_getfood)
        val selectedfood:String = intent.getStringExtra("CATEGORY")


        //                                              Overlay

        val overlay = findViewById<View>(R.id.rvvc)
        overlay.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_FULLSCREEN)




        var btnviewtoCart = findViewById<Button>(R.id.cooking)

        btnviewtoCart.setOnClickListener {

            val intent = Intent(this@Getfood ,CartProduct::class.java)
            startActivity(intent)
        }



//1                                            Animation

        val poa= AnimationUtils.loadAnimation(this , R.anim.shake_animation)
        val imagefile = findViewById<ImageView>(R.id.imageView7)
        imagefile.startAnimation(poa)


        // 2                                           Animation

        val poa5= AnimationUtils.loadAnimation(this , R.anim.shake_animation)
        val imagefile5 = findViewById<ImageView>(R.id.imageView5)
        imagefile5.startAnimation(poa5)



// 3                                           Animation

        val poa8= AnimationUtils.loadAnimation(this , R.anim.shake_animation)
        val imagefile8 = findViewById<ImageView>(R.id.imageView8)
        imagefile8.startAnimation(poa8)



        // 4                                          Animation

        val poa9= AnimationUtils.loadAnimation(this , R.anim.shake_animation)
        val imagefile9 = findViewById<ImageView>(R.id.imageView9)
        imagefile9.startAnimation(poa9)



// 5                                           Animation

        val poa10= AnimationUtils.loadAnimation(this , R.anim.shake_animation)
        val imagefile10 = findViewById<ImageView>(R.id.imageView10)
        imagefile10.startAnimation(poa10)



//  6                                          Animation

        val poa11= AnimationUtils.loadAnimation(this , R.anim.shake_animation)
        val imagefile11 = findViewById<ImageView>(R.id.imageView11)
        imagefile11.startAnimation(poa11)



// 7                                           Animation


        val poa13 = AnimationUtils.loadAnimation(this, R.anim.shake_animation)
        val imagefile13 = findViewById<ImageView>(R.id.imageView13)
        imagefile13.startAnimation(poa13)


//                                         Animation


        val fooditems = AnimationUtils.loadAnimation(this , R.anim.transition_animation_from_top)
        val fooditemsheader = findViewById<TextView>(R.id.categorytext)

        fooditemsheader.startAnimation(fooditems)










//                                     Get food in Recycler view Code


        var foodproductlist = ArrayList<EProducts>()

        val fooditemURL = "http://192.168.43.24/zaitoon/fetch_eproduct.php?category=$selectedfood"
        var requestQ = Volley.newRequestQueue(this@Getfood)
        var jsonAR = JsonArrayRequest(Request.Method.GET, fooditemURL , null , Response.Listener{

            response->
            for (foodJO in 0.until(response.length())) {

                foodproductlist.add(EProducts(response.getJSONObject(foodJO).getInt("id"),
                        response.getJSONObject(foodJO).getString("name"),
                        response.getJSONObject(foodJO).getInt("price"),
                        response.getJSONObject(foodJO).getString("picture"),
                        response.getJSONObject(foodJO).getString("description")))
            }

            val foodAdapter = RVadapter(this@Getfood,foodproductlist)
            rvv.layoutManager = LinearLayoutManager(this@Getfood)
            rvv.adapter = foodAdapter





        }, Response.ErrorListener { error ->

            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setTitle("Message")
            dialogBuilder.setMessage(error.message)
            dialogBuilder.create().show()
        })

        requestQ.add(jsonAR)


    }
}
