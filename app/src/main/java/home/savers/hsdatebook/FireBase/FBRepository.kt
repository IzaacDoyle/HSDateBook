package  home.savers.hsdatebook.FireBase

import android.app.Activity
import android.util.Log
import android.view.View
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import home.savers.hsdatebook.DataClass.HSAccount
import home.savers.hsdatebook.DataClass.MainRecycleProd
import home.savers.hsdatebook.DataClass.Product
import home.savers.hsdatebook.Main_Screen

import home.savers.hsdatebook.RecyclerView.productRec

import java.security.Key
import java.security.Timestamp
import java.util.*
import kotlin.math.log

var update:Boolean = false
var database = FirebaseDatabase.getInstance()





/*
gets products
    fun getProductsRT() {
        db.collection("Products/Stock/OutOfDate").addSnapshotListener { value, error ->
            error?.let {
                Log.d("product","Error")
                return@addSnapshotListener
            }
            value?.let {
                for (document in it){
                    val key = document.id
                    val day = document.getString("Day")
                    val month = document.getString("Month")
                    val year = document.getString("Year")
                    val aisle = document.getString("Aisle")
                    val productName = document.getString("ProductName")
                    val quantity = document.getString("Quantity")
                    val productBarcode = document.getString("ProductBarcode")


                    if (productRec?.contains(MainRecycleProd(key, "$day", "$month", "$year", "$productName","$quantity"))!!) {
                        return@addSnapshotListener
                    }else{
                        productRec?.add(MainRecycleProd(key, "$day", "$month", "$year", "$productName", "$quantity"))


                    }
                }
            }
        }

    }




 */


//creates new products and saves to firebase firestore
    fun FBCreateNEWProd(

        Date:String,
        ProductName: String,
        Quantity: String,
        ProductBarcode: String,
        Aisle: String
    ) {


        var Product = hashMapOf(

            "Date" to Date.toLowerCase(),
            "ProductName" to ProductName.toLowerCase(),
            "Quantity" to Quantity.toLowerCase(),
            "ProductBarcode" to ProductBarcode.toLowerCase(),
            "Aisle" to Aisle.toLowerCase()
        )

        db.collection("Products/Stock/OutOfDate").document()
            .set(Product)
            .addOnSuccessListener {
                Log.d("Product", "Success to add")
            }
            .addOnFailureListener { e ->
                Log.d("Product", "Fail to add", e)
            }
    }

    fun deleteProduct(key: Key): Task<Void> {
        var documentReference = db.collection("Products/Stock/OutOfDate")
            .document(key.toString())
        return documentReference.delete()
    }








