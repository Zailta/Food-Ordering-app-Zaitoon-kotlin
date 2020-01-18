package com.example.zaitoon

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_actual_home.*
import kotlinx.android.synthetic.main.activity_cart_product.*

class CartProduct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_product)


        var overlay = findViewById<View>(R.id.cart)
        overlay.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_FULLSCREEN)






//                                       Fetching Orders in temporary window Code

        var cartProductURL = "http://192.168.43.24/zaitoon/fetch_temporary_order.php?email=${Person.email}"
        var cartlist = ArrayList<String>()

        var requestQ2 = Volley.newRequestQueue(this@CartProduct)
        var jsonAR2 = JsonArrayRequest(Request.Method.GET, cartProductURL , null , Response.Listener{

            response->

            for (joIndex in 0.until(response.length())) {

                cartlist.add("${response.getJSONObject(joIndex).getInt("id")} \n" +
                        " ${response.getJSONObject(joIndex).getString("name")} \n" +
                        " ${response.getJSONObject(joIndex).getInt("price")} \n " +
                        "${response.getJSONObject(joIndex).getInt("amount")} \n")
            }

            var cartProductAdapter = ArrayAdapter(this@CartProduct , R.layout.design_cart_product , cartlist)
            temporder.adapter = cartProductAdapter


        }, Response.ErrorListener {


        })

        requestQ2.add(jsonAR2)




        var clear = findViewById<Button>(R.id.decline)










//                                             Place order button

        placeorderbutton.setOnClickListener {


            var verifyOrderURL = "http://192.168.43.24/zaitoon/verify_order.php?email=${Person.email}"
            var requestQ1 = Volley.newRequestQueue(this@CartProduct)
            var stringRequest1 = StringRequest(Request.Method.GET , verifyOrderURL , Response.Listener {
                response->

                var intent = Intent(this , Final_Shopping::class.java)

                Toast.makeText(this , response , Toast.LENGTH_LONG).show()
                intent.putExtra("LATEST_INVOICE_NUMBER",response)
                startActivity(intent)


            }, Response.ErrorListener {  })

            requestQ1.add(stringRequest1)

        }






//                                                    Decline Button


        clear.setOnClickListener {

            var deleteURL = "http://192.168.43.24/zaitoon/decline_order.php?email=${Person.email}"
            var requestQ = Volley.newRequestQueue(this@CartProduct)
            var stringRequest = StringRequest(Request.Method.GET , deleteURL , Response.Listener {
                _->
                var intent = Intent(this , Actual_home::class.java)
                startActivity(intent)

            }, Response.ErrorListener { _->

            } )
            requestQ.add(stringRequest)


        }








//                                             Animation

        val poaorderfood= AnimationUtils.loadAnimation(this , R.anim.shake_animation)
        val imagefileorderfood = findViewById<ImageView>(R.id.groceriesorder)
        imagefileorderfood.startAnimation(poaorderfood)



//                                                   Animation


        val taft = AnimationUtils.loadAnimation(this , R.anim.transition_animation_from_top)
        val textheader = findViewById<TextView>(R.id.temptext)

        textheader.startAnimation(taft)



//                                             Place Order Button Animation




        val taftbutton = AnimationUtils.loadAnimation(this , R.anim.animation_top)
        val textheaderbutton = findViewById<Button>(R.id.placeorderbutton)

        textheaderbutton.startAnimation(taftbutton)



//                                             clear cart  Button Animation


        val taftbuttonclear = AnimationUtils.loadAnimation(this , R.anim.animation_top)
        val textheaderbuttonclear = findViewById<Button>(R.id.decline)

        textheaderbuttonclear.startAnimation(taftbuttonclear)


//                                            Animation Image 14

        val poa14= AnimationUtils.loadAnimation(this , R.anim.popout_animation)
        val imagefile14 = findViewById<ImageView>(R.id.imageView14)
        imagefile14.startAnimation(poa14)






//                                            Animation Image 16

        val poa16= AnimationUtils.loadAnimation(this , R.anim.popout_animation)
        val imagefile16 = findViewById<ImageView>(R.id.imageView16)
        imagefile16.startAnimation(poa16)




    }


}

