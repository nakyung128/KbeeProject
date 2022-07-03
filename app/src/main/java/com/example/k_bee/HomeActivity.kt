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

    lateinit var add1 : FloatingActionButton
    lateinit var todo1 : TextView
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
    lateinit var todoImg1 : ImageView
    lateinit var todoImg2 : ImageView
    lateinit var todoImg3 : ImageView
    lateinit var todoImg4 : ImageView
    lateinit var todoImg5 : ImageView
    lateinit var todoImg6 : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // 실시간 db 관리 객체 얻어오기
        val database : FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef : DatabaseReference = database.getReference()

        add1 = findViewById(R.id.add1) // 추가 버튼
        todo1 = findViewById(R.id.todo1) // 텍스트뷰
        add2 = findViewById(R.id.add2)
        todo2 = findViewById(R.id.todo2)
        add3 = findViewById(R.id.add3)
        todo3 = findViewById(R.id.todo3)
        add4 = findViewById(R.id.add4)
        todo4 = findViewById(R.id.todo4)
        add5 = findViewById(R.id.add5)
        todo5 = findViewById(R.id.todo5)
        add6 = findViewById(R.id.add6)
        todo6 = findViewById(R.id.todo6)

        todoImg1 = findViewById(R.id.todo_check1)
        todoImg2 = findViewById(R.id.todo_check2)
        todoImg3 = findViewById(R.id.todo_check3)
        todoImg4 = findViewById(R.id.todo_check4)
        todoImg5 = findViewById(R.id.todo_check5)
        todoImg6 = findViewById(R.id.todo_check6)

        // 투두리스트 이미 채워 넣음
        myRef.child("k-bee_database").child("todo1").get().addOnSuccessListener {
            if (it.value != "") {
                add1.visibility = View.GONE // 버튼 없애기
                todo1.visibility = View.VISIBLE
                todo1.text = it.value.toString()
            }
        }
        myRef.child("k-bee_database").child("todo2").get().addOnSuccessListener {
            if (it.value != "") {
                add2.visibility = View.GONE // 버튼 없애기
                todo2.visibility = View.VISIBLE
                todo2.text = it.value.toString()
            }
        }
        myRef.child("k-bee_database").child("todo3").get().addOnSuccessListener {
            if (it.value != "") {
                add3.visibility = View.GONE // 버튼 없애기
                todo3.visibility = View.VISIBLE
                todo3.text = it.value.toString()
            }
        }
        myRef.child("k-bee_database").child("todo4").get().addOnSuccessListener {
            if (it.value != "") {
                add4.visibility = View.GONE // 버튼 없애기
                todo4.visibility = View.VISIBLE
                todo4.text = it.value.toString()
            }
        }
        myRef.child("k-bee_database").child("todo5").get().addOnSuccessListener {
            if (it.value != "") {
                add5.visibility = View.GONE // 버튼 없애기
                todo5.visibility = View.VISIBLE
                todo5.text = it.value.toString()
            }
        }
        myRef.child("k-bee_database").child("todo6").get().addOnSuccessListener {
            if (it.value != "") {
                add6.visibility = View.GONE // 버튼 없애기
                todo6.visibility = View.VISIBLE
                todo6.text = it.value.toString()
            }
        }

        // 체크된 거 인텐트로 받을 경우
        var checkText = intent.getStringExtra("checked")
        // 한 군데 설정은 됨
        if (checkText != null) {
            add2.visibility = View.GONE // 버튼 없애기
            // 텍스트 추가
            todo2.visibility = View.VISIBLE
            todo2.text = checkText
            // 이미지 바꾸기
            todoImg2.setImageResource(R.drawable.honey)
            // 두 번째 todo에 선택했음
            myRef.child("k-bee_database").child("todo2").setValue(checkText)
            // 두 번째 거 실천했을 때
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
            // 리스트 추가 화면으로 이동
            var intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }

        add3.setOnClickListener {
            // 리스트 추가 화면으로 이동
            var intent = Intent(this, ShareActivity::class.java)
            startActivity(intent)
        }

        add4.setOnClickListener {
            // 리스트 추가 화면으로 이동
            var intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }

        add5.setOnClickListener {
            // 리스트 추가 화면으로 이동
            var intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }

        add6.setOnClickListener {
            // 리스트 추가 화면으로 이동
            var intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }
    }
}
