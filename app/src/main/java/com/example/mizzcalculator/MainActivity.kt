package com.example.mizzcalculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
//    deklarasi variable untuk nanti di inisiasikan
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        mengubah warna status bar navigasi menjadi hitam
        window.navigationBarColor = resources.getColor(R.color.black)

//        set default fragment nya kalkulator, jadi ketika app dibuka itu yang muncul pertama adalah kalkulator
        replaceFragment(KalkulatorFragment())

//        mendapatkan view bottom navigation
        bottomNavigationView = findViewById(R.id.bottom_navigation)

//        set listener jika masing-masing item di bottom navigation diklik
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.bottom_kalkulator -> {
                    replaceFragment(KalkulatorFragment())
                    true
                }
                R.id.bottom_suhu -> {
                    replaceFragment(SuhuFragment())
                    true
                }
                else -> false
            }
        }
    }
//    method untuk mengganti fragment
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_container, fragment)
            .commit()
    }
}