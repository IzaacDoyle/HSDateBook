package home.savers.hsdatebook.Product

import android.util.Log
import android.widget.SearchView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import home.savers.hsdatebook.DataClass.HSAccount
import home.savers.hsdatebook.DataClass.Product

class FBAccountViewModel : ViewModel() {
    private lateinit var firestore: FirebaseFirestore
    private var accounts: MutableLiveData<ArrayList<HSAccount>> = MutableLiveData<ArrayList<HSAccount>>()



    init {
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
        getProducts()

    }

    override fun onCleared() {
        super.onCleared()
    }

    private fun getProducts() {
        firestore.collection("Accounts/Users/Staff").addSnapshotListener{
                snapshot, e->

            if (e != null){
                Log.d("Account","Failed", e)
                return@addSnapshotListener
            }
            val allAccounts = ArrayList<HSAccount>()
            if (snapshot != null){
                val documents =    snapshot.documents
                documents.forEach{
                    val account= it.toObject(HSAccount::class.java)
                    if (account !=null)
                        allAccounts.add(account)
                }
            }
            accounts.value = allAccounts
            Log.d("Account","all prod $allAccounts")
        }
        firestore.collection("Accounts/Users/Manager").addSnapshotListener{
                snapshot, e->

            if (e != null){
                Log.d("Product","Failed", e)
                return@addSnapshotListener
            }
            val allProducts = ArrayList<HSAccount>()
            if (snapshot != null){
                val documents =    snapshot.documents
                documents.forEach{
                    val account= it.toObject(HSAccount::class.java)
                    if (account !=null)
                        allProducts.add(account)
                }
            }
            accounts.value = allProducts
            Log.d("Products","all prod $allProducts")
        }

    }




    internal var account: MutableLiveData<ArrayList<HSAccount>>
        get() {return  accounts }
        set(value){ accounts = value}
}


