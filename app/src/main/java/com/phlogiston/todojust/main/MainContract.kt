package com.phlogiston.todojust.main

import com.phlogiston.todojust.base.BasePresenter
import com.phlogiston.todojust.base.BaseView

interface MainContract {

    interface View : BaseView<Presenter> {}

    interface Presenter : BasePresenter<View>
}