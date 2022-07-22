package com.example.jobsearch.di

import com.example.jobsearch.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @FileName:  NetworkModule
 * @Author:  ganlong
 * @CreateDate:  2022/7/22
 * @Description:
 */
@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideApiService(): ApiService = ApiService.create()
}