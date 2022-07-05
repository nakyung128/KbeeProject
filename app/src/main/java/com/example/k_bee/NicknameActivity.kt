package com.example.k_bee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class NicknameActivity : AppCompatActivity() {
    lateinit var nameEdit: EditText
    lateinit var checkBtn: Button
    lateinit var applyBtn: Button

    private var fbAuth: FirebaseAuth? = null
    var fbFirestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nickname)

        nameEdit = findViewById(R.id.nameEdit) // 입력
        checkBtn = findViewById(R.id.checkBtn) // 중복 확인 버튼
        applyBtn = findViewById(R.id.applyBtn) // 결정 버튼

        fbAuth = FirebaseAuth.getInstance()
        fbFirestore = FirebaseFirestore.getInstance()

        val myRef = fbFirestore?.collection("users")?.document(fbAuth?.uid.toString())

        var user = UserInfo()

        val name = nameEdit.text.toString()

        // 처음 정보 저장
        user.nickname = nameEdit.text.toString()
        user.email = fbAuth?.currentUser?.email
        user.level = "1" // 레벨 초기 설정
        user.intro = ""

        // 중복 확인 버튼 눌렀을 때
        checkBtn.setOnClickListener {
            // 중복 확인하는 코드 작성 (수정 필요)
            fbFirestore?.collection("users")?.get()?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (i in task.result) {
                        if (i.id == name) {
                            Toast.makeText(this, "이미 존재하는 닉네임입니다.", Toast.LENGTH_SHORT)
                        }
                    }
                }
            }
        }

        // 결정 버튼 눌렀을 때
        applyBtn.setOnClickListener {
            // 닉네임, 이메일 db에 업로드
            fbFirestore?.collection("users")?.document(fbAuth?.uid.toString())?.set(user)
            // 메인 페이지로 이동
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}