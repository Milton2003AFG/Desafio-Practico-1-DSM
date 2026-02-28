package com.example.desafio1dsm

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.desafio1dsm.databinding.ActivityMainBinding
import com.example.desafio1dsm.databinding.ActivitySalarioBinding

class SalarioActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySalarioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySalarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalcular.setOnClickListener {
            calcular()
        }
    }

    private fun calcular(){
        val nombre = binding.tilNombre.editText?.text.toString()
        val salario = binding.tilSalario.editText?.text.toString()

        if(nombre.isEmpty()){
            binding.tilNombre.error = "Ingresa un nombre"
            return
        }

        if(salario.isEmpty()){
            binding.tilSalario.error = "Ingresa un salario"
            return
        }

        if(salario.toDouble() < 0){
            binding.tilSalario.error = "El salario debe ser positivo"
            return
        }

        binding.tilNombre.error = null
        binding.tilSalario.error = null

        val resultado = calcularDescuentos(nombre, salario.toDouble())

        binding.tvNombreResultado.text = "Nombre: ${resultado.nombre}"
        binding.tvSalario.text = "Salario: $${String.format("%.2f", resultado.salario)}"
        binding.tvAfp.text = "AFP: $${String.format("%.2f", resultado.afp)}"
        binding.tvIsss.text = "ISSS: $${String.format("%.2f",resultado.isss)}"
        binding.tvRenta.text = "Renta: $${String.format("%.2f", resultado.renta)}"
        binding.tvSalarioNeto.text = "Salario Neto: $${String.format("%.2f", resultado.salarioNeto)}"
    }

    private fun calcularDescuentos(nombre: String, salario: Double): ResultadoSalario{
        val afp = salario*0.0725
        val isss = salario*0.03

        val renta = when{
            salario <= 472.0 -> 0.0
            salario <= 895.24 -> (salario - 472.0) * 0.10 + 17.67
            salario <= 2038.10 -> (salario - 895.24) * 0.20 + 60.0
            else -> (salario - 2038.10) * 0.30 + 288.57
        }

        return ResultadoSalario(
            nombre = nombre,
            salario = salario,
            afp = afp,
            isss = isss,
            renta = renta,
            salarioNeto = salario-afp-isss-renta
        )
    }
}

data class ResultadoSalario(
    val nombre: String,
    val salario: Double,
    val afp: Double,
    val isss: Double,
    val renta: Double,
    val salarioNeto: Double
)