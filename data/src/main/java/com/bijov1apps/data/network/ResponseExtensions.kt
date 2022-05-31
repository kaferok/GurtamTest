package com.bijov1apps.data.network

import retrofit2.Response
import com.bijov1apps.domain.utils.Result

fun <T> Response<T>.bodyOrError():Result<T>{
    if(isSuccessful){
        body()?.let {
            return Result.Success(it)
        }
    }
    return Result.Failure("Error, please handle me")
}
