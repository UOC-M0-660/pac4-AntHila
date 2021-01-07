package edu.uoc.pac4.data.oauth

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import edu.uoc.pac4.data.SessionManager
import edu.uoc.pac4.data.network.Endpoints
import edu.uoc.pac4.ui.login.LoginActivity
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter
import io.ktor.client.request.post

class OAuthAuthenticationDataSource (private val httpClient: HttpClient) {

    private val TAG = "OAuthAuthenticationDataSource"

    suspend fun isUserAvailable(): Boolean {
        TODO("Not yet implemented")
    }

    @SuppressLint("LongLogTag")
    suspend fun login(authorizationCode: String): Boolean {
        // Get Tokens from Twitch
        try {
            val response = httpClient
                .post<OAuthTokensResponse>(Endpoints.tokenUrl) {
                    parameter("client_id", OAuthConstants.clientID)
                    parameter("client_secret", OAuthConstants.clientSecret)
                    parameter("code", authorizationCode)
                    parameter("grant_type", "authorization_code")
                    parameter("redirect_uri", OAuthConstants.redirectUri)
                }

            return true

        } catch (t: Throwable) {
            Log.w(TAG, "Error Getting Access token", t)
            return false
        }
    }

    suspend fun logout() {
        // Clear local session data
        SessionManager(this).clearAccessToken()
        SessionManager(this).clearRefreshToken()
        // Close this and all parent activities
        ActivityCompat.finishAffinity()
        // Open Login
        ContextCompat.startActivity(Intent(this, LoginActivity::class.java))
    }
}