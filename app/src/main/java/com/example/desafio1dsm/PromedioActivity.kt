package com.example.desafio1dsm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.desafio1dsm.databinding.ActivityPromedioBinding
import com.example.desafio1dsm.databinding.ActivitySalarioBinding
import com.google.android.material.textfield.TextInputLayout

class PromedioActivity: AppCompatActivity() {

    private lateinit var binding: ActivityPromedioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPromedioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalcular.setOnClickListener {
            calcular()
        }
    }

    private fun calcular(){
        val nombre = binding.tilNombre.editText?.text.toString()

        val calif1 = validarCampo(binding.tilCalif1, 0.0, 100.0, "Ingresa una calificación", "Calificación debe ser de 0 a 10") ?: return
        val porc1 = validarCampo(binding.tilPorc1, 0.0, 100.0, "Ingresa un porcentaje", "Porcentaje debe ser de 0 a 100") ?: return
        val calif2 = validarCampo(binding.tilCalif2, 0.0, 10.0, "Ingresa una calificación", "Calificación debe ser de 0 a 10") ?: return
        val porc2 = validarCampo(binding.tilPorc2, 0.0, 100.0, "Ingresa un porcentaje", "Porcentaje debe ser de 0 a 100") ?: return
        val calif3 = validarCampo(binding.tilCalif3, 0.0, 10.0, "Ingresa una calificación", "Calificación debe ser de 0 a 10") ?: return
        val porc3 = validarCampo(binding.tilPorc3, 0.0, 100.0, "Ingresa un porcentaje", "Porcentaje debe ser de 0 a 100") ?: return
        val calif4 = validarCampo(binding.tilCalif4, 0.0, 10.0, "Ingresa una calificación", "Calificación debe ser de 0 a 10") ?: return
        val porc4 = validarCampo(binding.tilPorc4, 0.0, 100.0, "Ingresa un porcentaje", "Porcentaje debe ser de 0 a 100") ?: return
        val calif5 = validarCampo(binding.tilCalif5, 0.0, 10.0, "Ingresa una calificación", "Calificación debe ser de 0 a 10") ?: return
        val porc5 = validarCampo(binding.tilPorc5, 0.0, 100.0, "Ingresa un porcentaje", "Porcentaje debe ser de 0 a 100") ?: return

        val promedio = calcularPromedioNota(calif1,porc1,calif2,porc2,calif3,porc3,calif4,porc4,calif5,porc5)
        val promedioPorcentaje = (porc1+porc2+porc3+porc4+porc5)
        binding.tvResultado.text = "$nombre\n El promedio de las calificaciones es: ${String.format("%.2f", promedio)} del ${String.format("%.2f", promedioPorcentaje)}%"

    }

    private fun validarCampo(
        til: TextInputLayout,
        min: Double,
        max: Double,
        mensajeVacio: String,
        mensajeRango: String
    ): Double? {
        val valor = til.editText?.text.toString().toDoubleOrNull()
        return when {
            valor == null -> {
                til.error = mensajeVacio
                null
            }
            valor !in min..max -> {
                til.error = mensajeRango
                null
            }
            else -> {
                til.error = null
                valor
            }
        }
    }

    private fun calcularPromedioNota(
        calif1: Double, porc1: Double,
        calif2: Double, porc2: Double,
        calif3: Double, porc3: Double,
        calif4: Double, porc4: Double,
        calif5: Double, porc5: Double
    ): Double{
        return (calif1*porc1/100)+(calif2*porc2/100)+(calif3*porc3/100)+(calif4*porc4/100)+(calif5*porc5/100)
    }
}