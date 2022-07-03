package com.example.k_bee.model

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.k_bee.R

class MainViewModel : ViewModel() {
    // 뷰에 나타낼 값들, 라이브 데이터 형식
    var image = MutableLiveData<Int>()
    var background = MutableLiveData<Int>()
    val content = MutableLiveData<String>()

    // 이미지, 배경 리스트들
    private var imageList = mutableListOf<Int>()
    private var backgroundList = mutableListOf<Int>()

    // 뷰 모델에서 onClick 메서드 구현
    //var randomImageSetClick: View.OnClickListener
    var randomBackgroundSetClick: View.OnClickListener

    init {
        // 기본

        imageList = mutableListOf(
            R.drawable.badge1,
            R.drawable.badge2,
            R.drawable.badge3,
            R.drawable.badge4
        )

        // 색상은 나중에 어울리는 거로 바꾸어야 할 듯
        backgroundList = mutableListOf(
            R.color.white,
            R.color.Ivory,
            R.color.AntiqueWhite,
            R.color.SkyBlue,
            R.color.YellowGreen,
            R.color.OrangeRed
        )

        // 이미지는 랜덤으로
        val idx = (Math.random() * 4).toInt()
        image.value = (imageList[idx])
        background.value = R.color.white
        content.value = "새로운 뱃지 획득!"

        // 배경색 랜덤으로
        randomBackgroundSetClick = View.OnClickListener {
            val idx = (Math.random() * 6).toInt()
            background.postValue(backgroundList[idx])
        }
    }
}