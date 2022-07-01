package com.example.k_bee

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, "1e237fb02326f3776d48f513ce1566cd")
    }
}