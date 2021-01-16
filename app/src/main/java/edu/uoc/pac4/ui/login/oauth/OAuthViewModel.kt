package edu.uoc.pac4.ui.login.oauth

import androidx.lifecycle.ViewModel
import edu.uoc.pac4.data.oauth.OAuthAuthenticationRepository
import org.koin.android.ext.android.inject
import org.koin.java.KoinJavaComponent.inject


class OAuthViewModel ():ViewModel(val oAuthAuthenticationRepository: OAuthAuthenticationRepository) {



}