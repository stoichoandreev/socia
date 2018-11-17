package com.sniper.social.converter

interface BaseConverter<in R, out VM> {

    fun map(response: R) : VM

}
