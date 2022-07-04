package com.example.k_bee

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class BadgeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_badge)

        lateinit var badgeList : Array<ImageView>

        val length = badgeList.size


        for (i in 0 until length) {
            // 배지 이미지 아이디 식별 (1-30)
            val badgeID = "badge" + (i + 1)
            val resID = resources.getIdentifier(badgeID, "id", packageName)
            badgeList[i] = findViewById<View>(resID) as ImageView

            // ShareActivity 에서 달성 배지 받아오기
            val byteArray = intent.getByteArrayExtra("badge")
            val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray!!.size)
            badgeList[i].setImageBitmap(bitmap)

        }

/*
        val badge = findViewById<View>(R.id.badge1) as ImageView


        val byteArray = intent.getByteArrayExtra("badge")
        val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray!!.size)

        badge.setImageBitmap(bitmap)*/



    }

}