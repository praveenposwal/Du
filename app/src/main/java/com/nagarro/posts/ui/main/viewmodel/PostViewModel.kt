package com.nagarro.posts.ui.main.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nagarro.posts.data.model.PostModel
import com.nagarro.posts.data.repository.PostRepository
import com.nagarro.posts.util.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class PostViewModel @ViewModelInject constructor(
    private val postRepository: PostRepository
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        _posts.postValue(Resource.error("Something Went Wrong"))
    }

    private val _posts = MutableLiveData<Resource<String, List<PostModel>>>()

    val posts: LiveData<Resource<String, List<PostModel>>>
        get() = _posts

    init {
        getPosts()
    }

    private fun getPosts() {

        viewModelScope.launch(exceptionHandler) {
            _posts.postValue(Resource.loading())
            postRepository.getPosts().let {
                if (it.isSuccessful) {
                    _posts.postValue(Resource.success(it.body()))
                } else {
                    _posts.postValue(Resource.error(it.errorBody().toString()))
                }
            }
        }

    }

}