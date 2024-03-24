package com.example.crudrealtimeclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.crudrealtimeclient.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setOnClickListener {
            val searchPhone: String = binding.searchPhone.text.toString()
            if (searchPhone.isNotEmpty()) {
                readData(searchPhone)
            } else {
                Toast.makeText(this, "Please enter the phone number", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(phoneNumber: String) {
        databaseReference = FirebaseDatabase.getInstance("https://crud-realtime-client-7c41a-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Vehicle Information")
        databaseReference.child(phoneNumber).get().addOnSuccessListener {
            if (it.exists()) {
                val ownName = it.child("ownerName").value
                val vehicleBrand = it.child("vehicleBrand").value
                Toast.makeText(this, "Result Found", Toast.LENGTH_SHORT).show()
                binding.searchPhone.text.clear()
                binding.readName.text = ownName.toString()
                binding.readBrand.text = vehicleBrand.toString()
            } else {
                Toast.makeText(this, "Not found data", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Some thing went wrong", Toast.LENGTH_SHORT).show()
        }
    }
}