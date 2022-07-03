package com.example.k_bee

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class BadgeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_badge)


        // 배지 이미지 아이디
        // val badge = resources.getIdentifier("badge", "int", "k_bee")
        val badge = findViewById<View>(R.id.badge1) as ImageView


        val byteArray = intent.getByteArrayExtra("badge")
        val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray!!.size)

        badge.setImageBitmap(bitmap)
    }
}