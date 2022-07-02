package com.example.k_bee

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.k_bee.databinding.ActivityBadgeBinding.bind

class RecyclerItemAdapter (private val context: Context) : RecyclerView.Adapter<RecyclerItemAdapter.ViewHolder>() {

    var datas = mutableListOf<todoData>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflatedView = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(datas[position])
    }

    // 각 항목에 필요한 기능 구현
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val todoText : CheckBox = itemView.findViewById(R.id.todoItem)

        fun bind(item: todoData) {
            todoText.text = item.text
        }
    }
}