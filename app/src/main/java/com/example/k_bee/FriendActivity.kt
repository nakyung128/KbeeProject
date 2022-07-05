// FriendsearchActivity 친구검색
package com.example.k_bee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil.setContentView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class FriendActivity : AppCompatActivity() {
    private var fbAuth: FirebaseAuth? = null
    var fbFirestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_search)

        fbAuth = FirebaseAuth.getInstance()
        fbFirestore = FirebaseFirestore.getInstance()

        /*var user = UserInfo()

        user.email = fbAuth?.currentUser?.email
        user.nickname = "나갱"
        fbFirestore?.collection("users")?.document(fbAuth?.uid.toString())?.set(user)*/

        fbFirestore?.collection("users")?.get()?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (i in task.result!!) {
                    if (i.id == "나갱") {
                        Toast.makeText(this, "이미 존재하는 닉네임입니다.", Toast.LENGTH_SHORT)
                        Log.d("name", "이미 존재하는 이름")
                    }
                }
            }
        }
    }
}