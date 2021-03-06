package edu.uoc.pac4.data.di

import edu.uoc.pac4.data.oauth.AuthenticationRepository
import edu.uoc.pac4.data.oauth.OAuthAuthenticationDataSource
import edu.uoc.pac4.data.oauth.OAuthAuthenticationRepository
import edu.uoc.pac4.data.streams.StreamsRepository
import edu.uoc.pac4.data.streams.TwitchStreamsDataSource
import edu.uoc.pac4.data.streams.TwitchStreamsRepository
import edu.uoc.pac4.data.user.TwitchUserDataSource
import edu.uoc.pac4.data.user.TwitchUserRepository
import edu.uoc.pac4.data.user.UserRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by alex on 11/21/20.
 */

val dataModule = module {

    // Streams example
    single<StreamsRepository> { TwitchStreamsRepository(TwitchStreamsDataSource(get())) }
    single<UserRepository> { TwitchUserRepository(TwitchUserDataSource(get ())) }
    single<AuthenticationRepository> { OAuthAuthenticationRepository(OAuthAuthenticationDataSource(get(),androidContext()),androidContext()) }

    //Factories User
    factory { TwitchUserRepository(get()) }
    factory { TwitchUserDataSource(get()) }

    //Factories Streams
    factory { TwitchStreamsRepository(get()) }
    factory { TwitchStreamsDataSource(get()) }

    //Factories OAuth
    factory { OAuthAuthenticationRepository(get(),androidContext()) }
    factory { OAuthAuthenticationDataSource(get(),androidContext()) }

}