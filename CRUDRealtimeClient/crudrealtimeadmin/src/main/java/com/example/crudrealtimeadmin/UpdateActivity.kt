package com.example.crudrealtimeadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.crudrealtimeadmin.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.updateButton.setOnClickListener{
            val refPhoneNumber = binding.referencePhone.text.toString()
            val updateOwnerName = binding.referenceName.text.toString()
            val updateBrandName = binding.referenceBrand.text.toString()
            if (refPhoneNumber.isNotEmpty() and updateBrandName.isNotEmpty() and updateOwnerName.isNotEmpty()) {
                updateData(refPhoneNumber, updateOwnerName, updateBrandName)
            } else {

            Toast.makeText(this, "Please fill all.", Toast.LENGTH_SHORT).show()
            }
        }
        binding.backButton.setOnClickListener {
            val intent = Intent(this@UpdateActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun updateData(phoneNumber: String, ownerName: String, BrandName: String) {
        databaseReference = FirebaseDatabase
            .getInstance("https://crud-realtime-client-7c41a-default-rtdb.asia-southeast1.firebasedatabase.app")
            .getReference("Vehicle Information")

        val productData = mapOf(
            "ownerName" to ownerName,
            "phoneNumber" to phoneNumber,
            "vehicleBrand" to BrandName)

        databaseReference.child(phoneNumber)
            .updateChildren(productData)
            .addOnSuccessListener {
            binding.referenceName.text.clear()
            binding.referencePhone.text.clear()
            binding.referenceBrand.text.clear()
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Unable to update", Toast.LENGTH_SHORT).show()
        }
    }
}