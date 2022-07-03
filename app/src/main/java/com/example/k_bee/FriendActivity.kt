// FriendsearchActivity 친구검색
package com.example.k_bee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil.setContentView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class FriendActivity : AppCompatActivity() {
    var fbAuth: FirebaseAuth? = null
    var fbFirestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_search)

        fbAuth = FirebaseAuth.getInstance()
        fbFirestore = FirebaseFirestore.getInstance()

        if(true) {
            var user = UserInfo()

            user.uid = fbAuth?.uid
            user.email = fbAuth?.currentUser?.email
            fbFirestore?.collection("users")?.document(fbAuth?.uid.toString())?.set(user)
        }
    }
}