package com.victor.adasfeed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

data class Post(
    val userName: String,
    val description: String,
    val imagePost: Int,
    val imageUser: Int,
    val isLiked: Boolean = false,
    val isFavorite: Boolean = false,
)

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
    ), Post(
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

class PostAdapter(private val postList: MutableList<Post> = mockedPostList()) :
    RecyclerView.Adapter<PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val postView =
            LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        return PostViewHolder(postView)
    }

    override fun getItemCount() = postList.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.textUserName.text = postList[position].userName
        holder.textPostDescription.text = postList[position].description
        holder.imagePost.setImageResource(postList[position].imagePost)
        holder.imageUser.setImageResource(postList[position].imageUser)

        holder.imageLiked.setOnClickListener {
            holder.imageLiked.setImageResource(R.drawable.favorite_filled)
        }
    }

    fun addNewPost(newPost: Post) {
        postList.add(newPost)
        notifyItemInserted(postList.indexOf(newPost))
        // perde performance pois recria a lista inteira
        // o DiffUtilCallback é melhor
    }

    fun setNewList(newList: List<Post>) {
        val diffCallback = PostDiffUtilCallback(oldPostList = postList, newPostList = newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        postList.clear()
        postList.addAll(newList)
//        notifyDataSetChanged()
        diffResult.dispatchUpdatesTo(this)
    }
}

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
}