package home.savers.hsdatebook


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import home.savers.hsdatebook.Calendar.Date
import home.savers.hsdatebook.FireBase.*
import home.savers.hsdatebook.FireBase.ManagerCheck


import home.savers.hsdatebook.RecyclerView.productRec
import kotlinx.android.synthetic.main.main_screen.*
import kotlinx.android.synthetic.main.manager_login_confirm_create.*
import kotlinx.android.synthetic.main.manager_login_confirm_create.view.*


class Main_Screen : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_screen)
        productRec = ArrayList()
        Date = ArrayList()




        getProductsRT()




       // val RecyclerAdaptor = RecyclerAdaptor(productRec!!, this)
      //  Product_RecycleView.layoutManager = LinearLayoutManager(this)
      // Product_RecycleView.adapter = RecyclerAdaptor



        Product_add_Main.setOnClickListener {
            val intent = Intent(this,Create_Product_Screen::class.java)
            startActivity(intent)
        }
        View_All_Dates.setOnClickListener{
            val intent = Intent(this,ViewAllProducts::class.java)
           startActivity(intent)
        }
/*
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


*/


        Log.d("value1","${productRec}")
//        Update()
    }
    fun Update(){
        //readCalInfo()
      //  productRec?.clear()
      //  getProductsRT()
     //   Product_RecycleView.adapter!!.notifyDataSetChanged()
    }

    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
         super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main_screen_tab,menu)
        val item2: MenuItem = menu!!.findItem(R.id.Update1)
        item2.isVisible = false
        val item3: MenuItem = menu!!.findItem(R.id.Update2)
        item3.isVisible = false



        val item:MenuItem = menu.findItem(R.id.Menu_LogOut)
        item.setShowAsAction(2)


        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.Menu_LogOut -> {
                val intent = Intent(this, StartUp_Screen::class.java)
                startActivity(intent)
                setContentView(R.layout.login_screen)
            }
            R.id.ManagerTools -> {
                val mDialView =
                    LayoutInflater.from(this).inflate(R.layout.manager_login_confirm_create, null)
                val mBuilder = AlertDialog.Builder(this)
                    .setView(mDialView)
                    .setTitle("Manager Login")
                val mAlertDialog = mBuilder.show()

                mAlertDialog.Manager_Confirm.setOnClickListener {
                    val managerun = mDialView.Manager_Username_Login.text.toString()
                    val managersn = mDialView.Manager_StaffNumber_Login.text.toString()

                    if (managerun.isEmpty() && managersn.isEmpty() && managersn.isNullOrBlank()) {
                        Toast.makeText(
                            baseContext,
                            "Please Enter The Information",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (managerun.isEmpty()) {
                        Toast.makeText(baseContext, "Please Enter UserName", Toast.LENGTH_SHORT)
                            .show()
                    } else if (managersn.isEmpty()) {
                        Toast.makeText(baseContext, "Please Enter Staff Number", Toast.LENGTH_SHORT)
                            .show()
                    } else if (managersn.isDigitsOnly()) {
                        CheckIfManager(managerun, managersn)
                        Toast.makeText(baseContext, "Manager : $ManagerCheck", Toast.LENGTH_SHORT)
                            .show()

                        //if manager is true account is added and returned to login so account can be logged into
                        if (ManagerCheck) {
                            mAlertDialog.dismiss()
                            val intent = Intent(this, Account_View::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(baseContext, "Manager Login Failed", Toast.LENGTH_SHORT)
                                .show()
                            val intent = Intent(this, Main_Screen::class.java)
                            startActivity(intent)
                        }
                    } else {
                        Toast.makeText(
                            this,
                            "Please Enter valid Staff Number - Digits Only",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }
            }

        }

        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {

        super.onResume()


    }

    override fun onBackPressed() {
        return
    }


}
