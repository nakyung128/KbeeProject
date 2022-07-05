package com.example.k_bee

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class MypageFragment : Fragment(R.layout.fragment_mypage) {

    lateinit var tvLevel: TextView
    lateinit var tvNickname: TextView
    lateinit var tvIntro: TextView
    lateinit var tvEmail: TextView

    private var fbAuth: FirebaseAuth? = null
    var fbFirestore: FirebaseFirestore? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var level: String = ""
        var nickname: String = ""
        var intro: String = ""
        var email: String = ""

        fbAuth = FirebaseAuth.getInstance()
        fbFirestore = FirebaseFirestore.getInstance()

        val view = inflater.inflate(R.layout.fragment_mypage, container, false)
        val uid = fbAuth!!.uid.toString()

        tvLevel = view.findViewById(R.id.tvLevel)
        tvNickname = view.findViewById(R.id.tvNickname)
        tvIntro = view.findViewById(R.id.tvIntro)
        tvEmail = view.findViewById(R.id.tvEmail)

        // 사용자 DB 읽기
        fbFirestore!!.collection("users").document(uid).get()
            .addOnSuccessListener { documentSnapshot ->
                val user = documentSnapshot.toObject<UserInfo>()
                level = user?.level.toString()
                nickname = user?.nickname.toString()
                intro = user?.intro.toString()
                email = user?.email.toString()

                // 텍스트뷰에 설정
                tvLevel.text = "LV: $level"
                tvNickname.text = nickname
                tvIntro.text = intro
                tvEmail.text = email
            }

        // 자기소개 클릭했을 때 수정 다이얼로그 띄우기
        tvIntro.setOnClickListener {

        }
        return view
    }
}