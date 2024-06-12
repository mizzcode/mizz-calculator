package com.example.mizzcalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class KalkulatorFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kalkulator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        mencari id view pada layout
        val input1 = view.findViewById<EditText>(R.id.input1)
        val input2 = view.findViewById<EditText>(R.id.input2)
        val btnPlus = view.findViewById<Button>(R.id.btnPlus)
        val btnMin = view.findViewById<Button>(R.id.btnMin)
        val btnMultiply = view.findViewById<Button>(R.id.btnMultiply)
        val btnDivide = view.findViewById<Button>(R.id.btnDivide)
        val btnEquals = view.findViewById<Button>(R.id.btnEquals)
        val result = view.findViewById<TextView>(R.id.result)

//        operator matematika dengan nilai default null
        var currentOperation: String? = null
//        listener jika operator plus di klik oleh user
        btnPlus.setOnClickListener {
            currentOperation = "+"
        }
//        listener jika operator minus di klik oleh user
        btnMin.setOnClickListener {
            currentOperation = "-"
        }
//        listener jika operator kali di klik oleh user
        btnMultiply.setOnClickListener {
            currentOperation = "*"
        }
//        listener jika operator bagi di klik oleh user
        btnDivide.setOnClickListener {
            currentOperation = "/"
        }

        btnEquals.setOnClickListener {
//            memastikan user klik operator terlebih dahulu
            if (currentOperation != null) {
//                mengubah text input menjadi int atau bisa null
                val num1 = input1.text.toString().toDoubleOrNull()
                val num2 = input2.text.toString().toDoubleOrNull()
//                jika inputan nya null atau kosong maka suruh user untuk mengisi input 1 atau 2
                if (num1 == null || num2 == null) {
                    result.text = "Input 1 atau input 2 ga boleh kosong lur 👽."
                    return@setOnClickListener
                }
//                untuk menampung nilai hasil dari operasi
                var finalResult = 0     // integer
                var finalResult2 = 0.0  // double

//                kondisi untuk menentukan operasi
                when (currentOperation) {
                    "+" -> finalResult = (num1 + num2).toInt()  // cast ke tipe data int
                    "-" -> finalResult = (num1 - num2).toInt()  // cast ke tipe data int
                    "*" -> finalResult = (num1 * num2).toInt()  // cast ke tipe data int
                    "/" -> {
                        if (num2.toInt() != 0) {
                            finalResult2 = num1 / num2
                        } else {
                            result.text = "Tidak bisa dibagi dengan 0 🥴"
                            return@setOnClickListener
                        }
                    }
                }

                if (currentOperation != "/") {
                    result.text = finalResult.toString() // menjadikan nilai hasil dari operasi menjadi string
                } else {
                    result.text = finalResult2.toString() // menjadikan nilai hasil dari operasi menjadi string
                }

            } else {
                // Handle kasus dimana user klik sama-dengan tanpa memilih operator
                result.text = "Pilih operator dulu euy 👌."
            }
        }

    }
}