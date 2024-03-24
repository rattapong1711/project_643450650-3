package com.example.crudrealtimeadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.crudrealtimeadmin.databinding.ActivityDeleteBinding
import com.example.crudrealtimeadmin.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.deleteButton.setOnClickListener {
            val phoneNumber = binding.deletePhone.text.toString()
            if (phoneNumber.isNotEmpty()) {
                delete(phoneNumber)
            } else {
                Toast.makeText(this, "Please Fill phone number", Toast.LENGTH_SHORT).show()
            }
        }
        binding.backButton.setOnClickListener {
            val intent = Intent(this@DeleteActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun delete(phoneNumber: String) {
        databaseReference = FirebaseDatabase.getInstance("https://crud-realtime-client-7c41a-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Vehicle Information")
        databaseReference.child(phoneNumber).removeValue().addOnSuccessListener {
            binding.deletePhone.text.clear()
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Unable to delete", Toast.LENGTH_SHORT).show()
        }
    }
}