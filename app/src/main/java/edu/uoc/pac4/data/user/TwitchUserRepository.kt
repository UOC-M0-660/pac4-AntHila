package edu.uoc.pac4.data.user

import android.util.Log
import edu.uoc.pac4.data.network.Endpoints
import edu.uoc.pac4.data.network.UnauthorizedException
import io.ktor.client.HttpClient
import io.ktor.client.features.ClientRequestException
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.put

/**
 * Created by alex on 11/21/20.
 */

class TwitchUserRepository(private val twitchUserDataSource: TwitchUserDataSource) : UserRepository {

    private val TAG = "TwitchUserRepository"

    override suspend fun getUser(): User? {
        return twitchUserDataSource.getUser()
    }

    override suspend fun updateUser(description: String): User? {
        return twitchUserDataSource.updateUser(description)
    }


}