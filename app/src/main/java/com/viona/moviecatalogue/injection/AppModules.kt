package com.viona.moviecatalogue.injection

import com.viona.moviecatalogue.data.network.NetworkConfig
import com.viona.moviecatalogue.data.repository.MovieRepository
import com.viona.moviecatalogue.data.repository.TVShowRepository
import com.viona.moviecatalogue.data.source.local.MovieLocalDatasource
import com.viona.moviecatalogue.data.source.local.TVShowLocalDatasource
import com.viona.moviecatalogue.data.source.local.room.AppDatabase
import com.viona.moviecatalogue.data.source.remote.MovieRemoteDataSource
import com.viona.moviecatalogue.data.source.remote.TVShowRemoteDataSource
import com.viona.moviecatalogue.utils.AppExecutors
import com.viona.moviecatalogue.utils.DataDummy
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModules = module {
    /*single { NetworkConfig() }
    single { RemoteDataSource(get()) }
    single { DataRepository(get()) }*/

    // context
    factory { AppDatabase.getInstance(androidContext()) }

    // core
    single { NetworkConfig() }
    single { get<NetworkConfig>().getApiService() }
    single { AppExecutors() }

    // datasource
    single { MovieRemoteDataSource(get()) }
    single { MovieLocalDatasource(get()) }
    single { TVShowRemoteDataSource(get()) }
    single { TVShowLocalDatasource(get()) }

    // repository
    single { MovieRepository(get(), get(), get()) }
    single { TVShowRepository(get(), get(), get()) }

    // utils
    single { DataDummy() }

}