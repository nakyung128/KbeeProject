package com.example.k_bee

import android.app.Application
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.common.model.AuthErrorCause.*
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApi
import com.kakao.sdk.user.UserApiClient

class LoginActivity : AppCompatActivity() {
    lateinit var ggBtn: Button
    lateinit var kkoBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        ggBtn = findViewById(R.id.ggBtn)
        kkoBtn = findViewById(R.id.kkoBtn)

        // 로그인 유지
        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null) {
                Toast.makeText(this, "토큰 정보 보기 실패", Toast.LENGTH_SHORT)
            }
            else if (tokenInfo != null) {
                // 홈 화면으로 넘어가기
                /*var intent = Intent(this, 홈액티비티::class.java)
                startActivity(intent)*/
            }
        }

        // 구글로 로그인 버튼 클릭했을 시
        ggBtn.setOnClickListener {
            // 여기에 기능 구현
            // 닉네임 정하는 페이지로 이동하는 코드 (로그인 성공시)
        }

        // 카카오 로그인 버튼 클릭했을 시
        kkoBtn.setOnClickListener {
            // 카카오톡으로 로그인
            UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                if (error != null) {
                    Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
                }
                else if (token != null) {
                    Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                    var intent = Intent(this, NicknameActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}