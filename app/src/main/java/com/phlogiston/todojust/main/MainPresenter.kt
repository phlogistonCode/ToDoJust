package com.phlogiston.todojust.main

import javax.inject.Inject

final class MainPresenter @Inject constructor() : MainContract.Presenter {

    private var mainView: MainContract.View? = null

    override fun takeView(view: MainContract.View) {
        mainView = view
    }

    override fun dropView() {
        mainView = null
    }
}