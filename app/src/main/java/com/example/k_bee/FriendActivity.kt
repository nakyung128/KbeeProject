// FriendsearchActivity 친구검색
package com.example.k_bee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil.setContentView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.user_list.view.*

class FriendActivity : AppCompatActivity() {
    private var fbAuth: FirebaseAuth? = null
    var fbFirestore: FirebaseFirestore? = null
    lateinit var userView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_search)

        fbAuth = FirebaseAuth.getInstance()
        fbFirestore = FirebaseFirestore.getInstance()

        userView = findViewById(R.id.userVIew)
        userView.adapter = UserViewAdapter()
        userView.layoutManager = LinearLayoutManager(this)
        //userView.layoutManager = LinearLayoutManager(this)
    }

    inner class UserViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        // User 클래스 ArrayList 생성
        var userArr : ArrayList<UserData> = arrayListOf()

        init {
            fbFirestore?.collection("users")?.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                userArr.clear()

                for (snapshot in querySnapshot!!.documents) {
                    var item = snapshot.toObject(UserData::class.java)
                    userArr.add(item!!)
                }
                notifyDataSetChanged()
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.user_list, parent, false)
            return ViewHolder(view)
        }

        inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        }

        // onCreateViewHolder에서 만든 view와 실제 데이터를 연결
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            var viewHolder = (holder as ViewHolder).itemView

            viewHolder.name.text = userArr[position].nickname
            viewHolder.email.text = userArr[position].email
            viewHolder.intro.text = userArr[position].intro
        }

        override fun getItemCount(): Int {
            return userArr.size
        }
    }
}