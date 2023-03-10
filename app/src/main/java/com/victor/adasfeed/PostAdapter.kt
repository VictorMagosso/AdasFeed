package com.victor.adasfeed

import android.content.Context
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.SnackbarLayout

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
        imagePost = R.drawable.stories1,
        imageUser = R.drawable.user3,
    ),
    Post(
        userName = "Marcela",
        description = "Olhem meus doguinhos que fofos",
        imagePost = R.drawable.stories4,
        imageUser = R.drawable.user4,
    ),
    Post(
        userName = "Marcela",
        description = "Olhem meus doguinhos que fofos",
        imagePost = R.drawable.stories3,
        imageUser = R.drawable.user5,
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
    private val postList: MutableList<Post> = mockedPostList()
) :
    RecyclerView.Adapter<PostViewHolder>() {

    var customSnackbarLayout: View? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val postView =
            LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)

        customSnackbarLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.snack_bar_layout, parent, false)
        return PostViewHolder(postView)
    }

    override fun getItemCount() = postList.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.textUserName.text = postList[position].userName
        holder.textPostDescription.text = postList[position].description
        holder.imagePost.setImageResource(postList[position].imagePost)
        holder.imageUser.setImageResource(postList[position].imageUser)

        holder.imageLiked.setOnClickListener {
            holder.imageLiked.setImageResource(R.drawable.liked_fill)
        }

        holder.imagePost.setOnLongClickListener {
            removePostById(position, holder.itemView.rootView) { removedPost ->
                addNewPost(removedPost, position)
            }
            true
        }
    }

    private fun addNewPost(newPost: Post, index: Int) {
        postList.add(index, newPost)
        notifyItemInserted(index)
        // perde performance pois recria a lista inteira
        // o DiffUtilCallback é melhor
    }

    fun addNewPost(newPost: Post) {
        postList.add(newPost)
        notifyItemInserted(postList.indexOf(newPost))
        // perde performance pois recria a lista inteira
        // o DiffUtilCallback é melhor
    }

    private fun removePostById(position: Int, parentView: View, undoRemove: (Post) -> Unit) {
        val postToBeRemoved = postList[position]
        val successRemoving = postList.remove(postToBeRemoved)

        if (successRemoving) {
            notifyItemRemoved(position)

            val snackbar = Snackbar.make(
                parentView,
                "Post excluído!",
                Snackbar.LENGTH_INDEFINITE
            )

            val snackbarLayout = snackbar.view as SnackbarLayout
            snackbarLayout.setPadding(0)

            val undoButton = customSnackbarLayout?.findViewById<Button>(R.id.buttonUndoRemove)

            snackbarLayout.removeView(customSnackbarLayout)
            snackbarLayout.addView(customSnackbarLayout, 0)
            snackbar.show()

            val timer = object : CountDownTimer(5000L, 10) {
                val progressBar = customSnackbarLayout?.findViewById<ProgressBar>(R.id.progressHorizontalBar)
                override fun onTick(millisUntilFinished: Long) {
                    progressBar?.max = 5000
                    progressBar?.progress = millisUntilFinished.toInt()
                }

                override fun onFinish() {
                    progressBar?.progress = 0
                    snackbar.dismiss()
                }
            }
            timer.start()

            undoButton?.setOnClickListener {
                undoRemove(postToBeRemoved)
                timer.cancel()
                snackbar.dismiss()
            }
        }
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