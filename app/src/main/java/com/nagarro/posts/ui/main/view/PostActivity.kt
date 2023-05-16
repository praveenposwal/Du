package com.nagarro.posts.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.posts.R
import com.nagarro.posts.data.model.PostModel
import com.nagarro.posts.ui.main.adapter.PostAdapter
import com.nagarro.posts.ui.main.viewmodel.PostViewModel
import com.nagarro.posts.util.Status
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_post.*

@AndroidEntryPoint
class PostActivity : AppCompatActivity() {

    private val postViewModel : PostViewModel by viewModels()
    private lateinit var  postAdapter: PostAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        setView()
        setupObserver()
    }

    private fun setView() {
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    postAdapter?.tabPosition = tab.position
                    postAdapter?.notifyDataSetChanged()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
        recyclerView.layoutManager = LinearLayoutManager(this)
        postAdapter = PostAdapter(arrayListOf())
        postAdapter.clickListener = { i: Int, post: PostModel ->
            post.isFav = true
            postAdapter?.notifyItemChanged(i)
        }
        recyclerView.adapter = postAdapter
    }

    private fun setupObserver() {
        postViewModel.posts.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { posts -> render(posts) }
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun render(list: List<PostModel>) {
        postAdapter.updateData(list)
        postAdapter.notifyDataSetChanged()
    }
}