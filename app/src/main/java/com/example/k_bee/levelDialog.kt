package com.example.k_bee

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText

class levelDialog (context: Context) {
    private val dialog = Dialog(context)
    lateinit var button: Button

    fun showDialog() {
        dialog.setContentView(R.layout.level_dialog)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()

        button = dialog.findViewById<Button>(R.id.okBtn)

        button.setOnClickListener {
            dialog.dismiss()
        }
    }
}