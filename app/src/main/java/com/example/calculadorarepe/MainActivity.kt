package com.example.calculadorarepe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.calculadorarepe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    var firstValue: Double = 0.0
    var operator: Char = '0'

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.ButtonClear.setOnClickListener{
            binding.editTextNumber.text.clear()
        }

        binding.ButtonDelete.setOnClickListener{
            binding.editTextNumber.setText(binding.editTextNumber.text.dropLast(1))
        }

        binding.ButtonEquals.setOnClickListener{
            when(operator) {
                '+' -> {
                    binding.editTextNumber.setText("${firstValue + binding.editTextNumber.text.toString().toDouble()}")
                }
                '÷' -> {
                    binding.editTextNumber.setText("${firstValue / binding.editTextNumber.text.toString().toDouble()}")
                }
                'x' -> {
                    binding.editTextNumber.setText("${firstValue * binding.editTextNumber.text.toString().toDouble()}")
                }
                '-' -> {
                    binding.editTextNumber.setText("${firstValue - binding.editTextNumber.text.toString().toDouble()}")
                }
                else -> binding.editTextNumber.setText("ERROR")
            }
            operator = '0'
            firstValue = 0.0
        }

        binding.ButtonPercentaje.setOnClickListener{
            binding.editTextNumber.setText(percentage(operator))
        }
    }
    fun percentage(operatorToUse: Char) : String {
        when(operatorToUse) {
            '+' -> { return "${firstValue + (firstValue*binding.editTextNumber.text.toString().toDouble()/100)}"}
            '-' -> { return "${firstValue - (firstValue*binding.editTextNumber.text.toString().toDouble()/100)}"}
            '0' -> { return "${binding.editTextNumber.text.toString().toDouble()/100}"}
            else -> {return "ERROR"}
        }
    }

    fun getOperator(view: View){
        val button = view as Button

        operator = button.text.get(0)


        firstValue = binding.editTextNumber.text.toString().toDouble()
        binding.editTextNumber.text.clear()
    }

    fun numberButtonClicked(view: View){
        val button = view as Button

        if (button.id.equals(binding.ButtonDecimal.id)) {
            Toast.makeText(this, "decimal pressed", Toast.LENGTH_SHORT).show()
            if (!binding.editTextNumber.text.contains(".")) {
                binding.editTextNumber.text.append(button.text)
                Toast.makeText(this, "no previous decimal", Toast.LENGTH_SHORT).show()
            }
        }else{
            binding.editTextNumber.text.append(button.text)
        }


    }




}