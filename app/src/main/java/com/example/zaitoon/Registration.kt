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
import kotlinx.android.synthetic.main.activity_registration.*

class Registration : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        //                                        Screen Overlay


        val overlay = findViewById<View>(R.id.registration)
        overlay.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_FULLSCREEN)



//                                          Animation


        val taft = AnimationUtils.loadAnimation(this , R.anim.transition_animation_from_top)
        val textheader = findViewById<TextView>(R.id.textView2)

        textheader.startAnimation(taft)


//                                            Animation

        val poa= AnimationUtils.loadAnimation(this , R.anim.popout_animation)
        val imagefile = findViewById<ImageView>(R.id.imageView4)
        imagefile.startAnimation(poa)




//                                          Animation


        val signinanin = AnimationUtils.loadAnimation(this , R.anim.animation_top)
        val textheadersignin = findViewById<TextView>(R.id.signintext)
        textheadersignin.startAnimation(signinanin)




//                           Button listener Sign In operation execution Code


        gog.setOnClickListener {

            if (editpass.text.toString().equals(editpassconfirm.text.toString()) && editemail.text.toString().isNotBlank()&& editname.text.toString().isNotBlank() && editemail.text.toString().length > 15){

                val signUpURL = "http://192.168.43.24/zaitoon/insert_product.php?email=" + editemail.text.toString() + "&name=" + editname.text.toString() + "&pass=" + editpass.text.toString()

                val requestQ = Volley.newRequestQueue(this@Registration)
                val stringRequest = StringRequest(Request.Method.GET, signUpURL , Response.Listener{

                    response->
                    if (response.equals("A user with this Email Address already exists")){


                        val dialogBuilder = AlertDialog.Builder(this)
                        dialogBuilder.setTitle("Message")
                        dialogBuilder.setMessage(response)
                        dialogBuilder.create().show()


                    }
                    else{
                        //  val dialogBuilder = AlertDialog.Builder(this)
                        //   dialogBuilder.setTitle("Message")
                        //   dialogBuilder.setMessage(response)
                        //   dialogBuilder.create().show()
                        Person.email = editemail.text.toString()

                        val snack = Snackbar.make(it, response ,Snackbar.LENGTH_LONG)
                        snack.view.setBackgroundColor(Color.BLACK)
                        snack.show()

                        Handler().postDelayed({
                            val homeIntent = Intent(this@Registration, Actual_home::class.java)
                            startActivity(homeIntent)
                        },1000)
                    }

                }, Response.ErrorListener{ error->
                    val dialogBuilder = AlertDialog.Builder(this)
                    dialogBuilder.setTitle("Message")
                    dialogBuilder.setMessage(error.message)
                    dialogBuilder.create().show()

                })
                requestQ.add(stringRequest)

            }else{

                val snack = Snackbar.make(it,"Please Enter Valid Credentials",Snackbar.LENGTH_LONG)
                snack.view.setBackgroundColor(Color.BLACK)
                snack.show()
            }
        }

    }

}
