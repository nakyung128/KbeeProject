package com.example.k_bee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class ListActivity : AppCompatActivity() {
    lateinit var todoAdapter: RecyclerItemAdapter
    val datas = mutableListOf<todoData>()
    lateinit var todoView : RecyclerView
    lateinit var addBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_list)

        todoView = findViewById(R.id.recyclerView)

        initRecycler()

        // 추가 버튼 클릭했을 때
        addBtn.setOnClickListener {

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