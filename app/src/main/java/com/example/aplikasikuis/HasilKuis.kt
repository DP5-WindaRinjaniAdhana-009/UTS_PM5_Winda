package com.example.aplikasikuis

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HasilKuis : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hasil_kuis)

        val benar = intent.getIntExtra("BENAR", 0)
        val salah = intent.getIntExtra("SALAH", 0)
        val hasil = intent.getIntExtra("HASIL", 0)

        val hasilText: TextView = findViewById(R.id.hasilText)
        hasilText.text = "Benar: $benar\nSalah: $salah\nSkor: $hasil%"

        val tombolUlangi: Button = findViewById(R.id.buttonUlangi)
        tombolUlangi.setOnClickListener {

            val intent = Intent(this@HasilKuis, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
