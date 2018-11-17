package com.sniper.social.base.utils


inline fun <T> Iterable<T>.filterItem(predicate: (T) -> Boolean): T {
    return filterTo(ArrayList<T>(), predicate)[0]
}
