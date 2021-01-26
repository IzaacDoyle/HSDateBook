package home.savers.hsdatebook

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import home.savers.hsdatebook.FireBase.*

import kotlinx.android.synthetic.main.add_account.*
import kotlinx.android.synthetic.main.manager_login_confirm_create.*
import kotlinx.android.synthetic.main.manager_login_confirm_create.view.*



class Create_NewUser_Screen: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_account)



        Confirm_Creeate.setOnClickListener {

            val username_create = findViewById<EditText>(R.id.UserName_Create)
            val staffnumber_create = findViewById<EditText>(R.id.StaffNumber_Create)

            var username = username_create.text.toString()
            var staffnumber = staffnumber_create.text.toString()

            if (username.isEmpty() && staffnumber.isEmpty()) {
                Toast.makeText(this, "Please Enter Information", Toast.LENGTH_SHORT).show()
            }else if (staffnumber.isDigitsOnly()){
                newuserAccUN = username
                newuserAccSN = staffnumber

                val mDialView = LayoutInflater.from(this).inflate(R.layout.manager_login_confirm_create, null)
                val mBuilder = AlertDialog.Builder(this)
                    .setView(mDialView)
                    .setTitle("Manager Login")
                val mAlertDialog = mBuilder.show()

                mAlertDialog.Manager_Confirm.setOnClickListener {
                    val managerun = mDialView.Manager_Username_Login.text.toString()
                    val managersn = mDialView.Manager_StaffNumber_Login.text.toString()

                    if (managerun.isEmpty() && managersn.isEmpty() && managersn.isNullOrBlank()){
                        Toast.makeText(baseContext, "Please Enter The Information", Toast.LENGTH_SHORT).show()
                    }else if (managerun.isEmpty()){
                        Toast.makeText(baseContext, "Please Enter UserName", Toast.LENGTH_SHORT).show()
                    }else if (managersn.isEmpty() ){
                        Toast.makeText(baseContext, "Please Enter Staff Number", Toast.LENGTH_SHORT).show()
                    }else if (managersn.isDigitsOnly()){
                          ManagerCheck(managerun,managersn)


                        //if manager is true account is added and returned to login so account can be logged into
                        if (ManagerCheck){
                            mAlertDialog.dismiss()
                            finish()
                            val intent = Intent(this,StartUp_Screen::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(baseContext,"Manager Login Failed",Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(this, "Please Enter valid Staff Number - Digits Only", Toast.LENGTH_SHORT).show()

                    }
                }
            }else{

                Toast.makeText(this, "Please Enter valid Staff Number - Digits Only", Toast.LENGTH_SHORT).show()
            }






       }


}





    override fun onBackPressed() {
        val intent = Intent(this, StartUp_Screen::class.java)
        startActivity(intent)
        finish()
    }
}