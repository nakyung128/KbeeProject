package com.example.k_bee

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText

class addDialog (context: Context) {
    private val dialog = Dialog(context)
    private lateinit var onClickListener: OnDialogClickListener
    private lateinit var cancelBtn : Button
    private lateinit var okBtn : Button

    fun setOnclickListener(listener: OnDialogClickListener) {
        onClickListener = listener
    }

    fun showDialog() {
        dialog.setContentView(R.layout.add_dialog)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()

        val editTodo = dialog.findViewById<EditText>(R.id.editTodo)
        cancelBtn = dialog.findViewById<Button>(R.id.cancelBtn)
        okBtn = dialog.findViewById<Button>(R.id.okBtn)

        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }
        okBtn.setOnClickListener {
            onClickListener.onClicked(editTodo.text.toString())
            dialog.dismiss()
        }
    }
   interface OnDialogClickListener {
       fun onClicked(todo: String)
   }
}