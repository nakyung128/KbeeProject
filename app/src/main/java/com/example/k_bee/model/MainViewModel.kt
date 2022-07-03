package com.example.k_bee.model

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.k_bee.BadgeActivity
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

    init {
        // 기본
        /*imageList = mutableListOf(
            R.drawable.badge1,
            R.drawable.badge2,
            R.drawable.badge3,
            R.drawable.badge4
        )*/

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
        badge()

        // 배경색 랜덤으로
        randomBackgroundSetClick = View.OnClickListener {
            val idx = (Math.random() * 6).toInt()
            background.postValue(backgroundList[idx])
        }
    }

    private fun badge() {
        // 모든 뱃지 다 수령함
        if (imageList.isEmpty()) {
            image.value = R.drawable.standing // 일단 서 있는 꿀벌 이미지로
            background.value = R.color.white
            content.value = "모든 뱃지를 다 모았어요"
            title.value = "대단해요!"
        } else { // 아직 뱃지 남아 있을 때
            val idx = (Math.random() * imageList.size).toInt()
            Log.d("img", imageList.size.toString())
            image.value = (imageList[idx])
            imageList.removeAt(idx) // 받은 뱃지 리스트에서 제거
            /*if (idx < imageList.size) {
                imageList[idx]= imageList[idx + 1]
            }*/
            background.value = R.color.white
            content.value = "새로운 뱃지 획득!"
            title.value = "BEE-SIDE 스토리 인증"
        }
    }
}