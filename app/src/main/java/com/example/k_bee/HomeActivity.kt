package com.example.k_bee

import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kakao.sdk.common.KakaoSdk

class HomeActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(binding.root)
        }
    }