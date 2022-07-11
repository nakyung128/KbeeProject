package com.example.k_bee

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    lateinit var add1 : Button
    lateinit var todo1 : TextView
    lateinit var add2 : Button
    lateinit var todo2 : TextView
    lateinit var add3 : Button
    lateinit var todo3 : TextView
    lateinit var add4 : Button
    lateinit var todo4 : TextView
    lateinit var add5 : Button
    lateinit var todo5 : TextView
    lateinit var add6 : Button
    lateinit var todo6 : TextView
    lateinit var todoImg1 : ImageView
    lateinit var todoImg2 : ImageView
    lateinit var todoImg3 : ImageView
    lateinit var todoImg4 : ImageView
    lateinit var todoImg5 : ImageView
    lateinit var todoImg6 : ImageView
    lateinit var weekText : TextView

    lateinit var friendSearch : Button

    var check1 : Boolean = false
    var check2 : Boolean = false
    var check3 : Boolean = false
    var check4 : Boolean = false
    var check5 : Boolean = false
    var check6 : Boolean = false
    var allCheck : Boolean = false

    var month : String = ""
    var week : String = ""

    private lateinit var auth : FirebaseAuth

    // 사용자 고유 uid
    private var user : String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef: DatabaseReference = database.getReference()

        // 사용자 uid
        auth = FirebaseAuth.getInstance()

        // 유저 정보
        user = auth.currentUser?.uid.toString()

        // 해당하는 uid가 없는 경우
        myRef.child("$user").get().addOnSuccessListener {
            if (it.value == null) {
                val dataInput = DataModel(
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                )
                myRef.child(user).setValue(dataInput)
            }
        }

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        add1 = view.findViewById(R.id.add1) // 추가 버튼
        todo1 = view.findViewById(R.id.todo1) // 텍스트뷰
        add2 = view.findViewById(R.id.add2)
        todo2 = view.findViewById(R.id.todo2)
        add3 = view.findViewById(R.id.add3)
        todo3 = view.findViewById(R.id.todo3)
        add4 = view.findViewById(R.id.add4)
        todo4 = view.findViewById(R.id.todo4)
        add5 = view.findViewById(R.id.add5)
        todo5 = view.findViewById(R.id.todo5)
        add6 = view.findViewById(R.id.add6)
        todo6 = view.findViewById(R.id.todo6)

        todoImg1 = view.findViewById(R.id.todo_check1)
        todoImg2 = view.findViewById(R.id.todo_check2)
        todoImg3 = view.findViewById(R.id.todo_check3)
        todoImg4 = view.findViewById(R.id.todo_check4)
        todoImg5 = view.findViewById(R.id.todo_check5)
        todoImg6 = view.findViewById(R.id.todo_check6)

        friendSearch = view.findViewById(R.id.friendBtn)

        friendSearch.setOnClickListener {
            var intent = Intent(context, FriendActivity::class.java)
            startActivity(intent)
        }

        weekText = view.findViewById(R.id.now_week) // 이번 주

        var calendar: Calendar = Calendar.getInstance()
        month = (calendar.get(Calendar.MONTH) + 1).toString()
        week = calendar.get(Calendar.WEEK_OF_MONTH).toString()

        weekText.text = "${month}월 ${week}주차"

        myRef.child("$user").child("isCheck1").get().addOnSuccessListener {
            if (it.value == true) {
                todoImg1.setImageResource(R.drawable.honey)
                check1 = true
            } else {
                check1 = false
            }
        }
        myRef.child("$user").child("isCheck2").get().addOnSuccessListener {
            if (it.value == true) {
                todoImg2.setImageResource(R.drawable.honey)
                check2 = true
            } else {
                check2 = false
            }
        }
        myRef.child("$user").child("isCheck3").get().addOnSuccessListener {
            if (it.value == true) {
                todoImg3.setImageResource(R.drawable.honey)
                check3 = true
            } else {
                check3 = false
            }
        }
        myRef.child("$user").child("isCheck4").get().addOnSuccessListener {
            if (it.value == true) {
                todoImg4.setImageResource(R.drawable.honey)
                check4 = true
            } else {
                check4 = false
            }
        }
        myRef.child("$user").child("isCheck5").get().addOnSuccessListener {
            if (it.value == true) {
                todoImg5.setImageResource(R.drawable.honey)
                check5 = true
            } else {
                check5 = false
            }
        }
        myRef.child("$user").child("isCheck6").get().addOnSuccessListener {
            if (it.value == true) {
                todoImg6.setImageResource(R.drawable.honey)
                check6 = true
            } else {
                check6 = false
            }
        }
        myRef.child("$user").child("allCheck").get().addOnSuccessListener {
            if (it.value == true) {
                allCheck = true
            } else {
                allCheck = false
            }
        }

        // 투두리스트 이미 채워 넣음
        myRef.child("$user").child("todo1").get().addOnSuccessListener {
            if (it.value != "") {
                add1.visibility = View.GONE // 버튼 없애기
                todo1.visibility = View.VISIBLE
                todo1.text = it.value.toString()
            }
        }
        myRef.child("$user").child("todo2").get().addOnSuccessListener {
            if (it.value != "") {
                add2.visibility = View.GONE // 버튼 없애기
                todo2.visibility = View.VISIBLE
                todo2.text = it.value.toString()
            }
        }
        myRef.child("$user").child("todo3").get().addOnSuccessListener {
            if (it.value != "") {
                add3.visibility = View.GONE // 버튼 없애기
                todo3.visibility = View.VISIBLE
                todo3.text = it.value.toString()
            }
        }
        myRef.child("$user").child("todo4").get().addOnSuccessListener {
            if (it.value != "") {
                add4.visibility = View.GONE // 버튼 없애기
                todo4.visibility = View.VISIBLE
                todo4.text = it.value.toString()
            }
        }
        myRef.child("$user").child("todo5").get().addOnSuccessListener {
            if (it.value != "") {
                add5.visibility = View.GONE // 버튼 없애기
                todo5.visibility = View.VISIBLE
                todo5.text = it.value.toString()
            }
        }
        myRef.child("$user").child("todo6").get().addOnSuccessListener {
            if (it.value != "") {
                add6.visibility = View.GONE // 버튼 없애기
                todo6.visibility = View.VISIBLE
                todo6.text = it.value.toString()
            }
        }

        add1.setOnClickListener {
            var intent = Intent(context, ListActivity::class.java)
            intent.putExtra("number", 1)
            startActivity(intent)
        }

        add2.setOnClickListener {
            // 리스트 추가 화면으로 이동
            var intent = Intent(context, ListActivity::class.java)
            intent.putExtra("number", 2)
            startActivity(intent)
        }
        add3.setOnClickListener {
            // 리스트 추가 화면으로 이동
            var intent = Intent(context, ListActivity::class.java)
            intent.putExtra("number", 3)
            startActivity(intent)
        }
        add4.setOnClickListener {
            // 리스트 추가 화면으로 이동
            var intent = Intent(context, ListActivity::class.java)
            intent.putExtra("number", 4)
            startActivity(intent)
        }
        add5.setOnClickListener {
            // 리스트 추가 화면으로 이동
            var intent = Intent(context, ListActivity::class.java)
            intent.putExtra("number", 5)
            startActivity(intent)
        }
        add6.setOnClickListener {
            // 리스트 추가 화면으로 이동
            var intent = Intent(context, ListActivity::class.java)
            intent.putExtra("number", 6)
            startActivity(intent)
        }

        // 텍스트 눌렀을 때 목표 바꿀 수 있도록
        todo1.setOnClickListener {
            var intent = Intent(context, ListActivity::class.java)
            intent.putExtra("number", 1)
            startActivity(intent)
        }
        todo2.setOnClickListener {
            var intent = Intent(context, ListActivity::class.java)
            intent.putExtra("number", 2)
            startActivity(intent)
        }
        todo3.setOnClickListener {
            var intent = Intent(context, ListActivity::class.java)
            intent.putExtra("number", 3)
            startActivity(intent)
        }
        todo4.setOnClickListener {
            var intent = Intent(context, ListActivity::class.java)
            intent.putExtra("number", 4)
            startActivity(intent)
        }
        todo5.setOnClickListener {
            var intent = Intent(context, ListActivity::class.java)
            intent.putExtra("number", 5)
            startActivity(intent)
        }
        todo6.setOnClickListener {
            var intent = Intent(context, ListActivity::class.java)
            intent.putExtra("number", 6)
            startActivity(intent)
        }

        // 벌꿀집 이미지 클릭했을 때 색 채우기 (IsCheck == false 일 때)
        todoImg1.setOnClickListener {
            myRef.child("$user").child("todo1").get().addOnSuccessListener {
                if (it.value != "") {
                    myRef.child("$user").child("isCheck1").get().addOnSuccessListener {
                        if (it.value == false) { // 현재 상태 false인 경우
                            Toast.makeText(activity, "환경 보호에 한 걸음 더!", Toast.LENGTH_SHORT).show()                    // 색 채우기
                            todoImg1.setImageResource(R.drawable.honey)
                            myRef.child("$user").child("isCheck1").setValue(true) // IsCheck1 상태 true로 변경
                            check1 = true
                            // 모든 목표 다 달성
                            if (check1 && check2 && check3 && check4 && check5 && check6 && !allCheck) {
                                myRef.child("$user").child("allCheck").setValue(true)
                                allCheck = true
                                Toast.makeText(activity, "모든 목표를 다 달성했어요!", Toast.LENGTH_SHORT).show()
                                var intent = Intent(activity, ShareActivity::class.java)
                                startActivity(intent)
                            }
                        } else {
                            todoImg1.setImageResource(R.drawable.empty)
                            myRef.child("$user").child("isCheck1").setValue(false) // 상태 false로 변경
                            check1 = false
                        }
                    }
                } else {
                    Toast.makeText(context, "목표 설정을 먼저 완료해 주세요!", Toast.LENGTH_SHORT).show()
                }
            }
        }
        todoImg2.setOnClickListener {
            myRef.child("$user").child("todo2").get().addOnSuccessListener {
                if (it.value != "") {
                    myRef.child("$user").child("isCheck2").get().addOnSuccessListener {
                        if (it.value == false) { // 현재 상태 false인 경우
                            Toast.makeText(activity, "환경 보호에 한 걸음 더!", Toast.LENGTH_SHORT).show()                    // 색 채우기
                            todoImg2.setImageResource(R.drawable.honey)
                            myRef.child("$user").child("isCheck2").setValue(true) // IsCheck1 상태 true로 변경
                            check2 = true
                            // 모든 목표 다 달성
                            if (check1 && check2 && check3 && check4 && check5 && check6 && !allCheck) {
                                myRef.child("$user").child("allCheck").setValue(true)
                                allCheck = true
                                Toast.makeText(activity, "모든 목표를 다 달성했어요!", Toast.LENGTH_SHORT).show()
                                var intent = Intent(context, ShareActivity::class.java)
                                startActivity(intent)
                            }
                        } else {
                            todoImg2.setImageResource(R.drawable.empty)
                            myRef.child("$user").child("isCheck2").setValue(false) // 상태 false로 변경
                            check2 = false
                        }
                    }
                } else {
                    Toast.makeText(context, "목표 설정을 먼저 완료해 주세요!", Toast.LENGTH_SHORT).show()
                }
            }
        }
        todoImg3.setOnClickListener {
            myRef.child("$user").child("todo3").get().addOnSuccessListener {
                if (it.value != "") {
                    myRef.child("$user").child("isCheck3").get().addOnSuccessListener {
                        if (it.value == false) {
                            Toast.makeText(activity, "환경 보호에 한 걸음 더!", Toast.LENGTH_SHORT).show()
                            // 색 채우기
                            todoImg3.setImageResource(R.drawable.honey)
                            myRef.child("$user").child("isCheck3").setValue(true) // IsCheck1 상태 true로 변경
                            check3 = true
                            // 모든 목표 다 달성
                            if (check1 && check2 && check3 && check4 && check5 && check6 && !allCheck) {
                                myRef.child("$user").child("allCheck").setValue(true)
                                allCheck = true
                                Toast.makeText(activity, "모든 목표를 다 달성했어요!", Toast.LENGTH_SHORT).show()
                                var intent = Intent(context, ShareActivity::class.java)
                                startActivity(intent)
                            }
                        } else {
                            todoImg3.setImageResource(R.drawable.empty)
                            myRef.child("$user").child("isCheck3").setValue(false) // 상태 false로 변경
                            check3 = false
                        }
                    }
                } else {
                    Toast.makeText(context, "목표 설정을 먼저 완료해 주세요!", Toast.LENGTH_SHORT).show()
                }
            }
        }
        todoImg4.setOnClickListener {
            myRef.child("$user").child("todo4").get().addOnSuccessListener {
                if (it.value != "") {
                    myRef.child("$user").child("isCheck4").get().addOnSuccessListener {
                        if (it.value == false) {
                            Toast.makeText(activity, "환경 보호에 한 걸음 더!", Toast.LENGTH_SHORT).show()
                            // 색 채우기
                            todoImg4.setImageResource(R.drawable.honey)
                            myRef.child("$user").child("isCheck4").setValue(true) // IsCheck4 상태 true로 변경
                            check4 = true
                            // 모든 목표 다 달성
                            if (check1 && check2 && check3 && check4 && check5 && check6 && !allCheck) {
                                myRef.child("$user").child("allCheck").setValue(true)
                                allCheck = true
                                Toast.makeText(activity, "모든 목표를 다 달성했어요!", Toast.LENGTH_SHORT).show()
                                var intent = Intent(context, ShareActivity::class.java)
                                startActivity(intent)
                            }
                        } else {
                            todoImg4.setImageResource(R.drawable.empty)
                            myRef.child("$user").child("isCheck4").setValue(false) // 상태 false로 변경
                            check4 = false
                        }
                    }
                } else {
                    Toast.makeText(context, "목표 설정을 먼저 완료해 주세요!", Toast.LENGTH_SHORT).show()
                }
            }
        }
        todoImg5.setOnClickListener {
            myRef.child("$user").child("todo5").get().addOnSuccessListener {
                if (it.value != "") {
                    myRef.child("$user").child("isCheck5").get().addOnSuccessListener {
                        if (it.value == false) {
                            Toast.makeText(activity, "환경 보호에 한 걸음 더!", Toast.LENGTH_SHORT).show()
                            // 색 채우기
                            todoImg5.setImageResource(R.drawable.honey)
                            myRef.child("$user").child("isCheck5").setValue(true) // IsCheck1 상태 true로 변경
                            check5 = true
                            // 모든 목표 다 달성
                            if (check1 && check2 && check3 && check4 && check5 && check6 && !allCheck) {
                                myRef.child("$user").child("allCheck").setValue(true)
                                allCheck = true
                                Toast.makeText(activity, "모든 목표를 다 달성했어요!", Toast.LENGTH_SHORT).show()
                                var intent = Intent(context, ShareActivity::class.java)
                                startActivity(intent)
                            }
                        } else {
                            todoImg5.setImageResource(R.drawable.empty)
                            myRef.child("$user").child("isCheck5").setValue(false) // 상태 false로 변경
                            check5 = false
                        }
                    }
                } else {
                    Toast.makeText(context, "목표 설정을 먼저 완료해 주세요!", Toast.LENGTH_SHORT).show()
                }
            }
        }
        todoImg6.setOnClickListener {
            myRef.child("$user").child("todo6").get().addOnSuccessListener {
                if (it.value != "") {
                    myRef.child("$user").child("isCheck6").get().addOnSuccessListener {
                        if (it.value == false) {
                            Toast.makeText(activity, "환경 보호에 한 걸음 더!", Toast.LENGTH_SHORT).show()
                            // 색 채우기
                            todoImg6.setImageResource(R.drawable.honey)
                            myRef.child("$user").child("isCheck6").setValue(true) // 상태 true로 변경
                            check6 = true
                            // 모든 목표 다 달성
                            if (check1 && check2 && check3 && check4 && check5 && check6 && !allCheck) {
                                myRef.child("$user").child("allCheck").setValue(true) // 상태 true로 변경
                                allCheck = true
                                Toast.makeText(activity, "모든 목표를 다 달성했어요!", Toast.LENGTH_SHORT).show()
                                var intent = Intent(context, ShareActivity::class.java)
                                startActivity(intent)
                            }
                        } else {
                            todoImg6.setImageResource(R.drawable.empty)
                            myRef.child("$user").child("isCheck6").setValue(false) // 상태 false로 변경
                            check6 = false
                        }
                    }
                } else {
                    Toast.makeText(context, "목표 설정을 먼저 완료해 주세요!", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return view
    }
}