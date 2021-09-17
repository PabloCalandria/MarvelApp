package com.intermedia.challenge.mvp.model

import android.content.Intent
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth

class LoginModel {

    fun createNewAccount(email: String, password: String, showSuccess:() -> Unit, showError:() -> Unit){
        if (email.isNotEmpty() && password.isNotEmpty()) {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        showSuccess.invoke()
                    }else{
                        showError.invoke()
                    }
                }
        }
    }

    fun authenticationAccount(email: String, password: String, goToMainScreen:() -> Unit, showError:() -> Unit) {
        if(email.isNotEmpty() && password.isNotEmpty()){
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener{
                    if(it.isSuccessful){
                        goToMainScreen.invoke()
                    }else{
                        showError.invoke()
                    }
                }
        }
    }

}