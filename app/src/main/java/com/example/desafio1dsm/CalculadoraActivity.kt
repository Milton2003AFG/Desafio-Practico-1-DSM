package com.example.desafio1dsm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.desafio1dsm.databinding.ActivityCalculadoraBinding

class CalculadoraActivity: AppCompatActivity() {
    private lateinit var binding: ActivityCalculadoraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculadoraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSuma.setOnClickListener { operar("+") }
        binding.btnResta.setOnClickListener { operar("-") }
        binding.btnMultiplicacion.setOnClickListener { operar("*") }
        binding.btnDivision.setOnClickListener { operar("/") }
        binding.btnExponente.setOnClickListener { operar("^") }
        binding.btnRaiz.setOnClickListener { operar("√") }
    }

    private fun operar(operacion: String) {
        val numero1 = binding.tilNumero1.editText?.text.toString().toDoubleOrNull()

        if (numero1 == null) {
            binding.tilNumero1.error = "Ingresa un número"
            return
        }
        binding.tilNumero1.error = null

        // Raíz solo necesita número 1
        if (operacion == "√") {
            if (numero1 < 0) {
                binding.tilNumero1.error = "No se puede calcular raíz de un número negativo"
                return
            }
            val resultado = Math.sqrt(numero1)
            binding.tvResultado.text = "√$numero1 = ${String.format("%.5f", resultado)}"
            return
        }

        val numero2 = binding.tilNumero2.editText?.text.toString().toDoubleOrNull()
        if (numero2 == null) {
            binding.tilNumero2.error = "Ingresa un número"
            return
        }
        binding.tilNumero2.error = null

        // Validar división entre cero
        if (operacion == "/" && numero2 == 0.0) {
            binding.tilNumero2.error = "No se puede dividir entre cero"
            return
        }

        val resultado = calcular(numero1, numero2, operacion)
        binding.tvResultado.text = "$numero1 $operacion $numero2 = ${String.format("%.5f", resultado)}"
    }

    private fun calcular(numero1: Double, numero2: Double, operacion: String): Double {
        return when (operacion) {
            "+" -> numero1 + numero2
            "-" -> numero1 - numero2
            "*" -> numero1 * numero2
            "/" -> numero1 / numero2
            "^" -> Math.pow(numero1, numero2)
            else -> 0.0
        }
    }
}