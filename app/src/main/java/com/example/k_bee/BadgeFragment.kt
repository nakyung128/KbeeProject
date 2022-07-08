package com.example.k_bee

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.io.*
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
        // DB 사용
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef: DatabaseReference = database.getReference()
        val view = inflater.inflate(R.layout.fragment_badge, container, false)

        auth = FirebaseAuth.getInstance() // 사용자 uid
        user = auth.currentUser?.uid.toString() // 유저 정보

        badge1 = view.findViewById(R.id.badge1)

        myRef.child("$user").child("badge").get().addOnSuccessListener {
            if (it.value != null) {
                val image : String = it.value.toString()
                val imgBitmap : Bitmap? = StringToBitmap(image)
                //resizeBitmap(imgBitmap, 1024)
                //Log.d("imgBitmap", imgBitmap.toString())
                //val drawable = BitmapDrawable(imgBitmap)
                //val b : ByteArray = binaryStringToByteArray(image)
                //val stream = ByteArrayInputStream(b)
                //Log.d("stream", stream.toString())
                //val downImg = Drawable.createFromStream(stream, "image")
                badge1.setImageBitmap(imgBitmap)
            }
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

    // String을 Bitmap으로 변환시켜주는 함수
    fun StringToBitmap(encodedString : String): Bitmap? {
         return try {
             val encodeByte: ByteArray =
                 Base64.decode(encodedString, Base64.DEFAULT)
             BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.size)

         } catch (e: Exception) {
             e.message
            return null
         }

    }

    fun convertStreamToString(`is`: BufferedInputStream): String? {
        val reader = BufferedReader(InputStreamReader(`is`))
        val sb = StringBuilder()
        var line: String? = null
        try {
            while (reader.readLine().also { line = it } != null) {
                sb.append(line).append('\n')
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                `is`.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return sb.toString()
    }

    /*fun resizeBitmap(source: Bitmap?, maxLength: Int): Bitmap? {
        try {
            if (source!!.height >= source.width) {
                if (source.height <= maxLength) { // if image height already smaller than the required height
                    return source
                }

                val aspectRatio = source.width.toDouble() / source.height.toDouble()
                val targetWidth = (maxLength * aspectRatio).toInt()
                val result = Bitmap.createScaledBitmap(source, targetWidth, maxLength, false)
                return result
            } else {
                if (source.width <= maxLength) { // if image width already smaller than the required width
                    return source
                }

                val aspectRatio = source.height.toDouble() / source.width.toDouble()
                val targetHeight = (maxLength * aspectRatio).toInt()

                val result = Bitmap.createScaledBitmap(source, maxLength, targetHeight, false)
                return result
            }
        } catch (e: Exception) {
            return source
        }
    }*/
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


