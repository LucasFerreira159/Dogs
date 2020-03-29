package com.app4funbr.dogs

import com.app4funbr.dogs.interfaces.DogsAPI
import com.app4funbr.dogs.model.DogBreed
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://raw.githubusercontent.com"

class DogsApiService {

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(DogsAPI::class.java)

    fun getDogs(): Single<List<DogBreed>> {
        return api.getDogs()
    }
}