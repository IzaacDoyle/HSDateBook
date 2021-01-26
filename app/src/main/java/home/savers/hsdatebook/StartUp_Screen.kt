package  home.savers.hsdatebook


import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.provider.Settings.Global
import android.util.Log
import android.view.Menu
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.QuerySnapshot
import home.savers.hsdatebook.DataClass.HSAccount
import home.savers.hsdatebook.FireBase.*
import home.savers.hsdatebook.RecyclerView.productRec
import kotlinx.android.synthetic.main.login_screen.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.log


class StartUp_Screen: AppCompatActivity() {

    lateinit var userData: MutableList<HSAccount>






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen)

        GetFBAcc()
       // readCalInfo()


        Toast.makeText(this,"${listofLoginStaff}",Toast.LENGTH_SHORT).show()
        listofLoginStaff = ArrayList()
        listofLoginmanager = ArrayList()








//login needs to be changed so that it works

        Login_Sign_In.setOnClickListener {


                val staffNumers = findViewById<EditText>(R.id.StaffNumber_Login)
                val usernames = findViewById<EditText>(R.id.UserName_Login)

            val username = usernames.text.toString().toLowerCase(Locale.ROOT)
                val staffNumber = staffNumers.text.toString().toLowerCase(Locale.ROOT)
            if (username.isEmpty() && staffNumber.isEmpty()) {
                    Toast.makeText(baseContext, "Please Enter The Information", Toast.LENGTH_SHORT)
                        .show()
                } else if (username.isEmpty()) {
                    Toast.makeText(baseContext, "Please Enter UserName", Toast.LENGTH_SHORT).show()
                } else if (staffNumber.isEmpty()) {
                    Toast.makeText(baseContext, "Please Enter Staff Number", Toast.LENGTH_SHORT)
                        .show()
                } else if (staffNumber.isDigitsOnly()) {

                   FBLogin(username, staffNumber)


                            if (UserCheck || ManagerCheck) {
                                Log.d("Login", "Login should work")
                                finish()
                                val intent = Intent(this, Main_Screen::class.java)
                                listofLoginStaff!!.clear()
                                listofLoginmanager!!.clear()
                                startActivity(intent)
                                finish()
                            }



                } else {

                    Toast.makeText(
                        this,
                        "Please Enter valid Staff Number - Digits Only",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }











            Login_Create_Account.setOnClickListener {
                val intent = Intent(this, Create_NewUser_Screen::class.java)
                startActivity(intent)
                setContentView(R.layout.add_account)


            }

            db.collection("users")
                .get()
                .addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
                    if (task.isSuccessful) {
                        for (document in task.result!!) {
                            Log.d("test", document.id + " => " + document.data)
                        }
                    } else {
                        Log.w("test", "Error getting documents.", task.exception)
                    }
                })


            //create account
            /*
        val usernames = findViewById<TextView>(R.id.!!!!!!!)
            val staffNumers = findViewById<TextView>(R.id.!!!!!!!!!)
         FBAddUser(usernames.text.toString(), staffNumers.text.toString())
         */


        }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main_screen_tab,menu)
        return false
    }









}




