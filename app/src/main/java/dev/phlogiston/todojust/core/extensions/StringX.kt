package dev.phlogiston.todojust.core.extensions

fun String?.isNotNullAndBlank() = this.isNullOrBlank().not()