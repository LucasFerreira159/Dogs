package com.app4funbr.dogs.view

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.palette.graphics.Palette
import com.app4funbr.dogs.R
import com.app4funbr.dogs.databinding.FragmentDetailBinding
import com.app4funbr.dogs.model.DogPalette
import com.app4funbr.dogs.util.getProgressDrawable
import com.app4funbr.dogs.util.loadImage
import com.app4funbr.dogs.viewmodel.DetailViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    private var dogUuid = 0
    private lateinit var viewModel: DetailViewModel
    private lateinit var dataBinding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            dogUuid = DetailFragmentArgs.fromBundle(it).dogUuid
        }

        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.fetch(dogUuid)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.dogLiveData.observe(this, Observer { dog ->
           dog?.let {
               dataBinding.dog = it
               it.imageUrl?.let { url ->
                   setupBackgroundColor(url)
               }
           }
        })
    }

    private fun setupBackgroundColor(url: String) {
        Glide.with(requireContext())
            .asBitmap()
            .load(url)
            .into(object : CustomTarget<Bitmap>(){
                override fun onLoadCleared(placeholder: Drawable?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                   Palette.from(resource)
                       .generate() {palette ->
                           val intColor = palette?.lightMutedSwatch?.rgb ?: 0
                           val myPalette = DogPalette(intColor)
                           dataBinding.palette = myPalette
                       }
                }
            })
    }
}
