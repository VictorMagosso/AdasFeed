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
import com.google.android.material.snackbar.Snackbar

data class Post(
    val userName: String,
    val description: String,
    val imagePost: Int,
    val imageUser: Int,
    var isLiked: Boolean = false,
    val isFavorite: Boolean = false
)

private val uniquePosts = listOf(
    Post(
        userName = "Jonas do Dark",
        description = "Hoje foi dia de muita gravação !!!",
        imagePost = R.drawable.feed1,
        imageUser = R.drawable.user1
    ),
    Post(
        userName = "Marcela",
        description = "Olhem meus doguinhos que fofos",
        imagePost = R.drawable.feed2,
        imageUser = R.drawable.user2
    )
)

private const val MOCKED_LIST_SIZE = 20

private fun mockedPostList() = MutableList(MOCKED_LIST_SIZE) { uniquePosts[it % uniquePosts.size].copy() }

class PostAdapter(private val postList: MutableList<Post> = mockedPostList()) :
    RecyclerView.Adapter<PostViewHolder>() {

    private lateinit var mRecyclerView: RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val postView =
            LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        return PostViewHolder(postView)
    }

    override fun getItemCount() = postList.size

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

        mRecyclerView = recyclerView
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.textUserName.text = postList[position].userName
        holder.textPostDescription.text = postList[position].description
        holder.imagePost.setImageResource(postList[position].imagePost)
        holder.imageUser.setImageResource(postList[position].imageUser)
        holder.imageLiked.setImageResource(getLikeButtonResource(postList[position].isLiked))

        holder.imageLiked.setOnClickListener {
            val likeState = getLikeButtonResource(toggleLikeAndReturn(position))
            holder.imageLiked.setImageResource(likeState)
            Toast.makeText(
                it.context,
                getLikedPosts().joinToString(
                    transform = { post -> "${post.userName} + ${post.isLiked}" },
                    separator = " "
                ),
                Toast.LENGTH_LONG
            ).show()
        }

        holder.rootView.setOnLongClickListener {
            val holdedPost = holdPostForDeletion(position)
            Snackbar.make(it, R.string.toast_message_post_removed, Snackbar.LENGTH_LONG).setAction(
                R.string.toast_action_post_removed,
                View.OnClickListener {
                    addPostToPosition(holdedPost.first, holdedPost.second)
                    mRecyclerView.scrollToPosition(holdedPost.second)
                }
            ).show()
            true
        }
    }

    fun getLikeButtonResource(isLiked: Boolean): Int {
        return if (isLiked) {
            R.drawable.liked_fill
        } else {
            R.drawable.liked1
        }
    }

    fun getLikedPosts(): List<Post> {
        return postList.filter { it.isLiked }
    }

    fun addPostToPosition(post: Post, position: Int) {
        postList.add(position, post)
        notifyItemInserted(postList.indexOf(post))
        notifyItemRangeChanged(position, postList.size)
    }

    fun addNewPost(newPost: Post) {
        postList.add(newPost)
        notifyItemInserted(postList.indexOf(newPost))
        // perde performance pois recria a lista inteira
        // o DiffUtilCallback é melhor
    }

    fun holdPostForDeletion(postPosition: Int): Pair<Post, Int> {
        val savedPost = postList.elementAt(postPosition)
        removePost(postPosition)
        return savedPost to postPosition
    }

    fun removePost(postPosition: Int) {
        postList.removeAt(postPosition)
        notifyItemRemoved(postPosition)
        notifyItemRangeChanged(postPosition, postList.size)
    }

    fun toggleLikeAndReturn(likedPostPosition: Int): Boolean {
        val post = postList.elementAt(likedPostPosition)
        post.apply { isLiked = !isLiked }
        return post.isLiked
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
    private val newPostList: List<Post>
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
    val rootView: View
    val textUserName: TextView
    val textPostDescription: TextView
    val imageUser: ImageView
    val imagePost: ImageView
    val imageLiked: ImageView
    val imageFavorite: ImageView

    init {
        rootView = view
        textUserName = view.findViewById(R.id.textUserName)
        textPostDescription = view.findViewById(R.id.textPostDescription)
        imageUser = view.findViewById(R.id.imageUser)
        imagePost = view.findViewById(R.id.imagePost)
        imageLiked = view.findViewById(R.id.imageLiked)
        imageFavorite = view.findViewById(R.id.imageFavorite)
    }
}
