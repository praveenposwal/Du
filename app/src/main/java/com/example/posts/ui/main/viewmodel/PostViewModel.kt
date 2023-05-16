package com.example.posts.ui.main.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.posts.data.model.PostModel
import com.example.posts.data.repository.PostRepository
import com.example.posts.util.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class PostViewModel @ViewModelInject constructor(
    private val postRepository: PostRepository
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        _albums.postValue(Resource.error("Something Went Wrong"))
    }

    private val _albums = MutableLiveData<Resource<String,List<PostModel>>>()

    val albums: LiveData<Resource<String,List<PostModel>>>
        get() = _albums

    init {
        getAlbums()
    }

    private fun getAlbums() {

        viewModelScope.launch(exceptionHandler) {
            _albums.postValue(Resource.loading())
            postRepository.getAlbum().let {
                if (it.isSuccessful) {
                    _albums.postValue(Resource.success(it.body()))
                } else {
                    _albums.postValue(Resource.error(it.errorBody().toString()))
                }
            }
        }

    }

}