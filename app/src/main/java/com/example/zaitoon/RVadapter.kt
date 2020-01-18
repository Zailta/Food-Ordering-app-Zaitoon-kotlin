package com.example.zaitoon

import android.app.Activity
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recyclerview_row.view.*

class RVadapter(var context: Context, var  arrayList: ArrayList<EProducts> ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var productView = LayoutInflater.from(context).inflate(R.layout.recyclerview_row, parent, false)

        return ProductViewHolder(productView)
    }

    override fun getItemCount(): Int {

        return arrayList.size

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as ProductViewHolder).initializeUIComponents(arrayList[position].id, arrayList[position].name, arrayList[position].price, arrayList[position].picture, arrayList[position].description)

    }

    inner class ProductViewHolder(myView: View) : RecyclerView.ViewHolder(myView) {


        fun initializeUIComponents(id: Int, name: String, price: Int, picture: String, des: String) {

            itemView.txtid.text = id.toString()
            itemView.description.text = des
            itemView.txtname.text = name
            itemView.txtprice.text = price.toString()
            var pictURL = "http://192.168.1.6/zaitoon/images/"
            pictURL = pictURL.replace(" ", "%20")
            Picasso.get().load(pictURL + picture).into(itemView.recyclerimage)

            itemView.cart.setOnClickListener {

                Person.addtocartproductID = id

                var amountFragment = AmountFragment()
                @Suppress("DEPRECATION") var fragmentManager = (itemView.context as Activity).fragmentManager
                amountFragment.show(fragmentManager, "TAG")


            }

        }
    }
}


