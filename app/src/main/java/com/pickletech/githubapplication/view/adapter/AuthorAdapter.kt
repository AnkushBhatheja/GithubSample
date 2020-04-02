package com.pickletech.githubapplication.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pickletech.githubapplication.R
import com.pickletech.githubapplication.databinding.ItemAuthorBinding
import com.pickletech.githubapplication.databinding.ItemFooterBinding
import com.pickletech.githubapplication.model.Author
import com.pickletech.githubapplication.view.ItemClickListener

class AuthorAdapter constructor(private val listener: ItemClickListener<Author>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val authors: MutableList<Author> = mutableListOf()

    override fun getItemViewType(position: Int): Int {
        return if (position == authors.size - 1 && authors[position].id < 0) 0 else 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == 0) {
            val binding: ItemFooterBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_footer, parent, false
            );
            return FooterHolder(binding)
        }
        val binding: ItemAuthorBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_author, parent, false
        );
        return ItemHolder(binding)
    }


    override fun getItemCount(): Int {
        return authors.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is ItemHolder) {
            holder.mBinding.author = authors[position]
            holder.bind(authors[position], listener)
        } else if (holder is FooterHolder) {
            holder.itemView.setOnClickListener {
                listener.showMore()
            }
        }
    }

    fun addAll(items: List<Author>?) {
        authors.clear()
        authors.addAll(items ?: mutableListOf())
        notifyDataSetChanged()
    }

    fun clear() {
        authors.clear()
        notifyDataSetChanged()
    }

    class ItemHolder(val mBinding: ItemAuthorBinding) : RecyclerView.ViewHolder(mBinding.root) {

        fun bind(item: Author, listener: ItemClickListener<Author>) {
            mBinding.root.setOnClickListener {
                listener.onItemClick(item)
            }
        }
    }

    class FooterHolder(mBinding: ItemFooterBinding) :
        RecyclerView.ViewHolder(mBinding.root) {

    }
}