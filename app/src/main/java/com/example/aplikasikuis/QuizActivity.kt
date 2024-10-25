package com.example.aplikasikuis

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {

    private lateinit var kuis: TextView
    private lateinit var rg: RadioGroup
    private lateinit var pilihanA: RadioButton
    private lateinit var pilihanB: RadioButton
    private lateinit var pilihanC: RadioButton
    private lateinit var pilihanD: RadioButton
    private var nomor = 0

    companion object {
        var hasil = 0
        var benar = 0
        var salah = 0
    }

    private val pertanyaanKuis = arrayOf(
        "1. Presiden Indonesia yang kedelapan adalah?",
        "2. Negara manakah yang memiliki jumlah penduduk terbanyak di dunia?"
    )

    private val pilihanJawaban = arrayOf(
        arrayOf("a. Soekarno", "b. Prabowo Subianto", "c. Joko Widodo", "d. B.J.Habibie"),
        arrayOf("a. India", "b. Amerika Serikat", "c. China", "d. Indonesia")
    )

    private val jawabanBenar = arrayOf(
        "b. Prabowo Subianto",
        "c. China"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        kuis = findViewById(R.id.kuis)
        rg = findViewById(R.id.pilihan)
        pilihanA = findViewById(R.id.pilihanA)
        pilihanB = findViewById(R.id.pilihanB)
        pilihanC = findViewById(R.id.pilihanC)
        pilihanD = findViewById(R.id.pilihanD)

        benar = 0
        salah = 0

        tampilkanPertanyaan()
    }

    private fun tampilkanPertanyaan() {
        kuis.text = pertanyaanKuis[nomor]
        val pilihanSaatIni = pilihanJawaban[nomor]
        pilihanA.text = pilihanSaatIni[0]
        pilihanB.text = pilihanSaatIni[1]
        pilihanC.text = pilihanSaatIni[2]
        pilihanD.text = pilihanSaatIni[3]
        rg.clearCheck()
    }

    fun next(view: android.view.View) {
        if (rg.checkedRadioButtonId != -1) {
            val jawabanUser = findViewById<RadioButton>(rg.checkedRadioButtonId)
            val ambilJawabanUser = jawabanUser.text.toString()

            if (ambilJawabanUser == jawabanBenar[nomor]) {
                benar++
            } else {
                salah++
            }

            nomor++
            if (nomor < pertanyaanKuis.size) {
                tampilkanPertanyaan()
            } else {
                hasil = (benar * 100) / pertanyaanKuis.size
                val selesai = Intent(applicationContext, HasilKuis::class.java)
                selesai.putExtra("BENAR", benar)
                selesai.putExtra("SALAH", salah)
                selesai.putExtra("HASIL", hasil)

                startActivity(selesai)
            }
        } else {
            Toast.makeText(this, "Eits silahkan pilih jawaban dahulu yaa", Toast.LENGTH_LONG).show()
        }
    }
}
