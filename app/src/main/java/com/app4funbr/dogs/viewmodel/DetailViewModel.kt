package com.app4funbr.dogs.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app4funbr.dogs.model.DogBreed

class DetailViewModel: ViewModel() {

    val dogLiveData = MutableLiveData<DogBreed>()

    fun fetch() {
        val dog = DogBreed("1", "Corgi", "15 years", "breedGroup",
            "bredFor", "temperament", "")
        dogLiveData.value = dog
    }
}