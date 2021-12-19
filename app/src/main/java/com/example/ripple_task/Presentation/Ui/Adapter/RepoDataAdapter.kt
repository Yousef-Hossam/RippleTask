package com.example.ripple_task.Presentation.Ui.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ripple_task.DominLayer.Model.ItemsItem
import com.example.ripple_task.R
import kotlinx.android.synthetic.main.repo_item.view.*


class RepoDataAdapter ( private val repoItems : ArrayList<ItemsItem>) : RecyclerView.Adapter<RepoDataAdapter.DataViewHolder>(){

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ItemsItem) {
            itemView.textViewRepoName.text = item.name
            itemView.textViewRepoDesc.text = item.description
            Glide.with(itemView.imageViewAvatar.context)
                .load(item.owner.avatarUrl)
                .into(itemView.imageViewAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.repo_item, parent,
                false
            )
        )

    override fun onBindViewHolder(holder: DataViewHolder, position: Int)  =
        holder.bind(repoItems[position])

    override fun getItemCount(): Int {
        if (repoItems.size>0){
            return repoItems.size
        }else{
            return 0
        }

    }
    fun addData(list: List<ItemsItem>) {
        if (list.isNotEmpty()){
            repoItems.addAll(list)
        }
    }
    fun clearData() {
        repoItems.clear()
    }
}