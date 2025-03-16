package com.example.chatbot

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.editTextText)
        val sendButton = findViewById<Button>(R.id.button)
        val chatLayout = findViewById<LinearLayout>(R.id.chatLinearLayout)
        val scrollView = findViewById<ScrollView>(R.id.scrollView2)
        val homeButton = findViewById<Button>(R.id.button2) // Reference to button2

        sendButton.setOnClickListener {
            val message = editText.text.toString().trim()

            if (message.isNotEmpty()) {
                addMessageBubble(message, chatLayout, true)  // User message (Right)
                editText.text.clear()

                // Simulate bot response
                chatLayout.postDelayed({
                    addMessageBubble(
                        "This is a bot reply!",
                        chatLayout,
                        false
                    )  // Bot message (Left)
                }, 1000)

                // Scroll to bottom
                scrollView.post {
                    scrollView.fullScroll(View.FOCUS_DOWN)
                }
            }
        }

        // Navigate to HomePageActivity when button2 is clicked
        homeButton.setOnClickListener {
            val intent = Intent(this, HomepageActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addMessageBubble(message: String, chatLayout: LinearLayout, isUser: Boolean) {
        val messageBubble = TextView(this).apply {
            text = message
            textSize = 18f
            setTextColor(ContextCompat.getColor(context, android.R.color.white))
            setPadding(30, 20, 30, 20) // Padding for a well-shaped bubble

            setBackgroundResource(if (isUser) R.drawable.message_bubble_background else R.drawable.message_chatbot_background)

            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = if (isUser) Gravity.END else Gravity.START  // Align user right, bot left
                setMargins(24, 16, 60, 16) // SAME margins for both
            }

            maxWidth = (resources.displayMetrics.widthPixels * 0.75).toInt() // Limit width to 75% of screen
        }

        chatLayout.addView(messageBubble)
    }
}
