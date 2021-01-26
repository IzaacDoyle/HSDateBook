package home.savers.hsdatebook.FireBase


import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.auth.User
import home.savers.hsdatebook.DataClass.HSAccount





var db = FirebaseFirestore.getInstance()
lateinit var userData: MutableList<HSAccount>
 lateinit var newuserAccUN: String
 lateinit var newuserAccSN: String
  var ManagerCheck:  Boolean = false
  var UserCheck: Boolean = false
val Tag = "AccountFetch"
var listofLoginStaff:ArrayList<HSAccount>?=null
var listofLoginmanager:ArrayList<HSAccount>?=null



fun FBAddUser(username: String, staffnumbers: String) {

    var user = hashMapOf(
        "Username" to username.toLowerCase(),
        "staffNumber" to staffnumbers.toLowerCase()
    )

    db.collection("Accounts/Users/Staff").document(staffnumbers).set(user)

        .addOnSuccessListener {
            Log.d("UserCreated", "FBLogin: User has been added")
        }
}


fun GetFBAcc() {
    db.collection("Accounts/Users/Staff")
        .get()
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (document in task.result!!) {

                    val staffNumber = document.getString("staffNumber").toString()
                    val usernames = document.getString("Username").toString()
                    var Account = HSAccount(usernames.toString(), staffNumber.toString())
                    listofLoginStaff?.add(Account)

                }
                Log.d("Account","$listofLoginStaff")
            }
        }

    db.collection("Accounts/Users/Manager")
        .get()
        .addOnCompleteListener{
            task ->
            if (task.isSuccessful){
                for (document in task.result!!) {

                    val staffNumber = document.getString("staffNumber")
                    val usernames = document.getString("Username")
                    var Account = HSAccount(usernames.toString(), staffNumber.toString())
                    listofLoginmanager?.add(Account)
                }
                Log.d("Account","$listofLoginmanager")

            }
        }
}


fun FBLogin(username: String, staffnumbers: String) {

// could change it so that it always gets data from online when start up and then compairs when button is pressed ??

    val Account =HSAccount(username,staffnumbers)
    Log.d("Account","$listofLoginStaff ")
    Log.d("Account","$Account ")
    if (listofLoginStaff?.contains(HSAccount(username,staffnumbers)) == true){
        Log.d("Account","LoginWorked ")
        UserCheck = true
    }else{
        Log.d("Account","NotWorked")
    }
    Log.d("Account","$listofLoginmanager")
    if (listofLoginmanager?.contains(HSAccount(username,staffnumbers)) == true){
        Log.d("Account","LoginWorked  ")
        ManagerCheck = true
    }else{
        Log.d("Account","NotWorked2")
    }



}





fun ManagerCheck(username: String, staffnumbers: String) {


    db.collection("Accounts/Users/Manager")
        .get()
        .addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
            if (task.isSuccessful) {
                for (document in task.result!!) {
                    Log.d("Manager", document.id + " => " + document.data)
                    val usernames = document.getString("Username")
                    val staffnumber = document.getString("staffNumber")

                    if (username.toLowerCase() == document.getString("Username")
                            ?.toLowerCase() && staffnumbers == document.getString("staffNumber")
                    ) {
                        Log.d("Manager", "worked")

                        //checks to see if Managers Account exists and if so adds account to firebase and returns true
                        if (task.isSuccessful) {
                            ManagerCheck = true
                            FBAddUser(newuserAccUN, newuserAccSN)
                        }

                    } else {
                        Log.d("Manager", "didnt not work ${staffnumbers}")

                    }
                    Log.d("Manager", "$usernames   $staffnumber")

                }
            } else {
                Log.w("Manager", "Error getting documents.", task.exception)

            }


        })
}

fun CheckIfManager(username: String, staffnumbers: String) {


    db.collection("Accounts/Users/Manager")
        .get()
        .addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
            if (task.isSuccessful) {
                for (document in task.result!!) {
                    Log.d("Manager", document.id + " => " + document.data)
                    val usernames = document.getString("Username")
                    val staffnumber = document.getString("staffNumber")

                    if (username.toLowerCase() == document.getString("Username")
                            ?.toLowerCase() && staffnumbers == document.getString("staffNumber")
                    ) {
                        Log.d("Manager", "worked")

                        //checks to see if Managers Account exists and if so adds account to firebase and returns true
                        if (task.isSuccessful) {
                            ManagerCheck = true
                        }

                    } else {
                        Log.d("Manager", "didnt not work ${staffnumbers}")

                    }
                    Log.d("Manager", "$usernames   $staffnumber")

                }
            } else {
                Log.w("Manager", "Error getting documents.", task.exception)

            }


        })
}






















