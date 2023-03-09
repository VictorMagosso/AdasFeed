package com.victor.adasfeed

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StoriesAdapter(
    private val storiesList: List<Stories> = mockList()
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
        return storiesList.size
    }

    //Bind = vincular
    //View holder = "segura" um espaço pra view
    override fun onBindViewHolder(holder: StoriesViewHolder, position: Int) {
        holder.textUserName.text = storiesList[position].textUserName
        holder.textTimeInMinutes.text = storiesList[position].textTimeInMinutes
        holder.imageUser.setImageResource(storiesList[position].imageUser)
        holder.imagePost.setImageResource(storiesList[position].imagePost)

        holder.textUserName.setOnClickListener {
            println(holder.textUserName.text)
        }
    }
}

private fun mockList() = listOf(Stories(
    "Joao",
    "15 m",
    imageUser = R.drawable.user1,
    imagePost = R.drawable.stories1,
),
    Stories(
        "Victor",
        "28 m",
        imageUser = R.drawable.user2,
        imagePost = R.drawable.stories2,
    ), Stories(
        "Marina da Silva Costa",
        "59 m",
        imageUser = R.drawable.user3,
        imagePost = R.drawable.stories3,
    ), Stories(
        "Ninja desconhecido",
        "60 m",
        imageUser = R.drawable.user4,
        imagePost = R.drawable.stories4,
    ))
