package com.example.replicaroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.replicaroom.dao.DatabaseBuilder
import com.example.replicaroom.databinding.ActivityMainBinding
import com.example.replicaroom.entity.City
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




    }
}