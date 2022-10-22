package com.example.tarea4galeriaperros

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.tarea4galeriaperros.databinding.ItemDogBinding
import com.squareup.picasso.Picasso

class DogViewHolder (view: View): RecyclerView.ViewHolder(view){
    private val binding = ItemDogBinding.bind(view)
    fun bind(image: String){
        //using picasso to use image url convert into image
        Picasso.get().load(image).into(binding.ivDog)
    }
}