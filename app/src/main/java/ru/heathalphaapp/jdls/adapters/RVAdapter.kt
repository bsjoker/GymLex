package ru.heathalphaapp.jdls.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_item.view.*
import ru.heathalphaapp.jdls.R
import ru.heathalphaapp.jdls.models.VisitorModel

class RVAdapter(val visitors: ArrayList<VisitorModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        GroupVH(LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false))

    override fun getItemCount() = visitors.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)= holder.itemView.run {
        tv_fio.text = visitors[position].fio
        tv_age.text = visitors[position].age.toString()
        tv_section.text = visitors[position].section
        switch_button.isChecked = visitors[position].active
    }

    private inner class GroupVH(itemView: View) : RecyclerView.ViewHolder(itemView)
}
