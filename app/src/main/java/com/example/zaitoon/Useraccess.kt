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
import kotlinx.android.synthetic.main.activity_useraccess.*

class Useraccess : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_useraccess)

        //                                              Overlay

        val overlay = findViewById<View>(R.id.userlayout)
        overlay.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_FULLSCREEN)

//                                          Animation

        val taft = AnimationUtils.loadAnimation(this , R.anim.transition_animation_from_top)
        val textheaderUser = findViewById<TextView>(R.id.textView2User)

        textheaderUser.startAnimation(taft)

//                                              Animation

        val poaUser= AnimationUtils.loadAnimation(this , R.anim.popout_animation)
        val imagefile = findViewById<ImageView>(R.id.imageView4User)

        imagefile.startAnimation(poaUser)

//                                          Animation


        val loginanin = AnimationUtils.loadAnimation(this , R.anim.animation_top)
        val textheaderlogin = findViewById<TextView>(R.id.logintext)

        textheaderlogin.startAnimation(loginanin)


//                                              Animation

        val poaUsercook= AnimationUtils.loadAnimation(this , R.anim.popout_animation)
        val imagefilecook = findViewById<ImageView>(R.id.cookexcited)

        imagefilecook.startAnimation(poaUsercook)





//                             Button click Listener with login Operation


        google.setOnClickListener {

            if (editTextemail.text.toString().isNotBlank() && editTextpass.text.toString().isNotBlank()) {

                val signUpURL = "https://zailta.000webhostapp.com/login_app_user.php?email=" + editTextemail.text.toString() + "&pass=" + editTextpass.text.toString()

                val requestQ = Volley.newRequestQueue(this@Useraccess)
                val stringRequest = StringRequest(Request.Method.GET, signUpURL, Response.Listener {

                    response ->
                    if (response.equals("The user does exist")) {

                        val snack = Snackbar.make(it, response, Snackbar.LENGTH_LONG)
                        snack.view.setBackgroundColor(Color.BLACK)
                        snack.show()


                        Person.email = editTextemail.text.toString()

                        Handler().postDelayed({
                            val homeIntent = Intent(this@Useraccess, Actual_home::class.java)
                            startActivity(homeIntent)
                        },1000)


                    } else {


                        val dialogBuilder = AlertDialog.Builder(this)
                        dialogBuilder.setTitle("Message")
                        dialogBuilder.setMessage(response)
                        dialogBuilder.create().show()

                    }

                }, Response.ErrorListener { error ->
                    val dialogBuilder = AlertDialog.Builder(this)
                    dialogBuilder.setTitle("Message")
                    dialogBuilder.setMessage(error.message)
                    dialogBuilder.create().show()

                })
                requestQ.add(stringRequest)
            }
            else{

                val snack = Snackbar.make(it,"Please Enter Valid Credentials",Snackbar.LENGTH_LONG)
                snack.view.setBackgroundColor(Color.BLACK)
                snack.show()

            }




        }
    }
}
