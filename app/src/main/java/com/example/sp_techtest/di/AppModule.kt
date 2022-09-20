package com.example.sp_techtest.di

import com.example.sp_techtest.viewmodel.AlbumAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAlbumAPI(): AlbumAPIService {
        return AlbumAPIService.getInstance().create(AlbumAPIService::class.java)
    }
}