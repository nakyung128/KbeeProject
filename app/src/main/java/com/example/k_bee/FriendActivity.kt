// FriendsearchActivity 친구검색
package com.example.k_bee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
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
    lateinit var nameSearch : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_search)

        fbAuth = FirebaseAuth.getInstance()
        fbFirestore = FirebaseFirestore.getInstance()

        userView = findViewById(R.id.userVIew)
        nameSearch = findViewById(R.id.nameSearch)

        userView.adapter = UserViewAdapter()
        userView.layoutManager = LinearLayoutManager(this)

        /*searchBtn.setOnClickListener {
            (userView.adapter as UserViewAdapter).search(nameSearch.text.toString())
        }*/

        var watcher = MyEditWatcher()
        nameSearch.addTextChangedListener(watcher)
        //nameSearch.setOnClickListener(MyEnterListener())
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

        fun search (searchWord : String) {
            fbFirestore?.collection("users")?.addSnapshotListener { querySnapshot, firebaseFirestoreExeption ->
                // ArrayList 비워줌
                userArr.clear()

                for (snapshot in querySnapshot!!.documents) {
                    if (snapshot.getString("nickname")!!.contains(searchWord)) {
                        var item = snapshot.toObject(UserData::class.java)
                        userArr.add(item!!)
                    }
                }
                notifyDataSetChanged()
            }
        }
    }

    // editText 실시간 입력
    inner class MyEditWatcher : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        // 문자열이 바뀐 후
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            (userView.adapter as UserViewAdapter).search(p0.toString())
        }

        override fun afterTextChanged(p0: Editable?) {
            (userView.adapter as UserViewAdapter).search(p0.toString())
        }
    }

    /*inner class MyEnterListener : TextView.OnEditorActionListener {
        override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
            var handled = false // 키보드 내림
            // 완료 버튼 클릭 처리
            if (actionId == EditorInfo.IME_ACTION_DONE) {

            } else if (actionId == EditorInfo.IME_ACTION_NEXT) {
                handled = true // 키보드 유지
            }
            return handled
        }
    }*/
}
