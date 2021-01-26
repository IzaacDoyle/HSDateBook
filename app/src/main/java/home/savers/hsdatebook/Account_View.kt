package home.savers.hsdatebook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import home.savers.hsdatebook.Product.FBAccountViewModel
import home.savers.hsdatebook.Product.FBProductDetailsViewModel
import home.savers.hsdatebook.RecyclerView.ProductRecycleAdaptor
import home.savers.hsdatebook.RecyclerView.RecyclerAdaptorAccount
import kotlinx.android.synthetic.main.recycleview.*

class Account_View : AppCompatActivity(){

    private lateinit var viewModel: FBAccountViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycleview)

        viewModel = ViewModelProviders.of(this).get(FBAccountViewModel::class.java)
        viewModel.account.observe(this, Observer { it ->

            val myAdapter = RecyclerAdaptorAccount(it, applicationContext)
            RecycleView.layoutManager = LinearLayoutManager(applicationContext)
            RecycleView.adapter = myAdapter
    })

}
}