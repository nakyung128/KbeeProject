package com.example.k_bee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.BoringLayout
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlin.properties.Delegates

class HomeActivity : AppCompatActivity() {
    lateinit var add2 : FloatingActionButton
    lateinit var todo2 : TextView
    lateinit var add3 : FloatingActionButton
    lateinit var todo3 : TextView
    lateinit var add4 : FloatingActionButton
    lateinit var todo4 : TextView
    lateinit var add5 : FloatingActionButton
    lateinit var todo5 : TextView
    lateinit var add6 : FloatingActionButton
    lateinit var todo6 : TextView
    lateinit var todoImg2 : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // 실시간 db 관리 객체 얻어오기
        val database : FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef : DatabaseReference = database.getReference()

        add2 = findViewById(R.id.add2) // 추가 버튼
        todo2 = findViewById(R.id.todo2) // 텍스트뷰
        add3 = findViewById(R.id.add3)
        todo3 = findViewById(R.id.todo3)
        add4 = findViewById(R.id.add4)
        todo4 = findViewById(R.id.todo4)
        add5 = findViewById(R.id.add5)
        todo5 = findViewById(R.id.todo5)
        add6 = findViewById(R.id.add6)
        todo6 = findViewById(R.id.todo6)

        todoImg2 = findViewById(R.id.todo_check2)

        // 여기에서 false 해 줘서 문제인 듯
        var number2 = false
        var number3 = false
        var number4 = false
        var number5 = false
        var number6= false

        // 체크된 거 인텐트로 받을 경우
        var checkText = intent.getStringExtra("checked")
        // 한 군데 설정은 됨
        if (checkText != null) {
            Log.d("number2", number2.toString()) // false로 출력됨
            add2.visibility = View.GONE // 버튼 없애기
            // 텍스트 추가
            todo2.visibility = View.VISIBLE
            todo2.text = checkText
            // 이미지 바꾸기
            todoImg2.setImageResource(R.drawable.honey)
            myRef.child("k-bee_database").child("todo2").setValue(checkText)
            myRef.child("k-bee_database").child("IsCheck2").setValue(true)
        }
        /*if (checkText != null) {
            if (number2) {
                add2.visibility = View.GONE // 버튼 없애기
                // 텍스트 추가
                todo2.visibility = View.VISIBLE
                todo2.text = checkText
                number2 = false // 다시 false 처리해 주기
                Log.d("number2", number2.toString())
            }
            else if (number3) {
                add3.visibility = View.GONE // 버튼 없애기
                // 텍스트 추가
                todo3.visibility = View.VISIBLE
                todo3.text = checkText
                number3 = false
            }
            else if (number4) {
                add4.visibility = View.GONE // 버튼 없애기
                // 텍스트 추가
                todo4.visibility = View.VISIBLE
                todo4.text = checkText
                number4 = false
            }
            else if (number5) {
                add5.visibility = View.GONE // 버튼 없애기
                // 텍스트 추가
                todo5.visibility = View.VISIBLE
                todo5.text = checkText
                number5 = false
            }
            else if (number6) {
                add6.visibility = View.GONE // 버튼 없애기
                // 텍스트 추가
                todo6.visibility = View.VISIBLE
                todo6.text = checkText
                number6 = false
            }
        }*/

        // 버튼 눌렀을 때
        add2.setOnClickListener {
            number2 = true
            Log.d("number2", number2.toString())
            // 리스트 추가 화면으로 이동
            var intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }

        add3.setOnClickListener {
            // 리스트 추가 화면으로 이동
            number3 = true
            var intent = Intent(this, ShareActivity::class.java)
            startActivity(intent)
        }

        add4.setOnClickListener {
            // 리스트 추가 화면으로 이동
            number4 = true
            var intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }

        add5.setOnClickListener {
            // 리스트 추가 화면으로 이동
            number5 = true
            var intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }

        add6.setOnClickListener {
            // 리스트 추가 화면으로 이동
            number6 = true
            var intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }
    }
}
