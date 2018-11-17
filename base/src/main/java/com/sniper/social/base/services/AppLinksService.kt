package com.sniper.social.base.services

class AppLinksService(private var baseAppLinkUrl: String) {

    fun generateScreenLink(screenLink: String): String = baseAppLinkUrl + screenLink

}
