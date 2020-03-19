package com.pickletech.githubapplication.view

interface ItemClickListener<T> {
    fun onItemClick(item: T)
    fun showMore()
}