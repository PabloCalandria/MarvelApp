package com.intermedia.challenge.ui.login

import android.content.Intent
import android.os.Bundle
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

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var callbackManager = CallbackManager.Factory.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Setup
        setup()
    }

    private fun setup(){
        binding.buttonSignUp.setOnClickListener{
            val email = binding.emailLoginEditText.text.toString()
            val password = binding.passwordLoginEditText.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(applicationContext, "Se creo la cuenta con exito", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, MainScreenActivity::class.java))
                            finish()
                        }else{
                            Toast.makeText(applicationContext, "La cuenta ya existe", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

        binding.buttonLogin.isEnabled = false

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
            binding.buttonLogin.isEnabled = true
            binding.buttonLogin.setOnClickListener{
                val email = binding.emailLoginEditText.text.toString()
                val password = binding.passwordLoginEditText.text.toString()
                if(email.isNotEmpty() && password.isNotEmpty()){
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(
                        email,
                        password
                    ).addOnCompleteListener {
                        if(it.isSuccessful){
                            startActivity(Intent(this, MainScreenActivity::class.java))
                            finish()
                        }else{
                            Toast.makeText(applicationContext, "Se produjo un error de autenticacion", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }else {
            binding.buttonLogin.isEnabled = false
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode,resultCode,data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}

