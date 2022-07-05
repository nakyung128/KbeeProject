package com.example.k_bee

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

class BadgeFragment : Fragment(R.layout.fragment_badge) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_badge, container, false)

        val badgeList : ArrayList<ImageView> = arrayListOf<ImageView>()
        val length = badgeList.size

        /*for (i in 0 until length) {
            // 배지 이미지 아이디 식별 (1-30)
            val badgeID = "badge" + (i + 1)
            val resID = resources.getIdentifier(badgeID, "id", packageName)
            badgeList[i] = view.findViewById<View>(resID) as ImageView

            // ShareActivity 에서 달성 배지 받아오기
            val byteArray = intent.getByteArrayExtra("badge")
            val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray!!.size)
            badgeList[i].setImageBitmap(bitmap)

        }*/
    }
}