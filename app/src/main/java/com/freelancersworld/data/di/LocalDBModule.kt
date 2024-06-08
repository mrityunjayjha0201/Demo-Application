package com.freelancersworld.data.di

import android.content.Context
import androidx.compose.runtime.Stable
import com.freelancersworld.data.local.dao.FavoriteDao
import com.freelancersworld.data.local.db.FreelancersWorldDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Stable
@Module
@InstallIn(SingletonComponent::class)
class LocalDBModule {
    @Provides
    @Singleton
    fun provideFreelancersWorldDatabase(
        @ApplicationContext context: Context
    ): FreelancersWorldDatabase {
        return FreelancersWorldDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideFavoriteDao(appDatabase: FreelancersWorldDatabase): FavoriteDao {
        return appDatabase.favoriteDao()
    }
}
