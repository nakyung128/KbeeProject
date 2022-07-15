package com.example.k_bee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class IntroActivity : AppCompatActivity() {

    lateinit var introEdit : EditText
    lateinit var editBtn : Button

    private var fbAuth: FirebaseAuth? = null
    var fbFirestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        val mypageFragment = MypageFragment()

        fbAuth = FirebaseAuth.getInstance()
        fbFirestore = FirebaseFirestore.getInstance()

        introEdit = findViewById(R.id.introEdit)
        editBtn = findViewById(R.id.editBtn)

        editBtn.setOnClickListener {
            var intro = introEdit.text.toString()

            fbFirestore?.collection("users")?.document(fbAuth?.uid.toString())?.update("intro", intro)

            // 마이 페이지로 이동
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
    }
}