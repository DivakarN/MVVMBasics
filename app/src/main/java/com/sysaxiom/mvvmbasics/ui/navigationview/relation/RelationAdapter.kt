package com.sysaxiom.mvvmbasics.ui.navigationview.relation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sysaxiom.mvvmbasics.R
import com.sysaxiom.mvvmbasics.data.db.entities.Relative

class RelationAdapter(var relatives : List<Relative>) : RecyclerView.Adapter<RelationAdapter.RelationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.relation_card_view, parent, false)
        return RelationViewHolder(view)
    }

    override fun getItemCount(): Int {
        return relatives.size
    }

    override fun onBindViewHolder(holder: RelationViewHolder, position: Int) {
        var relative : Relative = relatives[position]

        if(relative.name.isNullOrEmpty()){
            holder.text_view_relation_name.setText("--")
        } else {
            holder.text_view_relation_name.setText(relative.name)
        }

        if(relative.type.isNullOrEmpty()){
            holder.text_view_relation_type.setText("--")
        } else {
            holder.text_view_relation_type.setText(relative.type)
        }

        if(relative.mobile.isNullOrEmpty()){
            holder.text_view_phone_number.setText("--")
        } else {
            holder.text_view_phone_number.setText(relative.mobile)
        }

        if(relative.email.isNullOrEmpty()){
            holder.text_view_email.setText("--")
        } else {
            holder.text_view_email.setText(relative.email)
        }
    }

    inner class RelationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text_view_relation_name : TextView = itemView.findViewById(R.id.text_view_relation_name)
        var text_view_relation_type : TextView = itemView.findViewById(R.id.text_view_relation_type)
        var text_view_phone_number : TextView = itemView.findViewById(R.id.text_view_phone_number)
        var text_view_email : TextView = itemView.findViewById(R.id.text_view_email)
    }
}
