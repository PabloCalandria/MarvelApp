package com.intermedia.challenge.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.intermedia.challenge.ui.main.MainScreenActivity
import com.intermedia.challenge.databinding.ActivityLoginBinding
import com.intermedia.challenge.mvp.model.LoginModel
import com.intermedia.challenge.mvp.presenter.LoginPresenter
import com.intermedia.challenge.mvp.view.LoginView

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var presenter: LoginPresenter
    private var callbackManager = CallbackManager.Factory.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        presenter = LoginPresenter(LoginModel(), LoginView(this,binding))
        setContentView(binding.root)

        //Setup
        setup()
    }

    private fun setup(){
        binding.buttonSignUp.setOnClickListener{
            presenter.createNewAccount()
        }

        binding.buttonLogin.visibility = View.INVISIBLE

        binding.emailLoginEditText.setOnFocusChangeListener { view, b ->
            change()
        }

        binding.passwordLoginEditText.setOnFocusChangeListener { view, b ->
            change()
        }

        binding.loginFacebookCardView.setOnClickListener {
            LoginManager.getInstance().logInWithReadPermissions(this, listOf("email"))
            LoginManager.getInstance().registerCallback(callbackManager,
                object : FacebookCallback<LoginResult> {

                    override fun onSuccess(result: LoginResult?) {
                        result?.let {
                            val token = it.accessToken

                            val credential = FacebookAuthProvider.getCredential(token.token)

                            FirebaseAuth.getInstance().signInWithCredential(credential)
                                .addOnCompleteListener{
                                    if(it.isSuccessful){
                                        startActivity(Intent(applicationContext, MainScreenActivity::class.java))
                                    }else{
                                        Toast.makeText(applicationContext, "error", Toast.LENGTH_SHORT).show()
                                    }
                                }
                        }
                    }

                    override fun onCancel() {

                    }

                    override fun onError(error: FacebookException?) {
                        Toast.makeText(applicationContext, "error", Toast.LENGTH_SHORT).show()
                    }

                })
        }
    }

    private fun change() {
        if(binding.emailLoginEditText.text.toString().isNotEmpty() && binding.passwordLoginEditText.text.toString().isNotEmpty()){
            binding.buttonLogin.visibility = View.VISIBLE
            binding.buttonLogin.setOnClickListener{
                presenter.login()
            }
        }else {
            binding.buttonLogin.visibility = View.INVISIBLE
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode,resultCode,data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}

