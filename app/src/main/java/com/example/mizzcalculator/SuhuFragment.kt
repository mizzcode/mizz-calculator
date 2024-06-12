package com.example.mizzcalculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class SuhuFragment : Fragment() {

    private val suhu = arrayOf("Celsius", "Fahrenheit", "Kelvin")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_suhu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spinner1 = view.findViewById<Spinner>(R.id.spinner_1)
        val spinner2 = view.findViewById<Spinner>(R.id.spinner_2)
        val editText1 = view.findViewById<EditText>(R.id.edit_1)
        val editText2 = view.findViewById<EditText>(R.id.edit_2)
        val btnConvert = view.findViewById<Button>(R.id.count)
        val textFormula = view.findViewById<TextView>(R.id.text_formula)

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, suhu)
        spinner1.adapter = adapter
        spinner2.adapter = adapter

        btnConvert.setOnClickListener {
            val inputSuhu = editText1.text.toString().toDoubleOrNull()
            if (inputSuhu == null) {
                Toast.makeText(requireContext(), "Masukkan suhu yang valid", Toast.LENGTH_SHORT).show();
                return@setOnClickListener
            }

            val fromSuhu = spinner1.selectedItem.toString()
            val toSuhu = spinner2.selectedItem.toString()

            val result = convertTemperature(inputSuhu, fromSuhu, toSuhu)
            editText2.setText(result.toString())

            val formula = getFormula(inputSuhu, fromSuhu, toSuhu)
            textFormula.text = formula
        }
    }

    private fun convertTemperature(value: Double, fromSuhu: String, toSuhu: String): Double {
        return when (fromSuhu) {
            "Celsius" -> when (toSuhu) {
                "Fahrenheit" -> value * 9/5 + 32
                "Kelvin" -> value + 273.15
                else -> value
            }
            "Fahrenheit" -> when (toSuhu) {
                "Celsius" -> (value - 32) * 5/9
                "Kelvin" -> (value - 32) * 5/9 + 273.15
                else -> value
            }
            "Kelvin" -> when (toSuhu) {
                "Celsius" -> value - 273.15
                "Fahrenheit" -> (value - 273.15) * 9/5 + 32
                else -> value
            }
            else -> value
        }
    }

    private fun getFormula(value: Double, fromSuhu: String, toSuhu: String): String {
        return when (fromSuhu) {
            "Celsius" -> when (toSuhu) {
                "Fahrenheit" -> "($value°C * 9/5) + 32"
                "Kelvin" -> "$value°C + 273.15"
                else -> "$value°C"
            }
            "Fahrenheit" -> when (toSuhu) {
                "Celsius" -> "($value°F - 32) * 5/9"
                "Kelvin" -> "($value°F - 32) * 5/9 + 273.15"
                else -> "$value°F"
            }
            "Kelvin" -> when (toSuhu) {
                "Celsius" -> "$value K - 273.15"
                "Fahrenheit" -> "($value K - 273.15) * 9/5 + 32"
                else -> "$value K"
            }
            else -> "$value"
        }
    }
}
