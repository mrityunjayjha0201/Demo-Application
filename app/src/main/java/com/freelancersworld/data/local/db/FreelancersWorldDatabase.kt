package com.freelancersworld.data.local.db

import android.content.Context
import androidx.compose.runtime.Stable
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.freelancersworld.data.local.converter.PhotosConverter
import com.freelancersworld.data.local.dao.FavoriteDao
import com.freelancersworld.data.models.FavoriteEntity
import com.freelancersworld.data.remote.utils.Constants

@Stable
@Database(
    entities = [FavoriteEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(PhotosConverter::class)
abstract class FreelancersWorldDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile
        private var instance: FreelancersWorldDatabase? = null

        fun getDatabase(context: Context): FreelancersWorldDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, FreelancersWorldDatabase::class.java, Constants.TABLE_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}