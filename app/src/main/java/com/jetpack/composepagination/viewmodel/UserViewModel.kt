package com.jetpack.composepagination.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jetpack.composepagination.model.UserData
import com.jetpack.composepagination.utils.UserSource
import kotlinx.coroutines.flow.Flow

class UserViewModel: ViewModel() {

    val user: Flow<PagingData<UserData>> = Pager(PagingConfig(pageSize = 6)) {
        UserSource()
    }.flow.cachedIn(viewModelScope)
}