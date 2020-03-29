package com.app4funbr.dogs.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.app4funbr.dogs.R
import com.app4funbr.dogs.model.DogBreed
import com.app4funbr.dogs.util.getProgressDrawable
import com.app4funbr.dogs.util.loadImage
import kotlinx.android.synthetic.main.item_dog.view.*

class DogsAdapter (val dogsList: ArrayList<DogBreed>) :
    RecyclerView.Adapter<DogsAdapter.DogsViewHolder>() {

    fun updateDogList(newDogList: List<DogBreed>) {
        dogsList.clear()
        dogsList.addAll(newDogList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_dog, parent, false)
        return DogsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dogsList.size
    }

    override fun onBindViewHolder(holder: DogsViewHolder, position: Int) {
        holder.view.text_name.text = dogsList[position].dogBreed
        holder.view.text_lifespan.text = dogsList[position].lifeSpan
        holder.view.setOnClickListener {
            Navigation.findNavController(it).navigate(ListFragmentDirections.actionDetailFragment())
        }
        holder.view.image.loadImage(
            dogsList[position].imageUrl,
            getProgressDrawable(holder.view.context))
    }

    class DogsViewHolder(var view:View): RecyclerView.ViewHolder(view) {

    }
}