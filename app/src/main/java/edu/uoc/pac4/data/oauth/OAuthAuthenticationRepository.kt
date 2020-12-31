package edu.uoc.pac4.data.oauth

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.core.content.ContextCompat.startActivity
import edu.uoc.pac4.data.SessionManager
import edu.uoc.pac4.data.network.Endpoints
import edu.uoc.pac4.ui.login.LoginActivity
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter
import io.ktor.client.request.post

/**
 * Created by alex on 11/21/20.
 */
class OAuthAuthenticationRepository(private val httpClient: HttpClient) : AuthenticationRepository {

    private val TAG = "OAuthAuthenticationRepository"

    override suspend fun isUserAvailable(): Boolean {
        TODO("Not yet implemented")
    }

    @SuppressLint("LongLogTag")
    override suspend fun login(authorizationCode: String): Boolean {
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

            return response

        } catch (t: Throwable) {
            Log.w(TAG, "Error Getting Access token", t)
            return null
        }
    }

    override suspend fun logout() {
        // Clear local session data
        SessionManager(this).clearAccessToken()
        SessionManager(this).clearRefreshToken()
        // Close this and all parent activities
        finishAffinity()
        // Open Login
        startActivity(Intent(this, LoginActivity::class.java))
    }
}