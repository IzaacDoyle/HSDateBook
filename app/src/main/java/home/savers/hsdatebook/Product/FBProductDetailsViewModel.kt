package home.savers.hsdatebook.Product

import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.core.view.isNotEmpty
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import home.savers.hsdatebook.Calendar.Days
import home.savers.hsdatebook.Calendar.Months
import home.savers.hsdatebook.Calendar.Years
import home.savers.hsdatebook.DataClass.MainRecycleProd
import home.savers.hsdatebook.DataClass.Product
import home.savers.hsdatebook.FireBase.getProductsRT

import home.savers.hsdatebook.FireBase.update
import home.savers.hsdatebook.RecyclerView.productRec
import java.lang.Character.toLowerCase
import java.util.*
import kotlin.collections.ArrayList


class FBProductDetailsViewModel : ViewModel() {
    private lateinit var firestore: FirebaseFirestore
    private var products: MutableLiveData<ArrayList<Product>> = MutableLiveData<ArrayList<Product>>()



    init {
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
        getProducts()

    }

    override fun onCleared() {
        super.onCleared()
    }

    private fun getProducts() {
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




   internal var product:MutableLiveData<ArrayList<Product>>
    get() {return  products }
    set(value){ products = value}
}






