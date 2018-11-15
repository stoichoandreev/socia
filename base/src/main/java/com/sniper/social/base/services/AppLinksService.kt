package com.sniper.social.base.services


class AppLinksService constructor(private var baseAppLinkUrl: String) {

    fun generateScreenLink(screenLink: String): String = baseAppLinkUrl + screenLink

}