package com.phlogiston.todojust.notes

import com.phlogiston.todojust.base.BasePresenter
import com.phlogiston.todojust.base.BaseView

interface NotesContract {

    interface View : BaseView<Presenter> {}

    interface Presenter : BasePresenter<View>
}