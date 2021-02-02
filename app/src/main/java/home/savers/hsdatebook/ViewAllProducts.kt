package home.savers.hsdatebook

import android.app.SearchManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isEmpty
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import home.savers.hsdatebook.DataClass.Product
import home.savers.hsdatebook.Product.FBProductDetailsViewModel
import home.savers.hsdatebook.RecyclerView.ProductRecycleAdaptor
import kotlinx.android.synthetic.main.display_product_update.*
import kotlinx.android.synthetic.main.recycleview.*
import kotlinx.android.synthetic.main.switch_item.*
import kotlinx.android.synthetic.main.view_all_products_screen.*
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList


class ViewAllProducts :  AppCompatActivity(){

    private lateinit var viewModel: FBProductDetailsViewModel



    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_all_products_screen)
        val sortByProduct = resources.getStringArray(R.array.SortBy)

      //  val productSearch = findViewById<EditText>(R.id.Product_Search)
        var spinnerText: String = ""

        viewModel = ViewModelProviders.of(this).get(FBProductDetailsViewModel::class.java)
        viewModel.product.observe(this, Observer { it ->
            //   products -> RecyclerAdaptor()
            Log.d("Products", "it $it")
            RecycleView.adapter?.notifyDataSetChanged()


            val spinner = findViewById<Spinner>(R.id.SortBy_Products)
            if (spinner != null) {
                val adapter =
                    ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, sortByProduct)
                spinner.adapter = adapter




                // fix search feature onData



              /* Search_Product_Details.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                   override fun onQueryTextSubmit(query: String?): Boolean {
                   // viewModel.SearchProducts(query!!)

                        viewModel.SearchProducts(query!!)
                       Log.d("SearchWord","$query")

                        return true
                   }

                   override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                   }



                })

               */

                Search_Product_Details.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                    override fun onQueryTextSubmit(query: String?): Boolean {
                        if (Search_Product_Details.isEmpty()) {
                            viewModel.getProducts()
                        } else {
                            if (query.isNullOrBlank()) {
                                Toast.makeText(
                                    applicationContext,
                                    "Error With Search",
                                    Toast.LENGTH_SHORT
                                ).show()
                                viewModel.getProducts()
                            } else {
                                viewModel.SearchProductsName(query.toLowerCase())
                                RecycleView.adapter?.notifyDataSetChanged()
                            }
                        }
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        if (Search_Product_Details.isEmpty()) {
                            viewModel.getProducts()
                        } else {
                            if (newText.isNullOrBlank()){
                                viewModel.getProducts()
                            }else{
                                viewModel.SearchProductsName(newText.toLowerCase())
                                RecycleView.adapter?.notifyDataSetChanged()
                            }
                        }
                        return true
                    }


                })
                if (Search_Product_Details.isEmpty()){
                    viewModel.getProducts()
                    RecycleView.adapter?.notifyDataSetChanged()

                }


               // viewModel.productUpdate()
                //viewModel.productDelete()




// fix search by Date
                SortBy_Products.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }

                    override fun onItemSelected(
                        parent: AdapterView<*>?, view: View?, position: Int, id: Long
                    ) {

                        if (parent != null) {
                            when (position) {
                                0 -> {
                                    val myAdapter = ProductRecycleAdaptor(it, applicationContext)
                                    RecycleView.layoutManager = LinearLayoutManager(
                                        applicationContext
                                    )
                                    RecycleView.adapter = myAdapter
                                    // Product_RecycleView.adapter?.notifyDataSetChanged()
                                }
                                1 -> {

                                    // val sortByDate = ArrayList<Product>(it.sortedBy{} )
                                    val myAdapter = ProductRecycleAdaptor(it, applicationContext)
                                    RecycleView.layoutManager = LinearLayoutManager(
                                        applicationContext
                                    )
                                    RecycleView.adapter = myAdapter
                                    //Product_RecycleView.adapter?.notifyDataSetChanged()


                                }
                                2 -> {


                                    val sortByDate = ArrayList<Product>(it.sortedBy { it.ProductName })
                                    val myAdapter = ProductRecycleAdaptor(
                                        sortByDate,
                                        applicationContext
                                    )
                                    RecycleView.layoutManager = LinearLayoutManager(
                                        applicationContext
                                    )
                                    RecycleView.adapter = myAdapter
                                    RecycleView.adapter?.notifyDataSetChanged()
                                }
                                3 -> {
                                    val sortByDate = ArrayList<Product>(it.sortedBy { it.Aisle })
                                    val myAdapter = ProductRecycleAdaptor(
                                        sortByDate,
                                        applicationContext
                                    )
                                    RecycleView.layoutManager = LinearLayoutManager(
                                        applicationContext
                                    )
                                    RecycleView.adapter = myAdapter
                                    RecycleView.adapter?.notifyDataSetChanged()

                                }
                                4 -> {

                                }
                                5 -> {

                                }
                                6 -> {

                                }
                                7 -> {

                                }
                                8 -> {

                                }
                            }

                        }
                    }
                }
            }

        })




    }







}