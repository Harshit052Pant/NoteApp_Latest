package com.example.noteapptry

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.snapshots

class Adapter(var listData:ArrayList<MyData>):RecyclerView.Adapter<Adapter.listViewHolder>() {

    var db: FirebaseDatabase = FirebaseDatabase.getInstance()
    var myRef = db.getReference("data")


    class listViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {

        var titleA:TextView = itemView.findViewById(R.id.title)
        var descriptionA:TextView = itemView.findViewById(R.id.description)
        var deleteData:TextView = itemView.findViewById(R.id.removeData)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): listViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.rcvlayout,parent,false)
        return listViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: listViewHolder, position: Int) {

        holder.titleA.text = listData[position].title
        holder.descriptionA.text = listData[position].description

        var newId = listData[position].iD
        holder.deleteData.setOnClickListener {
           deleteDataAndTraverse(newId)
        }


    }
//    fun deleteDatafun(id:String?){
//
//        fun delete (snapshot: DataSnapshot) {
//            for (i in snapshot.children) {
//                var iD = i.key.toString()
//                if (iD == id){
//                    myRef.child(iD).removeValue()
//                }
//            }
//
//
//        }
//        delete(myRef)
//
//
//    }

    fun deleteDataAndTraverse(id:String?) {
       myRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Traverse through each child node
                for (childSnapshot in dataSnapshot.children) {

                    var iD = childSnapshot.key.toString()
                    if (id==iD)
                        myRef.child(iD).removeValue()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle onCancelled
            }
        })
    }




}