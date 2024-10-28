package com.example.lab3_11

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Patterns
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), GestureDetector.OnGestureListener {

    private lateinit var gestureDetector: GestureDetector
    private lateinit var mainLayout: RelativeLayout
    private lateinit var emailEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emailEditText = findViewById(R.id.emailEditText)
        phoneEditText = findViewById(R.id.phoneEditText)
        submitButton = findViewById(R.id.submitButton)
        resultTextView = findViewById(R.id.resultTextView)

        submitButton.setOnClickListener {
            validateInput()
        }
        mainLayout=findViewById(R.id.main)
        gestureDetector = GestureDetector(this, this)


    }

    private fun validateInput() {
        val email = emailEditText.text.toString()
        val phone = phoneEditText.text.toString()
        val resultMessage: String

        if (isEmailValid(email)&& isPhoneValid(phone)) {
            resultMessage = "Email và số điện thoại hợp lệ"
        } else if (!isEmailValid(email) && !isPhoneValid(phone)) {
            resultMessage = "Email và số điện thoại không hợp lệ"
        } else if (!isPhoneValid(phone)) {
            resultMessage = "Số điện thoại không hợp lệ"
        }
        else{
            resultMessage = "Email không hợp lệ"
        }

        resultTextView.text = resultMessage
    }

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isPhoneValid(phone: String): Boolean {
        return phone.matches("\\d+".toRegex())
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(event) || super.onTouchEvent(event)
    }
    override fun onSingleTapUp(p0: MotionEvent): Boolean {
        mainLayout.setBackgroundColor(Color.BLUE)
        Toast.makeText(this, "Tap detected!", Toast.LENGTH_SHORT).show()
        DemS()
        return true
    }

    override fun onFling(
        e1: MotionEvent?,
        p1: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        if (e1 != null && p1 != null) {
            val diffY = p1.y - e1.y
            if (Math.abs(diffY) > Math.abs(p1.x - e1.x)) {
                if (diffY < 0) {
                    // Vuốt lên
                    mainLayout.setBackgroundColor(Color.YELLOW)
                } else {
                    // Vuốt xuống
                    mainLayout.setBackgroundColor(Color.RED)
                }
            }
        }
        Toast.makeText(this, "Swipe detected!", Toast.LENGTH_SHORT).show()
        DemS()
        return true
    }

    override fun onDown(p0: MotionEvent): Boolean = true
    override fun onShowPress(p0: MotionEvent) {}
    override fun onScroll(
        e1: MotionEvent?,
        p1: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ): Boolean = false
    override fun onLongPress(p0: MotionEvent) {}


    fun DemS() {
        val timer = object : CountDownTimer(3000, 1000) { // 5 giây, cập nhật mỗi giây
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                mainLayout.setBackgroundColor(Color.WHITE)
            }
        }
        timer.start()
    }



}
