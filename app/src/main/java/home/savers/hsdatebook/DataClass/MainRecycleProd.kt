package home.savers.hsdatebook.DataClass

data class MainRecycleProd (val Key:String,val Day :String, val Month:String , val Year :String, val ProductName : String,val Quantity:String){
    fun Date():String{
        return "$Day/$Month/$Year"
    }

}
