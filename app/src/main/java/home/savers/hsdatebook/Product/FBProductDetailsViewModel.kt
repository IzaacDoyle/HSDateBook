package home.savers.hsdatebook.Product

import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.core.view.isNotEmpty
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import home.savers.hsdatebook.Calendar.Days
import home.savers.hsdatebook.Calendar.Months
import home.savers.hsdatebook.Calendar.Years
import home.savers.hsdatebook.DataClass.MainRecycleProd
import home.savers.hsdatebook.DataClass.Product


import home.savers.hsdatebook.FireBase.update
import home.savers.hsdatebook.RecyclerView.productRec
import java.lang.Character.toLowerCase
import java.util.*
import kotlin.collections.ArrayList


class FBProductDetailsViewModel : ViewModel() {
    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var products: MutableLiveData<ArrayList<Product>> = MutableLiveData<ArrayList<Product>>()



    init {
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
        getProducts()

    }

    override fun onCleared() {
        super.onCleared()
    }

    public fun getProducts() {
          firestore.collection("Products/Stock/OutOfDate").addSnapshotListener{
              snapshot, e->

              if (e != null){
                  Log.d("Product","Failed", e)
                  return@addSnapshotListener
              }
              val allProducts = ArrayList<Product>()
              if (snapshot != null){

              val documents =    snapshot.documents
                  documents.forEach{
                    val products= it.toObject(Product::class.java)

                      if (products !=null)

                          allProducts.add(products!!)
                  }
              }
              products.value = allProducts
              Log.d("Products","all prod $allProducts")
          }

    }

    public fun productUpdate(){



    }

    public  fun productDelete(){

    }

    public fun SearchProductsName(productName:String) {
        firestore.collection("Products/Stock/OutOfDate").orderBy("ProductName")
            .startAt(productName).endAt("$productName\uf8ff")
            .get()
            .addOnSuccessListener { snapshot ->
                val searchProduct = ArrayList<Product>()
                if (snapshot != null) {
                    val documents = snapshot.documents
                    documents.forEach {
                        val sproducts = it.toObject(Product::class.java)
                        if (sproducts != null) {

                            searchProduct.add(sproducts!!)
                            Log.d("SearchsProd", "$sproducts")
                        }else{
                            Log.d("ErrorSearchsProd","$sproducts")
                        }
                    }
                }else{
                    Log.d("ErrorSearchsProd","$snapshot")
                }
                products.value = searchProduct

                if (productName.isEmpty() || productName.isNullOrBlank()){
                    getProducts()

                }
            }


        /*
        firestore.collection("Products/Stock/OutOfDate").orderBy("Aisle")
            .startAt(productName).endAt("$productName\uf8ff")
            .get()
            .addOnSuccessListener { snapshot ->
                val searchProduct = ArrayList<Product>()
                if (snapshot != null) {
                    val documents = snapshot.documents
                    documents.forEach {
                        val sproducts = it.toObject(Product::class.java)
                        if (sproducts != null) {

                            searchProduct.add(sproducts!!)
                            Log.d("SearchsProd", "$sproducts")
                        }else{
                            Log.d("ErrorSearchsProd","$sproducts")
                        }
                    }
                }else{
                    Log.d("ErrorSearchsProd","$snapshot")
                }
                products.value = searchProduct

            }

         */
/*
        firestore.collection("Products/Stock/OutOfDate").orderBy("ProductBarcode")
            .startAt(productName).endAt("$productName\uf8ff")
            .get()
            .addOnSuccessListener { snapshot ->
                val searchProduct = ArrayList<Product>()
                if (snapshot != null) {
                    val documents = snapshot.documents
                    documents.forEach {
                        val sproducts = it.toObject(Product::class.java)
                        if (sproducts != null) {

                            searchProduct.add(sproducts!!)
                            Log.d("SearchsProd", "$sproducts")
                        }else{
                            Log.d("ErrorSearchsProd","$sproducts")
                        }
                    }
                }else{
                    Log.d("ErrorSearchsProd","$snapshot")
                }
                products.value = searchProduct

            }

 */
            }


    



   internal var product:MutableLiveData<ArrayList<Product>>
    get() {return  products }
    set(value){ products = value}
}








