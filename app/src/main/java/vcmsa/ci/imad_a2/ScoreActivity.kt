package vcmsa.ci.imad_a2

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ScoreActivity : AppCompatActivity() {
    lateinit var scoreText: TextView
    lateinit var scorefeedbackText: TextView
    lateinit var reviewButton : Button
    lateinit var exitButton : Button

    val questions = arrayOf(
        "Nelson Mandela was president in 1994",
        "The sun is a planet",
        "Water boils at 100 degrees Celsius",
        "Africa is a country",
        "Humans have 206 bones in their body"
    )

    val answers = arrayOf(
        true, false, true, false, true
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        scoreText = findViewById(R.id.scoreText)
        scorefeedbackText = findViewById(R.id.scorefeedbackText)
        reviewButton = findViewById(R.id.reviewButton)
        exitButton = findViewById(R.id.exitButton)

        val score = intent.getIntExtra("score", 0)
        scoreText.text = "Your score is: $score / ${questions.size}"

        // gives feedback based on your score
        if (score >= 3) {
            scorefeedbackText.text = "Superb Job ツ"
        } else {
            scorefeedbackText.text = "Better luck next time (╯°□°)╯ ┻━┻"
        }

        // shows all questions and their correct answers
        reviewButton.setOnClickListener{
            showReviewDialog()
        }

        // exit the app with this button
        exitButton.setOnClickListener{
            finishAffinity() // this will close the app completely
        }
    }

    fun showReviewDialog(){
        val builder = AlertDialog.Builder (this)
        builder.setTitle("Review answers")

        val reviewText = StringBuilder()
        for (i in questions.indices) {
            reviewText.append("${i + 1}. ${questions[i]}\nAnswer: ${if (answers[i]) "True" else "False"}\n\n")
        }

        builder.setMessage(reviewText.toString())
        builder.setPositiveButton("Ok", null)
        builder.show()
    }
}