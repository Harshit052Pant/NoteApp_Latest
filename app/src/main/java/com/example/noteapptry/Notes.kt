package com.example.noteapptry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.noteapptry.databinding.ActivityNotesBinding
import com.google.firebase.database.FirebaseDatabase

class Notes : AppCompatActivity() {

    var db = FirebaseDatabase.getInstance()
    var myRef = db.getReference("data")

    lateinit var binding:ActivityNotesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
//

        binding.saveData.setOnClickListener{
            saveData()
        }
    }

    fun saveData() {

        var newId = myRef.push().key
        var Title = binding.mainTitle.text.toString()
        var Description = binding.mainDescription.text.toString()

        if (newId != null) {
            myRef.child(newId).child("title").setValue(Title)
            myRef.child(newId).child("description").setValue(Description)
        }


    }
}