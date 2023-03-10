package com.victor.adasfeed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*


private fun mockedPostList() = mutableListOf(
    Post(
        userName = "Jonas do Dark",
        description = "Hoje foi dia de muita gravação !!!",
        imagePost = R.drawable.feed1,
        imageUser = R.drawable.user1,
    ),
    Post(
        userName = "Marcela",
        description = "Olhem meus doguinhos que fofos",
        imagePost = R.drawable.feed2,
        imageUser = R.drawable.user2,
    ),
    Post(
        userName = "Marcela",
        description = "Olhem meus doguinhos que fofos",
        imagePost = R.drawable.feed2,
        imageUser = R.drawable.user2,
    ),
    Post(
        userName = "Marcela",
        description = "Olhem meus doguinhos que fofos",
        imagePost = R.drawable.feed2,
        imageUser = R.drawable.user2,
    ),
    Post(
        userName = "Marcela",
        description = "Olhem meus doguinhos que fofos",
        imagePost = R.drawable.feed2,
        imageUser = R.drawable.user2,
    ),
    Post(
        userName = "Marcela",
        description = "Olhem meus doguinhos que fofos",
        imagePost = R.drawable.feed2,
        imageUser = R.drawable.user2,
    ),
    Post(
        userName = "Marcela",
        description = "Olhem meus doguinhos que fofos",
        imagePost = R.drawable.feed2,
        imageUser = R.drawable.user2,
    ),
    Post(
        userName = "Marcela",
        description = "Olhem meus doguinhos que fofos",
        imagePost = R.drawable.feed2,
        imageUser = R.drawable.user2,
    ),
    Post(
        userName = "Marcela",
        description = "Olhem meus doguinhos que fofos",
        imagePost = R.drawable.feed2,
        imageUser = R.drawable.user2,
    ),

)

class PostAdapter(
    private val postList: MutableList<Post> = mockedPostList(),
    private val clickUndoDelete: (DeleteItem) -> Unit
) :
    RecyclerView.Adapter<PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val postView =
            LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        return PostViewHolder(postView)
    }

    override fun getItemCount() = postList.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val currentPost = postList[position]
        holder.textUserName.text = currentPost.userName
        holder.textPostDescription.text = currentPost.description
        holder.imagePost.setImageResource(currentPost.imagePost)
        holder.imageUser.setImageResource(currentPost.imageUser)

        if (currentPost.isLiked) {
            holder.likedButton.setIconResource(R.drawable.ic_liked)
        } else {
            holder.likedButton.setIconResource(R.drawable.ic_empty_liked)
        }
        holder.likedButton.setOnClickListener {
            val newObject = currentPost.copy(isLiked = !currentPost.isLiked)
            val newList = ArrayList(postList)
            newList[position] = newObject
            setNewList(newList)
            if (newList[position].isLiked) {
                val likedPosts = postList.filter { it.isLiked }
                Toast.makeText(
                    holder.itemView.context,
                    "Post liked: \n ${likedPosts.joinToString {it.userName + ", " + it.description + "\n"}}",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }


        holder.imagePost.setOnLongClickListener { it ->
            removePost(position)
            Snackbar.make(it, "Post removido", Snackbar.LENGTH_LONG)
                .setAction("Desfazer") {
                    clickUndoDelete(DeleteItem(position, currentPost))
                }.setDuration(5_000).show()
            true
        }
    }

    fun addNewPost(newPost: Post, position: Int? = null) {
        if (position != null) {
            postList.add(position, newPost)
        } else {
            postList.add(newPost)
        }
        notifyItemInserted(postList.indexOf(newPost))
        // perde performance pois recria a lista inteira
        // o DiffUtilCallback é melhor
    }

    private fun removePost(position: Int) {
        val newPostList = ArrayList(postList)
        newPostList.removeAt(position)
        setNewList(newPostList)
    }


    private fun setNewList(newList: List<Post>) {
        val diffCallback = PostDiffUtilCallback(oldPostList = postList, newPostList = newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        postList.clear()
        postList.addAll(newList)
//        notifyDataSetChanged()
        diffResult.dispatchUpdatesTo(this)
    }


}

class PostViewHolder(view: View) : ViewHolder(view) {
    val textUserName: TextView
    val textPostDescription: TextView
    val imageUser: ImageView
    val imagePost: ImageView
    val imageFavorite: ImageView
    val likedButton: MaterialButton

    init {
        textUserName = view.findViewById(R.id.textUserName)
        textPostDescription = view.findViewById(R.id.textPostDescription)
        imageUser = view.findViewById(R.id.imageUser)
        imagePost = view.findViewById(R.id.imagePost)
        likedButton = view.findViewById(R.id.liked_button)
        imageFavorite = view.findViewById(R.id.imageFavorite)
    }
}