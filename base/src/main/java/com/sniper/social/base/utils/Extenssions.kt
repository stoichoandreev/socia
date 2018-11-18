package com.sniper.social.base.utils

import java.lang.IndexOutOfBoundsException

inline fun <T> Iterable<T>.filterItem(predicate: (T) -> Boolean): T? =
        try {
            filterTo(ArrayList(), predicate)[0]
        } catch (ex: IndexOutOfBoundsException) {
            null
        }
