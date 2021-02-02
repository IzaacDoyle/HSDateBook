package home.savers.hsdatebook

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Switch
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import home.savers.hsdatebook.Product.FBProductDetailsViewModel
import java.security.Key

class Product_View_Update : AppCompatActivity(){


    private lateinit var viewModel: FBProductDetailsViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.display_product_update)
      //  val key = intent.getStringExtra("Key")
      //  Log.d("key",key)



        viewModel = ViewModelProviders.of(this).get(FBProductDetailsViewModel::class.java)
        viewModel.product.observe(this, Observer { it ->



        })

       // Toast.makeText(applicationContext, key,Toast.LENGTH_SHORT).show()






    }

    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_screen_tab, menu)
        val item: MenuItem = menu.findItem(R.id.Menu_LogOut)
        val item1: MenuItem = menu.findItem(R.id.ManagerTools)
        val item2: MenuItem = menu.findItem(R.id.Update2)

        item.isVisible = false
        item1.isVisible = false
        item2.setShowAsAction(2)
        item2.title = "Update"



        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here.
        val id = item.itemId
        
        if (id == R.id.Update) {

            return true
        }




        return super.onOptionsItemSelected(item)

    }
}