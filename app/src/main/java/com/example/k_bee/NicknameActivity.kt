package com.example.k_bee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

class NicknameActivity : AppCompatActivity() {
    lateinit var nameEdit: EditText
    lateinit var applyBtn: Button

    private var fbAuth: FirebaseAuth? = null
    var fbFirestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nickname)

        nameEdit = findViewById(R.id.nameEdit) // 입력
        applyBtn = findViewById(R.id.applyBtn) // 결정 버튼

        fbAuth = FirebaseAuth.getInstance()
        fbFirestore = FirebaseFirestore.getInstance()

        var user = UserInfo()

        applyBtn.setOnClickListener {
            var name = nameEdit.text.toString()
            var userInfo = UserInfo()

            // 중복 확인
            fbFirestore?.collection("users")?.whereEqualTo("nickname", "$name")?.get()?.addOnCompleteListener {
                if (it.isSuccessful) {
                    for (dc in it.result!!.documents) {
                        userInfo = dc.toObject(UserInfo::class.java)!!
                    }
                    if (userInfo.nickname != null) {
                        Toast.makeText(this, "이미 존재하는 닉네임입니다.", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "가입 완료!", Toast.LENGTH_LONG).show()
                        // 닉네임, 이메일 db에 업로드
                        // 처음 정보 저장
                        user.nickname = nameEdit.text.toString()
                        user.email = fbAuth?.currentUser?.email
                        user.level = "1" // 레벨 초기 설정
                        user.intro = "반가워요!"
                        fbFirestore?.collection("users")?.document(fbAuth?.uid.toString())?.set(user)
                        // 메인 페이지로 이동
                        var intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
        }
    }
}