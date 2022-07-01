package com.example.k_bee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.kakao.sdk.user.UserApiClient

class NicknameActivity : AppCompatActivity() {
    lateinit var nameEdit: EditText
    lateinit var checkBtn: Button
    lateinit var applyBtn: Button
    lateinit var number: TextView
    lateinit var mail: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nickname)

        nameEdit = findViewById(R.id.nameEdit) // 입력
        checkBtn = findViewById(R.id.checkBtn) // 중복 확인 버튼
        applyBtn = findViewById(R.id.applyBtn) // 결정 버튼
        number = findViewById(R.id.textView3)
        mail = findViewById(R.id.textView4)

        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Toast.makeText(this, "사용자 정보 요청 실패", Toast.LENGTH_SHORT).show()
            }
            else if (user != null) {
                Toast.makeText(this, "정보 요청 성공", Toast.LENGTH_SHORT).show()
                number.text = "회원 번호: ${user.id}"
                mail.text = "이메일: ${user.kakaoAccount?.email}"
            }
        }

        // 중복 확인 버튼 눌렀을 때
        checkBtn.setOnClickListener {
            var name = nameEdit.text.toString() // editText에 작성한 것 가져오기
            // 중복 확인하는 코드 작성
        }

        // 결정 버튼 눌렀을 때
        applyBtn.setOnClickListener {
            // 닉네임 db에 업로드
            // 메인 페이지로 이동
        }
    }
}