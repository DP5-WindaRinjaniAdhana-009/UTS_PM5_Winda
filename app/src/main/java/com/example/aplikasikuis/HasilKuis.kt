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

        val correctAnswers = intent.getIntExtra("BENAR", 0)
        val incorrectAnswers = intent.getIntExtra("SALAH", 0)
        val score = intent.getIntExtra("HASIL", 0)

        val resultText: TextView = findViewById(R.id.hasilText)
        resultText.text = "Benar: $correctAnswers\nSalah: $incorrectAnswers\nSkor: $score%"

        val retryButton: Button = findViewById(R.id.buttonUlangi)
        retryButton.setOnClickListener {
            val intent = Intent(this@HasilKuis, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
