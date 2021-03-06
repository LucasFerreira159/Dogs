package com.app4funbr.dogs.interfaces

import com.app4funbr.dogs.model.DogBreed
import io.reactivex.Single
import retrofit2.http.GET

interface DogsAPI {

    @GET("DevTides/DogsApi/master/dogs.json")
    fun getDogs(): Single<List<DogBreed>>
}