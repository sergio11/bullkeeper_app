package com.sanchez.sergio.instagram

import com.sanchez.sergio.*

object SimpleAuth {
  @JvmStatic
  fun connectInstagram(scopes: List<String> = listOf(), listener: AuthCallback) {
    AuthDataHolder.getInstance().instagramAuthData = AuthData(scopes, listener)
    InstagramAuthActivity.start(Initializer.context)
  }

  @JvmStatic
  fun disconnectInstagram() {
    AuthDataHolder.getInstance().instagramAuthData = null
    CookiesUtils.clearCookies(Initializer.context)
  }
}
