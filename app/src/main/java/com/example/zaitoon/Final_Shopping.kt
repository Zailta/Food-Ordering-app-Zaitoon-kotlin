package com.example.zaitoon

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_final__shopping.*

class Final_Shopping : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final__shopping)
        //                                              Overlay

        val overlay = findViewById<View>(R.id.final_shopping)
        overlay.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_FULLSCREEN)


//                                          Animation


        val taft = AnimationUtils.loadAnimation(this, R.anim.transition_animation_from_top)
        val textheader = findViewById<TextView>(R.id.finalText)
        textheader.startAnimation(taft)


        val taftdrop = AnimationUtils.loadAnimation(this, R.anim.transition_animation_from_top)
        val textheaderdrop = findViewById<TextView>(R.id.drop)
        textheaderdrop.startAnimation(taftdrop)


//                                            Animation

        val poa = AnimationUtils.loadAnimation(this, R.anim.shake_custom)
        val imagefile = findViewById<ImageView>(R.id.imageView19)
        imagefile.startAnimation(poa)


//                                          Animation


        val signinanin = AnimationUtils.loadAnimation(this, R.anim.animation_top)
        val textheadersignin = findViewById<TextView>(R.id.details)
        textheadersignin.startAnimation(signinanin)


//                                              Calculate fetch Price Code
        var totalPriceURL = "http://192.168.43.24/zaitoon/calculate_total_price.php?invoice_num=${intent.getStringExtra("LATEST_INVOICE_NUMBER")}"
        var requestQ = Volley.newRequestQueue(this@Final_Shopping)
        var stringRequest = StringRequest(Request.Method.GET, totalPriceURL, Response.Listener { response ->

            totalamount.text = "Sub Total ₹ : ${response}"

        }, Response.ErrorListener {

        })
        requestQ.add(stringRequest)

        buttonbleh.setOnClickListener {

            if(number.text.toString().isNotBlank() && pincode.text.toString().isNotBlank() && address.text.toString().isNotBlank()&&number.text.toString().length == 10 &&pincode.text.toString().length == 6 && address.text.toString().length > 10) {

                var addressURL = "http://192.168.43.24/zaitoon/address_table_person.php?name=${Person.email} &phone_number=${number.text.toString()} &address=${address.text.toString()} &pincode=${pincode.text.toString()}"

                var requestQaddress = Volley.newRequestQueue(this@Final_Shopping)
                var stringRequestaddress = StringRequest(Request.Method.GET, addressURL, Response.Listener {

                    response ->


                    val snack = Snackbar.make(it, response, Snackbar.LENGTH_LONG)
                    snack.view.setBackgroundColor(Color.BLACK)
                    snack.show()

                    Handler().postDelayed({
                        var intent = Intent(this@Final_Shopping, Address_display::class.java)
                        startActivity(intent)
                    }, 1000)

                }, Response.ErrorListener { error ->

                    val dialogBuilder = AlertDialog.Builder(this)
                    dialogBuilder.setTitle("Message")
                    dialogBuilder.setMessage(error.message)
                    dialogBuilder.create().show()


                })
                requestQaddress.add(stringRequestaddress)

            }
            else if (number.text.toString().length < 10|| number.text.toString().length >10){
                val snack2 = Snackbar.make(it,"You entered an Invalid format of Phone Number (eg: 1234567890) ",Snackbar.LENGTH_LONG)
                snack2.view.setBackgroundColor(Color.BLACK)
                snack2.show()

            }

            else if (pincode.text.toString().length < 6 || pincode.text.toString().length > 6){
                val snack2 = Snackbar.make(it,"You entered an Invalid Pincode(eg:190011 ,213345)",Snackbar.LENGTH_LONG)
                snack2.view.setBackgroundColor(Color.BLACK)
                snack2.show()
            }

            else if (address.text.toString().length < 10){
                val snack2 = Snackbar.make(it,"You must enter the entire Address (eg: flat/house no. ,street no.etc)",Snackbar.LENGTH_LONG)
                snack2.view.setBackgroundColor(Color.BLACK)
                snack2.show()
            }





            else {
                val snack = Snackbar.make(it,"You must Enter all fields!",Snackbar.LENGTH_LONG)
                snack.view.setBackgroundColor(Color.BLACK)
                snack.show()
            }







        }








    }


}








