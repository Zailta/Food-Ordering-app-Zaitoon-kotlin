@file:Suppress("DEPRECATION")
package com.example.zaitoon

import android.app.DialogFragment
import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley


@Suppress("DEPRECATION") class AmountFragment : DialogFragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var fragmentView = inflater.inflate(R.layout.fragment_amount, container, false)
        var edtamount = fragmentView.findViewById<EditText>(R.id.edtamount)as EditText
        var btnAddtoCart = fragmentView.findViewById<ImageButton>(R.id.imageButton)

        btnAddtoCart.setOnClickListener {
            if(edtamount.text.toString().isNotBlank()) {


                var tempOrderURL = "http://192.168.43.24/zaitoon/place_order.php?email=${Person.email} &p_id=${Person.addtocartproductID} &amount=${edtamount.text.toString()}"

                var requestQ = Volley.newRequestQueue(activity)
                var stringRequest = StringRequest(Request.Method.GET, tempOrderURL, Response.Listener {
                    dismiss()

                }, Response.ErrorListener {


                })
                requestQ.add(stringRequest)
                Handler().postDelayed({


                    val snack = Snackbar.make(it, "value added to Cart", Snackbar.LENGTH_LONG)
                    snack.view.setBackgroundColor(Color.BLACK)
                    snack.show()

                },500)



            }
            else{

                val snack = Snackbar.make(it,"Please Enter an Amount", Snackbar.LENGTH_LONG)
                snack.view.setBackgroundColor(Color.BLACK)
                snack.show()
            }

        }
        return fragmentView
    }


}
