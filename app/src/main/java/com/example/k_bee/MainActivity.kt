package com.example.k_bee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val fl: FrameLayout by lazy {
        findViewById(R.id.frame_layout)
    }

    private val bn: BottomNavigationView by lazy {
        findViewById(R.id.bottom_navi)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(fl.id, HomeFragment()).commit()

        loadFragment(HomeFragment())

        bn.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.tab_home -> {
                    loadFragment(HomeFragment())
                }
                R.id.tab_badge -> {
                    loadFragment(BadgeFragment())
                }
                R.id.tab_mypage -> {
                    loadFragment(MypageFragment())
                }
            }
            true
        }
    }
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .commit()
    }
}