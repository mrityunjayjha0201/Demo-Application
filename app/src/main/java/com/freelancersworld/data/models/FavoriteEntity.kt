package com.freelancersworld.data.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.freelancersworld.data.remote.utils.Constants

@Entity(tableName = Constants.TABLE_NAME)
data class FavoriteEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = Constants.COLUMN_ID) val id: Int,
    @ColumnInfo(name = Constants.COLUMN_NAME) val name: String,
    @ColumnInfo(name = Constants.COLUMN_IMAGE_URL) val image: String,
    @ColumnInfo(name = Constants.COLUMN_CREATED) val created: String,
    @ColumnInfo(name = Constants.COLUMN_STATUS) val status: Status,
    @ColumnInfo(name = Constants.COLUMN_SPECIES) val species: String,
    @ColumnInfo(name = Constants.COLUMN_GENDER) val gender: String,
    @ColumnInfo(name = Constants.COLUMN_TYPE) val type: String,
    @ColumnInfo(name = Constants.COLUMN_URL) val url: String,
    @ColumnInfo(name = Constants.COLUMN_EPISODE) val episode: List<String>
)