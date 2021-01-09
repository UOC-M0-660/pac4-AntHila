package edu.uoc.pac4.data.oauth

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import edu.uoc.pac4.data.SessionManager
import edu.uoc.pac4.data.network.Endpoints
import edu.uoc.pac4.data.streams.TwitchStreamsDataSource
import edu.uoc.pac4.ui.login.LoginActivity
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter
import io.ktor.client.request.post

/**
 * Created by alex on 11/21/20.
 */
class OAuthAuthenticationRepository(private val oAuthAuthenticationDataSource: OAuthAuthenticationDataSource, val context: Context) : AuthenticationRepository {

    private val TAG = "OAuthAuthenticationRepository"

    override suspend fun isUserAvailable(): Boolean {
        return SessionManager(context).isUserAvailable()
    }

    override suspend fun login(authorizationCode: String): Boolean {
        return oAuthAuthenticationDataSource.login(authorizationCode)
    }

    override suspend fun logout() {
        // Clear local session data
        SessionManager(context).clearAccessToken()
        SessionManager(context).clearRefreshToken()
        // Close this and all parent activities
        //ActivityCompat.finishAffinity(this)

        // Open Login
        var intent = Intent(context, LoginActivity::class.java)
        ContextCompat.startActivity(context,Intent(context, LoginActivity::class.java),null)
    }
}