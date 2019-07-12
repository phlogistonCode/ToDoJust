package com.phlogiston.todojust.di.scope

import javax.inject.Scope
import kotlin.annotation.AnnotationRetention.RUNTIME

@MustBeDocumented
@Scope
@Retention(RUNTIME)
annotation class PerActivity