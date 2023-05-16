package com.example.posts.ui.main.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.posts.util.Status
import com.example.posts.util.Validator

class LoginViewModel @ViewModelInject constructor(): ViewModel()  {


    private val emailField: MutableLiveData<String> = MutableLiveData()
    private val passwordField: MutableLiveData<String> = MutableLiveData()
    val loginBtn: MutableLiveData<Boolean> = MutableLiveData()


    fun onEmailChange(email: String) {emailField.value = email}

    fun onPasswordChange(password: String) {passwordField.value = password}

    fun validateInput(){
        val email = emailField.value
        val password = passwordField.value

        val validations = Validator.validateLoginFields(email, password)

        val successValidation = validations.filter { it.resource.status == Status.SUCCESS}

        loginBtn.value = successValidation.size == validations.size && email != null && password != null
    }

    fun onLogin(){
        //go to next screen
    }
}