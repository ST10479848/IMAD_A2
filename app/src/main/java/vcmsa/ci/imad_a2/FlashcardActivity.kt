package vcmsa.ci.imad_a2

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.*

class FlashcardActivity : AppCompatActivity() {
    //declare the parallel arrays for the questions and the answers
    val questions = arrayOf(
        "Nelson Mandela was president in 1994",
        "The sun is a planet", "Water boils at 100 degrees Celsius",
        "Africa is a country", "Humans have 206 bones in their body"
    )

    val answers = arrayOf(
        true,
        false,
        true,
        false,
        true
    )

    var currentIndex = 0
    var score = 0

    // view the variables
    lateinit var questionText: TextView
    lateinit var feedbackText: TextView
    lateinit var trueButton: Button
    lateinit var falseButton: Button
    lateinit var nextButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flashcard)

        //linking the views
        questionText = findViewById(R.id.questionText)
        feedbackText = findViewById(R.id.feedbackText)
        trueButton = findViewById(R.id.trueButton)
        falseButton = findViewById(R.id.falseButton)
        nextButton = findViewById(R.id.nextButton)

        //shows the first question
        loadQuestion()

        trueButton.setOnClickListener { checkAnswer(true) }
        falseButton.setOnClickListener { checkAnswer(false) }

        nextButton.setOnClickListener {
            currentIndex++
            if (currentIndex < questions.size) {
                loadQuestion()
            } else {
                //go to the ScoreActivity and send score
                val intent = Intent(this, ScoreActivity::class.java)
                intent.putExtra("score", score)
                startActivity(intent)
                finish()
            }
        }
    }

    fun loadQuestion() {
        questionText.text = questions[currentIndex]
        feedbackText.text = ""
        trueButton.isEnabled = true
        falseButton.isEnabled = true
    }

    fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = answers[currentIndex]
        if (userAnswer == correctAnswer) {
            feedbackText.text = "Correct :)"
            score++
        } else {
            feedbackText.text = "Incorrect :("
        }
        //disable the buttons after answering
        trueButton.isEnabled = false
        falseButton.isEnabled = false
    }
}