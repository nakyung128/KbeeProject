package com.example.k_bee

import android.graphics.Bitmap
import android.graphics.BitmapFactory
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

class BadgeFragment : Fragment(R.layout.fragment_badge) {

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

        var badgeList = arrayOfNulls<ImageView?>(5)

        auth = FirebaseAuth.getInstance() // 사용자 uid
        user = auth.currentUser?.uid.toString() // 유저 정보


        myRef.child("$user").child("num").get().addOnSuccessListener {
            var num = it.value.toString()
            for (i in 0 until badgeList.size) {
                myRef.child("$user").child("badge${i+1}").get().addOnSuccessListener {
                    if (it.value != null) {
                        val image : String = it.value.toString()
                        val imgBitmap : Bitmap? = StringToBitmap(image)
                        val badgeID : String = "badge" + (i + 1)
                        /*val pi  = PackageInfo()
                        val packageName = pi.packageName*/
                        val resID = requireContext().resources.getIdentifier(badgeID, "id", requireContext().packageName)
                        //var badgeName : ImageView = view.findViewById(resID)
                        badgeList[i] = view.findViewById(resID) as ImageView
                        badgeList[i]?.setImageBitmap(imgBitmap)
                    }
                }
            }
        }
        return view
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


}




