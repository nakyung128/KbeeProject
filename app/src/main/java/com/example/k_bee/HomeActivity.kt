package com.example.k_bee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeActivity : AppCompatActivity() {
    lateinit var add2 : FloatingActionButton
    lateinit var todo2 : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        add2 = findViewById(R.id.add2) // 추가 버튼
        todo2 = findViewById(R.id.todo2) // todo2 텍스트뷰

        // 체크된 거 인텐트로 받을 경우
        var checkText = intent.getStringExtra("checked")
        if (checkText != null) {
            add2.visibility = View.GONE // 버튼 없애기
            // 텍스트 추가
            todo2.visibility = View.VISIBLE
            todo2.text = checkText
        }

        // 버튼 눌렀을 때
        add2.setOnClickListener {
            // 리스트 추가 화면으로 이동
            var intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }

    }
}
