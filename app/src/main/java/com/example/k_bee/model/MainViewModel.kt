package com.example.k_bee.model

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.media.Image
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.graphics.drawable.toDrawable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.k_bee.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.io.ByteArrayOutputStream


class MainViewModel : ViewModel() {
    // 뷰에 나타낼 값들, 라이브 데이터 형식
    var image = MutableLiveData<Int>()
    var background = MutableLiveData<Int>()
    val content = MutableLiveData<String>()
    val title = MutableLiveData<String>()

    // firebase 권한 설정
    // val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    // val myRef: DatabaseReference = database.getReference()

    // 이미지, 배경 리스트들
    private var imageList = mutableListOf<Int>(R.drawable.badge1, R.drawable.badge2, R.drawable.badge3, R.drawable.badge4)
    private var backgroundList = mutableListOf<Int>()

    // 뷰 모델에서 onClick 메서드 구현
    //var randomImageSetClick: View.OnClickListener
    var randomBackgroundSetClick: View.OnClickListener
    var badgeBtnClick : View.OnClickListener

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

        badgeBtnClick = View.OnClickListener {

        }

        // 얻은 배지 firebase 로 이동
        //badgeUpload()
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
            image.value = (imageList[idx])
            imageList.remove(imageList[idx]) // 받은 뱃지 리스트에서 제거
            background.value = R.color.white
            content.value = "새로운 뱃지 획득!"
            title.value = "BEE-SIDE 스토리 인증"

            //var badgeImage : ColorDrawable = imageList[idx].toDrawable()
        }

    }

    // firebase 에 배지 이미지 업로드
    /*private fun badgeUpload() {
        var databaseRef: DatabaseReference = FirebaseDatabase.getInstance().reference


        val bitmap : Bitmap = (image as BitmapDrawable).getBitmap()

        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)

        val uploadImage = stream.toByteArray()
        val simage = byteArrayToBinaryString(uploadImage)

        val key : String = myRef.child("/badges").push().key!!
        val childUpdates : HashMap<String,Any> = HashMap()
        childUpdates["/badges/$key"] = simage.toString()
        myRef.updateChildren(childUpdates)
    }

    fun byteArrayToBinaryString(b: ByteArray) : StringBuilder {
        var sb : StringBuilder = StringBuilder()

        for (i in 0 until b.size)
        {
            sb.append(byteToBinaryString(b[i]))
        }

        return sb
    }

    fun byteToBinaryString(n : Byte) : String {
        var sb: StringBuilder = StringBuilder("00000000")
        for (bit in 0 until 8)
        {
            if ((n.toInt().shr(bit) and 1) > 0)
            {
                sb.setCharAt(7 - bit, '1')
            }
        }

        return sb.toString()
    }*/

}