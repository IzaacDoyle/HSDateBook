package home.savers.hsdatebook

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import home.savers.hsdatebook.DataClass.Product
import home.savers.hsdatebook.Product.FBProductDetailsViewModel
import home.savers.hsdatebook.RecyclerView.ProductRecycleAdaptor
import kotlinx.android.synthetic.main.recycleview.*
import kotlinx.android.synthetic.main.view_all_products_screen.*
import java.time.format.DateTimeFormatter
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




                // fix search feature
                Search_Product_Details.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        if (it.contains(query)){
                            adapter.filter.filter(query)
                        }else
                        {
                            Toast.makeText(applicationContext, "No Product Of this Name or Type",Toast.LENGTH_SHORT).show()
                        }
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        adapter.filter.filter(newText.toString())
                        return false
                    }

                }) 


// fix search by Date
                SortBy_Products.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }

                    override fun onItemSelected(
                        parent: AdapterView<*>?, view: View?, position: Int, id: Long
                    ) {
                        if (parent != null) {
                            if (position == 0) {
                                val myAdapter = ProductRecycleAdaptor(it, applicationContext)
                                RecycleView.layoutManager = LinearLayoutManager(
                                    applicationContext
                                )
                                RecycleView.adapter = myAdapter
                                // Product_RecycleView.adapter?.notifyDataSetChanged()
                            } else if (position == 1) {

                                // val sortByDate = ArrayList<Product>(it.sortedBy{} )
                                val myAdapter = ProductRecycleAdaptor(it, applicationContext)
                                RecycleView.layoutManager = LinearLayoutManager(
                                    applicationContext
                                )
                                RecycleView.adapter = myAdapter
                                //Product_RecycleView.adapter?.notifyDataSetChanged()


                            } else if (position == 2) {


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
                            } else if (position == 3) {
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

                            } else if (position == 4) {

                            } else if (position == 5) {

                            } else if (position == 6) {

                            } else if (position == 7) {

                            } else if (position == 8) {

                            }

                        }
                    }
                }
            }

        })




    }







}