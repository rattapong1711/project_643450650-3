package com.example.crudrealtimeadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.crudrealtimeadmin.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UploadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUploadBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            val name = binding.uploadOwnerName.text.toString()
            val operator = binding.uploadvehicleBrand.text.toString()
            val number = binding.uploadPhone.text.toString()

            databaseReference = FirebaseDatabase
                .getInstance("https://crud-realtime-client-7c41a-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference("Vehicle Information")
            val vehicleData = VehicleData(name, operator, number)
            databaseReference.child(number).setValue(vehicleData).addOnSuccessListener {
                binding.uploadOwnerName.text.clear()
                binding.uploadvehicleBrand.text.clear()
                binding.uploadPhone.text.clear()

                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@UploadActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }

        }
        binding.backButton.setOnClickListener {
            val intent = Intent(this@UploadActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}