package com.phlogiston.todojust.base

interface BasePresenter<T> {

    fun takeView(view: T)

    fun dropView()
}