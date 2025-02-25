package com.example.chatbot

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
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

        sendButton.setOnClickListener {
            val message = editText.text.toString().trim()

            if (message.isNotEmpty()) {
                addMessageBubble(message, chatLayout)
                editText.text.clear()

                // Scroll to the bottom
                scrollView.post {
                    scrollView.fullScroll(View.FOCUS_DOWN)
                }
            }
        }
    }

    private fun addMessageBubble(message: String, chatLayout: LinearLayout) {
        val messageBubble = TextView(this).apply {
            text = message
            textSize = 22f // Larger text size for visibility
            setTextColor(ContextCompat.getColor(context, android.R.color.black))
            setPadding(24, 16, 24, 16) // Padding inside the bubble (text and bubble edge)
            setBackgroundResource(R.drawable.message_bubble_background)
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = Gravity.END // Align bubble to the right
                setMargins(16, 16, 48, 16) // Adjust margins to ensure proper spacing
            }
        }

        chatLayout.addView(messageBubble)
    }


}