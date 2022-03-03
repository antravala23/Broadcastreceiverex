package com.example.broadcastreceiverex

import android.content.ContentValues
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class ConProviderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_con_provider)
    }

   fun onClickAddDetails(view: View) {

        // class to add values in the database
        val values = ContentValues()

        // fetching text from user
        values.put(MyConProvider.name, (findViewById<EditText>(R.id.textName)).
        text.toString())

       // inserting into database through content URI
          contentResolver.insert(MyConProvider.CONTENT_URI, values)

       // displaying a toast message
        Toast.makeText(baseContext, "New Record Inserted", Toast.LENGTH_LONG).show()
   }

    fun onClickShowDetails(view: View) {
        // inserting complete table details in this text field
        var resultView: TextView = findViewById(R.id.res)

        // creating a cursor object of the
        // content URI
        val cursor = contentResolver.query(Uri.parse("content://com.demo.con.provider/users"), null, null, null, null)

        // iteration of the cursor
        // to print whole table
        if(cursor!!.moveToFirst()) {
            val strBuild= StringBuilder()
            while (!cursor.isAfterLast) {
                strBuild.append("\n"+cursor.getString(cursor.getColumnIndex("id"))+ "-"+ cursor.getString(cursor.getColumnIndex("name")))
                cursor.moveToNext()
            }
            resultView.text = strBuild
        }
        else {
            resultView.text = "No Records Found"
        }
    }

}