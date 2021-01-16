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

class TwitchStreamsRepository(private val twitchStreamsDataSource: TwitchStreamsDataSource) : StreamsRepository {

    private val TAG = "TwitchStreamsRepository"

    override suspend fun getStreams(cursor: String?): Pair<String?, List<Stream>?> {
        val response = twitchStreamsDataSource.getStreams()

        return Pair(response?.pagination?.cursor, response?.data)
    }

}