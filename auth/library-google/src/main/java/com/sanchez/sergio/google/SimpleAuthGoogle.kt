package com.sanchez.sergio.google

import com.sanchez.sergio.AuthCallback
import com.sanchez.sergio.AuthData
import com.sanchez.sergio.AuthDataHolder
import com.sanchez.sergio.Initializer

object SimpleAuth {
  @JvmStatic
  fun connectGoogle(scopes: List<String> = listOf(), listener: AuthCallback) {
    AuthDataHolder.getInstance().googleAuthData = AuthData(scopes, listener)
    GoogleAuthActivity.start(Initializer.context)
  }

  @JvmStatic
  fun disconnectGoogle() {
    AuthDataHolder.getInstance().googleAuthData = null
    GoogleAuthActivity.setGoogleDisconnectRequested(Initializer.context,true)
  }

  @JvmStatic
  fun revokeGoogle() {
    GoogleAuthActivity.setGoogleRevokeRequested(Initializer.context,true)
  }
}
