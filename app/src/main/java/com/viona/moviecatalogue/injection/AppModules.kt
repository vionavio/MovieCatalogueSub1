package com.viona.moviecatalogue.injection

import com.viona.moviecatalogue.data.repository.DataRepository
import com.viona.moviecatalogue.data.source.remote.RemoteDataSource
import com.viona.moviecatalogue.utils.network.NetworkConfig
import org.koin.dsl.module

val appModules = module {
    single { NetworkConfig() }
    single { RemoteDataSource(get()) }
    single { DataRepository(get()) }
}