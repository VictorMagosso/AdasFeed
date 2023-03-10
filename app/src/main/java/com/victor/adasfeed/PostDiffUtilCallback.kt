package com.victor.adasfeed

import androidx.recyclerview.widget.DiffUtil

class PostDiffUtilCallback(
    private val oldPostList: List<Post>,
    private val newPostList: List<Post>,
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldPostList.size

    override fun getNewListSize() = newPostList.size

    // osItensSãoOsMesmos?
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldPostItem: Post = oldPostList[oldItemPosition]
        val newPostItem: Post = newPostList[newItemPosition]
        return oldPostItem.javaClass == newPostItem.javaClass
    }

    // osConteudosSãoOsMesmos?
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldPostItem: Post = oldPostList[oldItemPosition]
        val newPostItem: Post = newPostList[newItemPosition]
        return oldPostItem.hashCode() == newPostItem.hashCode()
    }
}