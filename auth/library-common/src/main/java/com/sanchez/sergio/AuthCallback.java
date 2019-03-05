package com.sanchez.sergio;

public interface AuthCallback {
  void onSuccess(SocialUser socialUser);

  void onError(Throwable error);

  void onCancel();
}
