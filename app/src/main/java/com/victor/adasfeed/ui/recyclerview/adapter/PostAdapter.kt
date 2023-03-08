package com.victor.adasfeed.ui.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.android.material.snackbar.Snackbar
import com.victor.adasfeed.R
import com.victor.adasfeed.mock.Faker
import com.victor.adasfeed.model.Post

class PostAdapter(private val postList: MutableList<Post> = Faker.makePostList()) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    class PostViewHolder(view: View) : ViewHolder(view) {
        val tvUsername: TextView = view.findViewById(R.id.textUserName)
        val tvDescription: TextView = view.findViewById(R.id.textPostDescription)
        val ivUser: ImageView = view.findViewById(R.id.imageUser)
        val ivPost: ImageView = view.findViewById(R.id.imagePost)
        val ivHeart: ImageView = view.findViewById(R.id.imageLiked)
        val ivBookmark: ImageView = view.findViewById(R.id.imageFavorite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val postView =
            LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        return PostViewHolder(postView)
    }

    override fun getItemCount() = postList.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.apply {
            val p = postList[position]

            tvUsername.text = p.userName
            tvDescription.text = p.description
            ivPost.setImageResource(p.imagePost)
            ivUser.setImageResource(p.imageUser)

            ivHeart.setOnClickListener {
                ivHeart.setImageResource(R.drawable.favorite_filled)
            }

            ivPost.setOnLongClickListener {
                removePost(position)

                Snackbar.make(it, "Post deletado", 5000).apply{
                    setAction("desfazer") {
                        insertPost(p, position)
                    }
                }.show()

                true
            }
        }
    }

    fun addPost(p: Post) {
        postList.add(p)
        notifyItemInserted(postList.indexOf(p))
    }

    fun insertPost(p: Post, position: Int) {
        postList.add(position, p)
        notifyItemInserted(position)
    }

    fun removePost(position: Int) {
        postList.removeAt(position)
        notifyItemRemoved(position)
    }
}
