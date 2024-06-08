package com.freelancersworld.data.di

import android.content.Context
import androidx.compose.runtime.Stable
import com.freelancersworld.FreelancersWorldApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Stable
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): FreelancersWorldApp {
        return app as FreelancersWorldApp
    }
}