package com.example.k_bee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.kakao.sdk.common.model.AuthErrorCause.*
import com.kakao.sdk.user.UserApiClient

class LoginActivity : AppCompatActivity() {
    lateinit var ggBtn: Button
    lateinit var kkoBtn: Button

    private var auth: FirebaseAuth? = null // 객체의 공유 인스턴스
    private lateinit var client : GoogleSignInClient
    private var RC_SIGN_IN = 1000



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        ggBtn = findViewById(R.id.ggBtn)
        kkoBtn = findViewById(R.id.kkoBtn)

        // firebase auth 객체 초기화
        auth = FirebaseAuth.getInstance()


        // 이미 로그인 되어 있는지 확인
        val account = GoogleSignIn.getLastSignedInAccount(this)
        if (account == null) {
            Log.d("Google account", "로그인 안 되어있음")
        } else {
            Log.d("Google account", "로그인 완료된 상태")
        }

        // 카카오 로그인 유지
        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null) {
                Toast.makeText(this, "토큰 정보 보기 실패", Toast.LENGTH_SHORT)
            }
            else if (tokenInfo != null) {
                // 홈 화면으로 넘어가기
                Toast.makeText(this, "정보 보기 성공", Toast.LENGTH_SHORT)
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }

        // 구글로 로그인 버튼 클릭했을 시
        ggBtn.setOnClickListener {
            // 로그인 요청
            ggSignIn()
        }

        // 구글 로그인 옵션 구성, requestIdToken 및 Email 요청
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.firebase_web_client_id))
            .requestEmail()
            .build()
        client = GoogleSignIn.getClient(this, gso)

        // 카카오 로그인 버튼 클릭했을 시
        kkoBtn.setOnClickListener {
            // 카카오톡으로 로그인
            kkoSignIn()
        }
    }

    // 유저가 앱에 이미 구글 로그인을 했는지 확인
    public override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(this)
        if (account !== null) { // 로그인 되어 있을 시에
            // 바로 메인 액티비티로 이동
            //toMainActivity(auth.currentUser)
            moveNextPage()
        }
    }

    private fun ggSignIn() {
        var signInIntent = client?.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun kkoSignIn() {
        UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
            if (error != null) {
                when {
                    error.toString() == AccessDenied.toString() -> {
                        Toast.makeText(this, "접근이 거부 됨(동의 취소)", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == InvalidClient.toString() -> {
                        Toast.makeText(this, "유효하지 않은 앱", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == InvalidGrant.toString() -> {
                        Toast.makeText(this, "인증 수단이 유효하지 않아 인증할 수 없는 상태", Toast.LENGTH_SHORT)
                            .show()
                    }
                    error.toString() == InvalidRequest.toString() -> {
                        Toast.makeText(this, "요청 파라미터 오류", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == InvalidScope.toString() -> {
                        Toast.makeText(this, "유효하지 않은 scope ID", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == Misconfigured.toString() -> {
                        Toast.makeText(
                            this,
                            "설정이 올바르지 않음(android key hash)",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    error.toString() == ServerError.toString() -> {
                        Toast.makeText(this, "서버 내부 에러", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == Unauthorized.toString() -> {
                        Toast.makeText(this, "앱이 요청 권한이 없음", Toast.LENGTH_SHORT).show()
                    }
                    else -> { // Unknown
                        Toast.makeText(this, "기타 에러", Toast.LENGTH_SHORT).show()
                    }
                }
            } else if (token != null) {
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                var intent = Intent(this, NicknameActivity::class.java)
                startActivity(intent)
            }
        }
    }

    // Move to NicknameActivity
    private fun moveNextPage() {
        var currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            this.finish()
        }
    }

    // Google Sign-In Methods
    private fun firebaseAuthWithGoogle(account : GoogleSignInAccount?) {
        // Google SignInAccount 객체에서 아이디 토큰을 가져와 firebase auth로 교환하고 firebase에 인증
        var credential = GoogleAuthProvider.getCredential(account?.idToken, null)
        auth?.signInWithCredential(credential)?.addOnCompleteListener{
                task ->
            if (task.isSuccessful) {
                // 로그인 처리
                Toast.makeText(this, "로그인 성공!", Toast.LENGTH_LONG).show()
                var intent = Intent(this, NicknameActivity::class.java)
                startActivity(intent)
            } else {
                // 오류
                Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == RC_SIGN_IN) {
            var result = Auth.GoogleSignInApi.getSignInResultFromIntent(data!!)
            // result가 성공했을 때 이 값을 firebase에 넘겨주기
            if(result!!.isSuccess) {
                var account = result.signInAccount
                // Second step
                firebaseAuthWithGoogle(account)
            }
        }
    }

    // Override
    override fun onResume() {
        super.onResume()
        moveNextPage() // 자동 로그인
    }
}