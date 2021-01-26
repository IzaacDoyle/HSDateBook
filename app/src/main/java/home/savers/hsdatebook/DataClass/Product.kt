package home.savers.hsdatebook.DataClass

import android.accessibilityservice.GestureDescription
import home.savers.hsdatebook.Calendar.Date
import home.savers.hsdatebook.RecyclerView.productRec
import java.security.Timestamp

data class Product( var Key: String,
                    var Date:String,
                    var Aisle:String,
                    var ProductName:String,
                    var Quantity:String,
                    var ProductBarcode:String)
{
    constructor(): this("","","","","","")


}
