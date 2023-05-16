package com.example.posts.util

data class Resource<out U,T>(val status: Status, val data: T?, val message: U?) {

    companion object {

        fun <U,T> success(data: T? = null): Resource<U,T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <U,T> error(msg: U?, data: T? = null): Resource<U,T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <U,T> loading(data: T? = null): Resource<U,T> {
            return Resource(Status.LOADING, data, null)
        }

        fun <U,T> unknown(data: T? = null): Resource<U,T> = Resource(Status.UNKNOWN, data,null)

    }

}