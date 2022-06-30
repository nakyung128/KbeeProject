package com.example.k_bee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoginActivity : AppCompatActivity() {
    lateinit var ggBtn: Button
    lateinit var kkoBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        ggBtn = findViewById(R.id.ggBtn);
        kkoBtn = findViewById(R.id.kkoBtn);

        // 구글로 로그인 버튼 클릭했을 시
        ggBtn.setOnClickListener {
            // 여기에 기능 구현
            // 닉네임 정하는 페이지로 이동하는 코드 (로그인 성공시)
            var intent = Intent(this, NicknameActivity::class.java)
            startActivity(intent)
        }

        // 카카오 로그인 버튼 클릭했을 시
        kkoBtn.setOnClickListener {
            // 여기에 기능 구현
            // 닉네임 정하는 페이지로 이동하는 코드 (로그인 성공시)
            var intent = Intent(this, NicknameActivity::class.java)
            startActivity(intent)
        }
    }
}