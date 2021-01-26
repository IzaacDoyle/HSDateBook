package home.savers.hsdatebook

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi

import androidx.appcompat.app.AppCompatActivity
import home.savers.hsdatebook.Calendar.*
import home.savers.hsdatebook.FireBase.FBCreateNEWProd
import kotlinx.android.synthetic.main.create_newproduct_screen.*
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

class Create_Product_Screen : AppCompatActivity() {



    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_newproduct_screen)

        calendarView = findViewById<CalendarView>(R.id.Calandar_Dates)
        val dateView = findViewById<TextView>(R.id.Cal_Date_View)
        val aisleArray = resources.getStringArray(R.array.Aisle)
        var spinnerText: String = ""
        //readCalInfo()


        calendarView.setOnDateChangeListener { calendarView: CalendarView, Year: Int, Month: Int, Day: Int ->

            Log.d("Cal", "Cal Changed ${Year} /${Month}/ ${Day}")
            dateView.text = "Date Selected : ${Day}/${Month}/${Year}"
            Days = Day.toString()
            Months = Month.toString()
            Years = Year.toString()
        }


        val spinner = findViewById<Spinner>(R.id.Product_Ailse_Select)
        if (spinner != null) {
            val adapter =
                ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, aisleArray)
            spinner.adapter = adapter

            Product_Ailse_Select.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?, position: Int, id: Long
                ) {
                    if (parent != null) {
                        if (position == 0) {
                            return
                        } else {
                            spinnerText = parent.getItemAtPosition(position).toString()
                        }
                    }
                }
            }
        }



        Create_Add_Product.setOnClickListener {
            val Aisle = spinnerText
            val CalDate = Cal_Date_View.text.toString()
            val ProductName = Create_Product_Name.text.toString()
            val ProductBarcode = Create_Product_Barcode.text.toString()
            val Quantity = Create_Product_Quantity.text.toString()
            if (CalDate.isEmpty()) {
                Toast.makeText(this, "Please Select A Date", Toast.LENGTH_SHORT).show()
            } else if (ProductName.isEmpty()) {
                Toast.makeText(this, "Please Enter A Product Name", Toast.LENGTH_SHORT).show()
            } else if (ProductBarcode.isEmpty()) {
                Toast.makeText(this, "Please Enter A Product Barcode", Toast.LENGTH_SHORT).show()
            } else if (Quantity.isEmpty()) {
                Toast.makeText(this, "Please Select A Product Quantity", Toast.LENGTH_SHORT).show()
            } else if (Aisle.isEmpty()) {
                Toast.makeText(this, "Please Select An Aisle", Toast.LENGTH_SHORT).show()
            } else {



                FBCreateNEWProd(

                    Date = "$Days/$Months/$Years",
                    "$ProductName",
                    "$Quantity",
                    "$ProductBarcode",
                    "$Aisle"
                )

                Log.d("Product", "Success")

                val intent = Intent(applicationContext, Main_Screen::class.java)
                startActivity(intent)



            }
        }


    }
}





