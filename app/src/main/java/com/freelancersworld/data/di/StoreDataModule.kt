package com.freelancersworld.data.di

import android.content.Context
import androidx.compose.runtime.Stable
import com.freelancersworld.data.datastore.StoreData
import com.freelancersworld.data.datastore.StoreDataImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Stable
@Module
@InstallIn(SingletonComponent::class)
object StoreDataModule {

    @Provides
    @Singleton
    fun provideStoreData(@ApplicationContext context: Context): StoreData {
        return StoreDataImpl(context)
    }
}
