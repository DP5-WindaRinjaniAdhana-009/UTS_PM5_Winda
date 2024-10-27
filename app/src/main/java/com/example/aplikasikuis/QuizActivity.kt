package com.example.aplikasikuis

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {

    private lateinit var questionText: TextView
    private lateinit var optionsGroup: RadioGroup
    private lateinit var optionA: RadioButton
    private lateinit var optionB: RadioButton
    private lateinit var optionC: RadioButton
    private lateinit var optionD: RadioButton
    private lateinit var explanationText: TextView
    private lateinit var finishButton: Button
    private var questionIndex = 0

    companion object {
        var score = 0
        var benar = 0
        var salah = 0
    }

    private val questions = arrayOf(
        "1. Jika sebuah pohon tumbang di hutan dan tidak ada makhluk hidup di sekitarnya, apakah suara tumbangnya benar-benar ada?",
        "2. Jika kita bepergian lebih cepat daripada kecepatan cahaya, apa yang akan terjadi pada persepsi kita terhadap waktu?"
    )

    private val answerOptions = arrayOf(
        arrayOf("A. Ya, karena suara tetap ada meski tidak ada yang mendengarnya.", "B. Tidak, karena suara hanya ada jika ada yang mendengar.", "C. Tergantung pada jenis pohon tersebut.", "D. Suara hanya ada dalam persepsi, jadi tidak ada jika tidak didengar."),
        arrayOf("A. Waktu akan terasa lebih cepat.", "B. Waktu akan berhenti total.", "C. Kita akan bergerak mundur dalam waktu.", "D. Tidak ada yang berubah dengan waktu.")
    )

    private val correctAnswers = arrayOf(
        "B. Tidak, karena suara hanya ada jika ada yang mendengar.",
        "C. Kita akan bergerak mundur dalam waktu."
    )

    private val explanations = arrayOf(
        "Penjelasan: Konsep filosofis yang mempertanyakan hubungan antara persepsi dan realitas. Suara, secara fisika, adalah gelombang yang memerlukan penerima (seperti telinga atau alat pendengar lainnya) untuk ditafsirkan sebagai suara. Tanpa pendengar, getaran itu mungkin tetap ada, tetapi tidak dapat dikatakan menghasilkan suara.\n" +
                "Jadi, jawaban yang benar adalah B.  Tidak, karena suara hanya ada jika ada yang mendengar.",
        "Penjelasan: Berdasarkan teori relativitas Einstein, jika seseorang bisa bergerak lebih cepat dari kecepatan cahaya (secara teori, meski belum mungkin secara fisik), maka ia akan mengalami efek \"pembalikan waktu.\" Artinya, ia akan bergerak mundur dalam waktu, karena kecepatan lebih tinggi dari cahaya dapat memengaruhi cara waktu dirasakan oleh orang yang bergerak pada kecepatan tersebut. \n" +
                "Jadi, jawaban yang benar adalah C. Kita akan bergerak mundur dalam waktu."
    )

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        questionText = findViewById(R.id.kuis)
        optionsGroup = findViewById(R.id.pilihan)
        optionA = findViewById(R.id.pilihanA)
        optionB = findViewById(R.id.pilihanB)
        optionC = findViewById(R.id.pilihanC)
        optionD = findViewById(R.id.pilihanD)
        explanationText = findViewById(R.id.explanationText)
        finishButton = findViewById(R.id.finishButton)

        benar = 0
        salah = 0
        showQuestion()
    }

    private fun showQuestion() {
        questionText.text = questions[questionIndex]
        val currentOptions = answerOptions[questionIndex]
        optionA.text = currentOptions[0]
        optionB.text = currentOptions[1]
        optionC.text = currentOptions[2]
        optionD.text = currentOptions[3]
        optionsGroup.clearCheck()
        explanationText.visibility = View.GONE
        finishButton.visibility = View.GONE // Tombol "Sudahi" hanya muncul di akhir
    }

    fun next(view: View) {
        if (optionsGroup.checkedRadioButtonId != -1) {
            val selectedAnswer = findViewById<RadioButton>(optionsGroup.checkedRadioButtonId).text.toString()

            // Tampilkan pembahasan setelah jawaban dipilih
            explanationText.visibility = View.VISIBLE
            if (selectedAnswer == correctAnswers[questionIndex]) {
                benar++
                explanationText.text = "Jawaban benar! \n${explanations[questionIndex]}"
            } else {
                salah++
                explanationText.text = "Jawaban salah! \n${explanations[questionIndex]}"
            }

            questionIndex++

            if (questionIndex >= questions.size) {
                finishButton.visibility = View.VISIBLE
                view.visibility = View.GONE
            } else {
                // jeda melanjutkan ke pertanyaan berikutnya
                view.postDelayed({ showQuestion() }, 10000) // delay
            }
        } else {
            Toast.makeText(this, "Hai, jawaban belum kamu pilih", Toast.LENGTH_LONG).show()
        }
    }

    fun finishQuiz(view: View) {
        score = (benar * 100) / questions.size
        val intent = Intent(this@QuizActivity, HasilKuis::class.java)
        intent.putExtra("BENAR", benar)
        intent.putExtra("SALAH", salah)
        intent.putExtra("HASIL", score)
        startActivity(intent)
    }
}
