package edu.uoc.pac4.data.streams

import android.util.Log
import edu.uoc.pac4.data.network.Endpoints
import edu.uoc.pac4.data.network.UnauthorizedException
import io.ktor.client.HttpClient
import io.ktor.client.features.ClientRequestException
import io.ktor.client.request.get
import io.ktor.client.request.parameter

/**
 * Created by alex on 11/21/20.
 */

class TwitchStreamsRepository(private val httpClient: HttpClient) : StreamsRepository {

    private val TAG = "TwitchStreamsRepository"

    @Throws(UnauthorizedException::class)
    override suspend fun getStreams(cursor: String? = null): StreamsResponse? {

        try {
            val response = httpClient
                    .get<StreamsResponse>(Endpoints.streamsUrl) {
                        cursor?.let { parameter("after", it) }
                    }
            return response
        } catch (t: Throwable) {
            Log.w(TAG, "Error getting streams", t)
            // Try to handle error
            return when (t) {
                is ClientRequestException -> {
                    // Check if it's a 401 Unauthorized
                    if (t.response?.status?.value == 401) {
                        throw UnauthorizedException
                    }
                    null
                }
                else -> null
            }
        }
    }

}