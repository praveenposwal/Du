package com.example.posts.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.posts.R
import com.example.posts.ui.main.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val loginViewModel : LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupView()
        setupObserver()
    }

    private fun setupObserver() {
         loginViewModel.loginBtn.observe(this,  Observer {
             bt_login.isEnabled = it
             bt_login.isClickable = it
         })
    }

    private fun setupView(){
        et_email.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                loginViewModel.onEmailChange(s.toString())
                loginViewModel.validateInput()
                Log.d("LOG","email-changed :: " + s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        })

        et_password.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                loginViewModel.onPasswordChange(s.toString())
                loginViewModel.validateInput()
                Log.d("LOG","password-changed ::" + s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        })

        bt_login.setOnClickListener {
            val intent = Intent(this, PostActivity::class.java)
            startActivity(intent)
        }
    }


}