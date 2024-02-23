package com.example.noteapptry

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.noteapptry.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    var listData = arrayListOf<MyData>()
    var db: FirebaseDatabase = FirebaseDatabase.getInstance()
    var myRef = db.getReference("data")
    var adapter:Adapter?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)





        binding.rcvMain.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        getData()

        binding.createNewNote.setOnClickListener {

            startActivity(Intent(this,Notes::class.java))
//            finish()
//            saveData()
        }

//        binding.removeData.setOnClickListener{
//
//            deleteData()
//        }


    }

//
//    fun saveData() {
//
//        var newId = myRef.push().key
//        var Title = binding.mainTitle.text.toString()
//        var Description = binding.mainDescription.text.toString()
//
//        if (newId != null) {
//            myRef.child(newId).child("title").setValue(Title)
//            myRef.child(newId).child("description").setValue(Description)
//        }
//
//
//    }


    fun getData()
    {

        myRef.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                listData.clear()

                for (i in snapshot.children){

                    var id = i.key
                    var newtitle = i.child("title").value.toString()
                    var newdescription = i.child("description").value.toString()

                    var note = MyData(iD = id,title = newtitle, description = newdescription)
                    listData.add(note)

                }

                binding.rcvMain.adapter = Adapter(listData)
                adapter?.notifyDataSetChanged()



            }

            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG,"error"+error.toString())
            }

        }


        )

    }




//
}

