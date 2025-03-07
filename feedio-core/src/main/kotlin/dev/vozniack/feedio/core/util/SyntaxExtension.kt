package dev.vozniack.feedio.core.util

inline fun throwIfTrue(exception: Exception, block: () -> Boolean): Unit? = if (block()) throw exception else null

fun String?.takeIfNotEmpty(): String? = this?.takeIf { it.isNotEmpty() }
