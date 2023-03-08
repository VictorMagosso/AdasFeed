package com.victor.adasfeed.ui.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.victor.adasfeed.R
import com.victor.adasfeed.mock.Faker
import com.victor.adasfeed.model.Post

class PostAdapter(private val postList: MutableList<Post> = Faker.makePostList()) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    class PostViewHolder(view: View) : ViewHolder(view) {
        val textUserName: TextView
        val textPostDescription: TextView
        val imageUser: ImageView
        val imagePost: ImageView
        val imageLiked: ImageView
        val imageFavorite: ImageView

        init {
            textUserName = view.findViewById(R.id.textUserName)
            textPostDescription = view.findViewById(R.id.textPostDescription)
            imageUser = view.findViewById(R.id.imageUser)
            imagePost = view.findViewById(R.id.imagePost)
            imageLiked = view.findViewById(R.id.imageLiked)
            imageFavorite = view.findViewById(R.id.imageFavorite)
        }

        fun bind(postList: MutableList<Post>, position: Int) {
            textUserName.text = postList[position].userName
            textPostDescription.text = postList[position].description
            imagePost.setImageResource(postList[position].imagePost)
            imageUser.setImageResource(postList[position].imageUser)

            imageLiked.setOnClickListener {
                imageLiked.setImageResource(R.drawable.favorite_filled)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val postView =
            LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        return PostViewHolder(postView)
    }

    override fun getItemCount() = postList.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(postList, position)
    }

    fun addNewPost(newPost: Post) {
        postList.add(newPost)
        notifyItemInserted(postList.indexOf(newPost))
    }
}

class PostDiffUtilCallback(
    private val oldPostList: List<Post>,
    private val newPostList: List<Post>,
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldPostList.size

    override fun getNewListSize() = newPostList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldPostItem: Post = oldPostList[oldItemPosition]
        val newPostItem: Post = newPostList[newItemPosition]
        return oldPostItem.javaClass == newPostItem.javaClass
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldPostItem: Post = oldPostList[oldItemPosition]
        val newPostItem: Post = newPostList[newItemPosition]
        return oldPostItem.hashCode() == newPostItem.hashCode()
    }
}
