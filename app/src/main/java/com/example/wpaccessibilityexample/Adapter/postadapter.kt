package com.example.wpaccessibilityexample.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.wpaccessibilityexample.R
import com.example.wpaccessibilityexample.data.Model
import com.example.wpaccessibilityexample.data.ModelItem

class postAdapter(private var mList: List<ModelItem>, val onClick: OnClickListener) : RecyclerView.Adapter<postAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_post, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = mList.get(holder.adapterPosition)

        holder.textView.text = item.title

        holder.card.setOnClickListener ( object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (onClick != null)
                    onClick?.equals(holder.adapterPosition)
            }
        })

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun refreshLists(body: Model) {
        mList = body
        notifyDataSetChanged()
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.itemName)
        val card : CardView = itemView.findViewById(R.id.card)
    }

    interface OnClickListener {
        fun onClick(position : Int )
    }


}
