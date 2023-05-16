package com.nagarro.posts.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.posts.R
import com.nagarro.posts.data.model.PostModel
import kotlinx.android.synthetic.main.item_layout.view.*

class PostAdapter(
    private val posts: ArrayList<PostModel>,
) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    private val allPosts = ArrayList<PostModel>(posts)

    var tabPosition = 0

    internal var clickListener: (Int, PostModel)->Unit = { _: Int, _: PostModel -> }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(post: PostModel, position: Int, clickListener: (Int, PostModel) -> Unit){
            itemView.tv_post_title.text = post.title
            itemView.tv_post_details.text = post.body
            itemView.setOnClickListener {
                clickListener(position, post)
            }
            if(tabPosition == 0){
                itemView.visibility = View.VISIBLE
                itemView.layoutParams =
                    RecyclerView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
            }else {
                if(post.isFav){
                    itemView.visibility = View.VISIBLE
                    itemView.layoutParams =
                        RecyclerView.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                }else{
                    itemView.visibility = View.GONE
                    itemView.layoutParams = RecyclerView.LayoutParams(0, 0)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(posts[position], position, clickListener)
    }

    override fun getItemCount(): Int {
      return  posts.size
    }

    fun updateData(list: List<PostModel>) {
        posts.addAll(list)
        allPosts.addAll(list)
    }

    fun filter(onlyFav: Boolean){
        if(onlyFav){
            var searchResult =  allPosts.filter { it.isFav}
            posts.clear()
            posts.addAll(searchResult)
        }else{
            posts.clear()
            posts.addAll(allPosts)
        }
        notifyDataSetChanged()
    }
}