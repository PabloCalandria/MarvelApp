package com.intermedia.challenge.mvp.view

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.intermedia.challenge.R
import com.intermedia.challenge.databinding.ActivityLoginBinding
import com.intermedia.challenge.mvp.view.base.ActivityView
import com.intermedia.challenge.ui.main.MainScreenActivity

class LoginView(activity: Activity, private val binding: ActivityLoginBinding): ActivityView(activity) {

    fun getUserEmail(): String{
        return binding.emailLoginEditText.text.toString()
    }

    fun getUserPassword(): String{
        return binding.passwordLoginEditText.text.toString()
    }

    fun showFail() {
        Toast.makeText(context, context?.resources?.getString(R.string.message_fail), Toast.LENGTH_SHORT).show()
    }

    fun showSuccess() {
        Toast.makeText(context, context?.resources?.getString(R.string.message_account), Toast.LENGTH_SHORT).show()
        context?.let {
            startActivity(it, Intent(activity, MainScreenActivity::class.java), null)
            activity?.finish()
        }
    }

    fun goToMainScreen() {
        context?.let {
            startActivity(it, Intent(activity, MainScreenActivity::class.java), null)
            activity?.finish()
        }
    }

    fun showNoExist() {
        Toast.makeText(context, "La cuenta no existe", Toast.LENGTH_SHORT).show()
    }
}