package com.example.k_bee

import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.io.ByteArrayInputStream
import kotlin.experimental.or

class BadgeFragment : Fragment(R.layout.fragment_badge) {

    //var badgeList: ArrayList<ImageView> = arrayListOf<ImageView>()
    lateinit var badge1: ImageView
    private lateinit var auth : FirebaseAuth
    // 사용자 고유 uid
    private var user : String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef: DatabaseReference = database.getReference()

        val view = inflater.inflate(R.layout.fragment_badge, container, false)

        // 사용자 uid
        auth = FirebaseAuth.getInstance()
        // 유저 정보
        user = auth.currentUser?.uid.toString()

        badge1 = view.findViewById(R.id.badge1)

        myRef.child("$user").child("badge").get().addOnSuccessListener {
            val image : String = it.value.toString()
            Toast.makeText(activity, image, Toast.LENGTH_SHORT).show()
            val b : ByteArray = binaryStringToByteArray(image)
            val stream = ByteArrayInputStream(b)
            val downImg = Drawable.createFromStream(stream, "image")
            badge1.setImageDrawable(downImg)
        }

        return view
    }
    fun binaryStringToByteArray(s: String) : ByteArray {
        val count : Int = s.length / 8
        val b = ByteArray(count)

        for (i in 1 until count) {
            val t = s.substring((i-1)*8, i*8)
            b[i-1] = binaryStringToByte(t)
        }
        return b
    }

    fun binaryStringToByte(s: String) : Byte {
        var ret : Byte
        var total : Byte = 0

        for (i in 0 until 8) {
            ret = if (s.get(7-i) == '1') (1.shl(i)).toByte() else 0
            total = (ret.or(total))
        }
        return total
    }
}


    /*private fun onCreateView(view:View, savedInstanceState:Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        /*badgeList = ArrayList(30)

        val length = badgeList.size

        // id 식별
        for (i in 0 until length)
        {
            val badgeID = "badge" + (i + 1)
            val resID = resources.getIdentifier(badgeID, "id", "packageName")
            badgeList[i] = view.findViewById(resID) as ImageView
        }*/

        // ShareActivity 에서 배지 이미지 불러오기

    }




        /*for (i in 0 until 30)
        {
            val byteArray = arguments?.getByteArray("badge")
            val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray!!.size)
            badgeList[i].setImageBitmap(bitmap)
        }*/

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

    /*val badge = findViewById<View>(R.id.badge1) as ImageView


   val byteArray = intent.getByteArrayExtra("badge")
   val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray!!.size)

   badge.setImageBitmap(bitmap)*/



}*/


