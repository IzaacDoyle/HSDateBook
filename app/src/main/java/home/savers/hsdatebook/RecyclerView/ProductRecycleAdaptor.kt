package home.savers.hsdatebook.RecyclerView

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import home.savers.hsdatebook.DataClass.MainRecycleProd
import home.savers.hsdatebook.DataClass.Product
import home.savers.hsdatebook.Product_View_Update
import home.savers.hsdatebook.R
import kotlinx.android.synthetic.main.display_products_all.view.*
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

var productRec: java.util.ArrayList<MainRecycleProd>?=null

class ProductRecycleAdaptor(private val products: ArrayList<Product>, private val context: Context):
    RecyclerView.Adapter<ProductRecycleAdaptor.ViewHolder>() {

    



    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindProducts(products: Product){

            itemView.View_PN.text  = products.ProductName
            itemView.View_A.text  = products.Aisle
            itemView.View_PB.text  = products.ProductBarcode
            itemView.View_Q.text  = products.Quantity




        }

        init {
            itemView.setOnClickListener { v: View ->
                val position = adapterPosition

                val intent = Intent(itemView.context, Product_View_Update::class.java)
                v.context.startActivity(intent)


            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.display_products_all,
            parent,
            false
        )
        return ViewHolder(v)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindProducts(products[position])

    }

    override fun getItemCount(): Int {
        return products.size
    }




    }


