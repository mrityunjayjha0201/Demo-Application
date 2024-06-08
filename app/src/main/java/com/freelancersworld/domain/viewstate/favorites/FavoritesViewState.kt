package com.freelancersworld.domain.viewstate.favorites

import androidx.compose.runtime.Stable
import com.freelancersworld.data.models.FavoriteEntity
import com.freelancersworld.domain.viewstate.IViewState

@Stable
data class FavoritesViewState(
    val favoritesList: List<FavoriteEntity> = emptyList(),
    val favoriteId: Int? = null,
    val isDisplay: Boolean = false,
    val isAllDeleteFavorites: Boolean = false,
    val isLoading: Boolean = false,
) : IViewState