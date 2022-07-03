package com.example.k_bee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView

class ListActivity : AppCompatActivity() {
    lateinit var todoAdapter: RecyclerItemAdapter
    val datas = mutableListOf<todoData>()
    lateinit var todoView : RecyclerView
    lateinit var addBtn : Button
    lateinit var chooseBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        todoView = findViewById(R.id.recyclerView)
        addBtn = findViewById(R.id.addBtn) // 추가하기 버튼
        chooseBtn = findViewById(R.id.chooseBtn) // 선택 완료 버튼

        initRecycler()

        // 화면 나갔다 오면 직접 추가한 목표가 사라짐. 수정해야 함.
        // 추가 버튼 클릭했을 때
        addBtn.setOnClickListener {
            val dialog = addDialog(this)
            dialog.showDialog()
            dialog.setOnclickListener(object : addDialog.OnDialogClickListener {
                override fun onClicked(todo: String) {
                    todoAdapter.addItem(todo)
                }
            })
        }

        // 선택 완료 클릭했을 때
        chooseBtn.setOnClickListener {
            var checkedText = todoAdapter.checkText // 체크된 텍스트 내용

            // 홈 화면으로 이동하기
            // 선택된 체크박스 텍스트 가져와서 인텐트로 전달.
            var intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("checked", checkedText) // 값 전달
            startActivity(intent) // 이동
        }
    }
    private fun initRecycler() {
        todoAdapter = RecyclerItemAdapter(this)
        todoView.adapter = todoAdapter

        datas.apply {
            // 기본 제공 목표
            add(todoData(text = "안 쓰는 코드 뽑아 두기"))
            add(todoData(text = "불 끄고 외출하기"))
            add(todoData(text = "장바구니 사용하기"))
            add(todoData(text = "종이 대신 전자문서 사용하기"))
            add(todoData(text = "채식 식단 실천해 보기"))
            add(todoData(text = "하루 한 시간 소등하기"))
            add(todoData(text = "대중교통 또는 자전거 이용하기"))

            todoAdapter.datas = datas
            todoAdapter.notifyDataSetChanged()
        }
    }
}