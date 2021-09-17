package com.intermedia.challenge.mvp.presenter

import com.intermedia.challenge.mvp.model.LoginModel
import com.intermedia.challenge.mvp.view.LoginView

class LoginPresenter(var model: LoginModel, var view: LoginView) {

    fun createNewAccount(){
        model.createNewAccount(view.getUserEmail(),view.getUserPassword(),
            { view.showSuccess() }, {view.showFail()})
    }

    fun login() {
        model.authenticationAccount(view.getUserEmail(),view.getUserPassword(),
            {view.goToMainScreen()}, {view.showNoExist()})
    }
}