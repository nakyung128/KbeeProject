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
    val title = MutableLiveData<String>()

    // 이미지, 배경 리스트들
    private var imageList = mutableListOf<Int>(R.drawable.badge1, R.drawable.badge2, R.drawable.badge3, R.drawable.badge4)
    private var backgroundList = mutableListOf<Int>()

    // 뷰 모델에서 onClick 메서드 구현
    //var randomImageSetClick: View.OnClickListener
    var randomBackgroundSetClick: View.OnClickListener
    var badgeBtnClick : View.OnClickListener

    init {

        // 배경색 랜덤
        backgroundList = mutableListOf(
            R.color.white,
            R.color.LightSalmon,
            R.color.LightPink,
            R.color.LightYellow,
            R.color.LightBlue,
            R.color.LightGrey
        )

        // 이미지는 랜덤으로
        badge()

        // 배경색 랜덤으로
        randomBackgroundSetClick = View.OnClickListener {
            val idx = (Math.random() * 6).toInt()
            background.postValue(backgroundList[idx])
        }

        badgeBtnClick = View.OnClickListener {

        }

    }

    private fun badge() {
        val idx = (Math.random() * imageList.size).toInt()
        image.value = (imageList[idx])
        imageList.remove(imageList[idx]) // 받은 뱃지 리스트에서 제거
        background.value = R.color.white
        content.value = "새로운 뱃지 획득!"
        title.value = "BEE-SIDE 스토리 인증"

    }



}