package home.savers.hsdatebook.RecyclerView

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import home.savers.hsdatebook.DataClass.HSAccount
import home.savers.hsdatebook.DataClass.MainRecycleProd
import home.savers.hsdatebook.R

import kotlinx.android.synthetic.main.productdisplay.view.*

import java.util.ArrayList




class RecyclerAdaptorAccount(private val accounts: ArrayList<HSAccount>, private val context: Context):
    RecyclerView.Adapter<RecyclerAdaptorAccount.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindProducts(accounts: HSAccount){



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.view_accounts_details, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdaptorAccount.ViewHolder, position: Int) {
        holder.bindProducts(accounts[position])
    }

    override fun getItemCount(): Int {
        return accounts.size
    }




}



