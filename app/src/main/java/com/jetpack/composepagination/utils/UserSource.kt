package com.jetpack.composepagination.utils

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jetpack.composepagination.model.UserData
import com.jetpack.composepagination.network.ApiClient
import retrofit2.HttpException
import java.io.IOException

class UserSource: PagingSource<Int, UserData>() {

    override fun getRefreshKey(state: PagingState<Int, UserData>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserData> {

        return try {
            val nextPage = params.key ?: 1
            val userList = ApiClient.apiService.getUserList(nextPage)
            LoadResult.Page(
                data = userList.data,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (userList.data.isEmpty()) null else userList.page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}