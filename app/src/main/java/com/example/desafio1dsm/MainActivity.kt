package com.example.desafio1dsm

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.desafio1dsm.databinding.ActivityMainBinding
import com.example.desafio1dsm.databinding.ActivityPromedioBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnPromedio.setOnClickListener {
            val intent = Intent(this, PromedioActivity::class.java)
            startActivity(intent)
        }

        binding.btnSalario.setOnClickListener {
            val intent = Intent(this, SalarioActivity::class.java)
            startActivity(intent)
        }

        binding.btnCalculadora.setOnClickListener {
            val intent = Intent(this, CalculadoraActivity::class.java)
            startActivity(intent)
        }
    }
}