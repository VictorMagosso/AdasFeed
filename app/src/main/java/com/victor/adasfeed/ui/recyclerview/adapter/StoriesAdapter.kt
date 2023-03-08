package com.victor.adasfeed.ui.recyclerview.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.victor.adasfeed.R
import com.victor.adasfeed.mock.Faker
import com.victor.adasfeed.model.Story

class StoriesAdapter(
    private val storyList: List<Story> = Faker.makeStoryList()
) : RecyclerView.Adapter<StoriesAdapter.StoriesViewHolder>() {

    inner class StoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textUserName: TextView
        val textTimeInMinutes: TextView
        val imageUser: ImageView
        val imagePost: ImageView

        init {
            textUserName = view.findViewById(R.id.textUserName)
            textTimeInMinutes = view.findViewById(R.id.textTimeInMinutes)
            imageUser = view.findViewById(R.id.imageUser)
            imagePost = view.findViewById(R.id.imagePost)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoriesViewHolder {
        val storiesItemView = LayoutInflater.from(parent.context).inflate(R.layout.stories_item, parent, false)
        Log.d("contexto no StoriesAdapter", parent.context.applicationContext.toString())
        return StoriesViewHolder(view = storiesItemView)
    }

    override fun getItemCount(): Int {
        return storyList.size
    }

    override fun onBindViewHolder(holder: StoriesViewHolder, position: Int) {
        holder.textUserName.text = storyList[position].textUserName
        holder.textTimeInMinutes.text = storyList[position].textTimeInMinutes
        holder.imageUser.setImageResource(storyList[position].imageUser)
        holder.imagePost.setImageResource(storyList[position].imagePost)

        holder.textUserName.setOnClickListener {
            println(holder.textUserName.text)
        }
    }
}
