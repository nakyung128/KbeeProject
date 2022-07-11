package com.example.k_bee

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.android.synthetic.main.fragment_mypage.*

class MypageFragment : Fragment(R.layout.fragment_mypage) {

    lateinit var tvLevel: TextView
    lateinit var tvNickname: TextView
    lateinit var tvIntro: TextView
    lateinit var tvEmail: TextView
    lateinit var ivProfile: ImageView

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
        ivProfile = view.findViewById(R.id.ivProfile)

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

        // 자기소개 클릭했을 때 수정하는 창 띄우기
        tvIntro.setOnClickListener {
            var intent = Intent(context, IntroActivity::class.java)
            startActivity(intent)
        }

        // 이미지 클릭했을 때 사진 바꾸기
        initImageViewProfile()

        return view
    }

    private fun initImageViewProfile() {
        ivProfile.setOnClickListener {
            when {
                // 갤러리 접근 권한이 있는 경우
                ActivityCompat.checkSelfPermission(
                    requireActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
                -> {
                    navigateGallery()
                }

                // 갤러리 접근 권한이 없는 경우 & 교육용 팝업을 보여줘야 하는 경우
                shouldShowRequestPermissionRationale(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                -> {
                    showPermissionContextPopup()
                }

                // 권한 요청 하기(requestPermissions) -> 갤러리 접근(onRequestPermissionResult)
                else -> requestPermissions(
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    1000
                )
            }
        }
    }

    // 권한 요청 승인 이후 실행되는 함수
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            1000 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    navigateGallery()
                else
                    Toast.makeText(context, "권한을 거부하셨습니다.", Toast.LENGTH_SHORT).show()
            }
            else -> {
                //
            }
        }
    }

    private fun navigateGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        // 가져올 컨텐츠들 중에서 Image 만을 가져온다.
        intent.type = "image/*"
        // 갤러리에서 이미지를 선택한 후, 프로필 이미지뷰를 수정하기 위해 갤러리에서 수행한 값을 받아오는 startActivityForeResult를 사용한다.
        startActivityForResult(intent, 2000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // 예외처리
        if (resultCode != Activity.RESULT_OK)
            return

        when (requestCode) {
            // 2000: 이미지 컨텐츠를 가져오는 액티비티를 수행한 후 실행되는 Activity 일 때만 수행하기 위해서
            2000 -> {
                val selectedImageUri: Uri? = data?.data
                if (selectedImageUri != null) {
                    ivProfile.setImageURI(selectedImageUri)

                } else {
                    Toast.makeText(context, "사진을 가져오지 못했습니다.", Toast.LENGTH_SHORT).show()
                }
            }
            else -> {
                Toast.makeText(context, "사진을 가져오지 못했습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showPermissionContextPopup() {
        AlertDialog.Builder(context)
            .setTitle("권한이 필요합니다.")
            .setMessage("프로필 이미지를 바꾸기 위해서는 갤러리 접근 권한이 필요합니다.")
            .setPositiveButton("동의하기") { _, _ ->
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1000)
            }
            .setNegativeButton("취소하기") { _, _ -> }
            .create()
            .show()
    }
}